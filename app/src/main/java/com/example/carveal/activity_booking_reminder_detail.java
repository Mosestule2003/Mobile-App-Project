package com.example.carveal;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class activity_booking_reminder_detail extends AppCompatActivity {

    private TextView txtBookingTitle, txtBookingTime, txtAppointmentTime, txtBookingMessage;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_reminder_detail);

        txtBookingTitle = findViewById(R.id.txtBookingTitle);
        txtBookingTime = findViewById(R.id.txtBookingTime);
        txtAppointmentTime = findViewById(R.id.txtAppointmentTime);
        txtBookingMessage = findViewById(R.id.txtBookingMessage);
        btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        String bookingTitle = intent.getStringExtra("bookingTitle");
        String bookingTime = intent.getStringExtra("bookingTime");
        long appointmentTimeMillis = intent.getLongExtra("appointmentTimeMillis", -1);
        String bookingMessage = intent.getStringExtra("bookingMessage");

        if (bookingTitle != null && bookingTime != null) {
            txtBookingTitle.setText(bookingTitle);
            txtBookingTime.setText("Booking Time: " + bookingTime);
        } else {
            txtBookingTitle.setText("No title provided");
            txtBookingTime.setText("No booking time provided");
        }

        if (appointmentTimeMillis != -1) {
            txtAppointmentTime.setText("Appointment Time: " + formatAppointmentTime(appointmentTimeMillis));
        } else {
            txtAppointmentTime.setText("No appointment time available");
        }

        txtBookingMessage.setText(bookingMessage != null ? bookingMessage : "No message provided");

        btnBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(activity_booking_reminder_detail.this, MainActivity.class);
            backIntent.putExtra("TAB_POSITION",2);
            startActivity(backIntent);
            finish();
        });
    }

    private String formatAppointmentTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        return sdf.format(new Date(timestamp));
    }
}
