package com.example.carveal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class activity_booking_confirmation_detail extends Activity {

    private TextView txtDate, txtTime, txtLocation;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation_detail);

        txtDate = findViewById(R.id.date);
        txtTime = findViewById(R.id.time);
        txtLocation = findViewById(R.id.location);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        if (intent != null) {
            String date = intent.getStringExtra("date");
            String time = intent.getStringExtra("time");
            String location = intent.getStringExtra("location");

            if (date != null) txtDate.setText(date);
            if (time != null) txtTime.setText(time);
            if (location != null) txtLocation.setText(location);
        }

        btnBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(activity_booking_confirmation_detail.this, activity_notification.class);
            startActivity(backIntent);
            finish();
        });

    }
}
