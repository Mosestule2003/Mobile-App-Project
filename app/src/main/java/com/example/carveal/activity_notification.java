package com.example.carveal;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class activity_notification extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<Notification> notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        recyclerView = findViewById(R.id.notificationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notifications = new ArrayList<>();

        notifications.add(new Notification(
                "Booking Confirmation",
                "Your booking was successful",
                System.currentTimeMillis(),
                "booking_confirmation"
        ));

        notifications.add(new Notification(
                "Booking Reminder",
                "Your booking reminder",
                System.currentTimeMillis(),
                "booking_reminder"
        ));

        notifications.add(new Notification(
                "Weather Update",
                "Weather caution",
                System.currentTimeMillis(),
                "weather_caution"
        ));

        adapter = new NotificationAdapter(notifications);
        recyclerView.setAdapter(adapter);
        adapter.startTimeUpdates();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.stopTimeUpdates();
    }
}
