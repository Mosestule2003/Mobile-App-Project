package com.example.carveal;

import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private List<Notification> notifications;
    private final Handler handler = new Handler();
    private final Runnable updateTimeRunnable = new Runnable() {
        @Override
        public void run() {
            notifyDataSetChanged();
            handler.postDelayed(this, 60000);
        }
    };

    public NotificationAdapter(List<Notification> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notification notification = notifications.get(position);
        holder.title.setText(notification.getTitle());
        holder.time.setText(getTimeAgo(notification.getTimestamp()));

        if ("booking_reminder".equalsIgnoreCase(notification.getType()) && notification.hasBookingData()) {
            long now = System.currentTimeMillis();
            long appointmentTime = notification.getBookingData().getAppointmentTime();
            long diffMillis = appointmentTime - now;
            long minutesRemaining = TimeUnit.MILLISECONDS.toMinutes(diffMillis);
            holder.message.setText("Appointment in " + minutesRemaining + " minutes");
            setIconBasedOnType(holder.icon, notification.getType());
        } else {
            holder.message.setText(notification.getMessage());
            setIconBasedOnType(holder.icon, notification.getType());
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent;
            String type = notification.getType().toLowerCase();
            switch (type) {
                case "booking_confirmation":
                    intent = new Intent(v.getContext(), activity_booking_confirmation_detail.class);
                    break;
                case "booking_reminder":
                    intent = new Intent(v.getContext(), activity_booking_reminder_detail.class);
                    break;
                default:
                    intent = new Intent(v.getContext(), activity_notification_detail.class);
                    break;
            }

            if (notification.hasBookingData()) {
                intent.putExtra("bookingTitle", notification.getTitle());
                intent.putExtra("appointmentTimeMillis", notification.getBookingData().getAppointmentTime());
                intent.putExtra("bookingMessage", notification.getMessage());
            }

            v.getContext().startActivity(intent);
        });
    }

    private void setIconBasedOnType(ImageView imageView, String type) {
        switch (type.toLowerCase()) {
            case "booking_confirmation":
                imageView.setImageResource(R.drawable.ic_booking_confirm);
                break;
            case "booking_reminder":
                imageView.setImageResource(R.drawable.ic_booking_reminder);
                break;
            default:
                imageView.setImageResource(R.drawable.ic_default_notification);
        }
    }

    private String getTimeAgo(long timestamp) {
        long now = System.currentTimeMillis();
        long diff = now - timestamp;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
        if (minutes < 60) return minutes + " minutes ago";
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        if (hours < 24) return hours + " hours ago";
        long days = TimeUnit.MILLISECONDS.toDays(diff);
        return days + " days ago";
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public void startTimeUpdates() {
        handler.post(updateTimeRunnable);
    }

    public void stopTimeUpdates() {
        handler.removeCallbacks(updateTimeRunnable);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title, message, time;

        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            title = itemView.findViewById(R.id.title);
            message = itemView.findViewById(R.id.message);
            time = itemView.findViewById(R.id.time);
        }
    }
}
