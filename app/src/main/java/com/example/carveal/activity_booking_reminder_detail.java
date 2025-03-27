package com.example.carveal;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class activity_booking_reminder_detail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_reminder_detail);

        TextView detailText = findViewById(R.id.detailText);
        detailText.setText("Booking Reminder Details");
        // Display further details using the passed BookingData.
    }
}
