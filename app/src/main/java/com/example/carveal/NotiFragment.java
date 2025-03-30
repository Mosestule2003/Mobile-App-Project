package com.example.carveal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotiFragment#} factory method to
 * create an instance of this fragment.
 */
public class NotiFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<Notification> notifications;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noti, container, false);


        recyclerView = view.findViewById(R.id.notificationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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

        return view;
    }





    @Override
    public void onDestroy() {
        super.onDestroy();
        adapter.stopTimeUpdates();
    }
}
