package com.example.carveal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class activity_confirmation extends Activity {

    private TextView txtDate, txtTime, txtLocation;
    private Button btnCancel, btnConfirm;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        txtDate = findViewById(R.id.date);
        txtTime = findViewById(R.id.time);
        txtLocation = findViewById(R.id.location);
        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);
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
            Intent backIntent = new Intent(activity_confirmation.this, activity_schedule.class);
            startActivity(backIntent);
            finish();
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paymentIntent = new Intent(activity_confirmation.this, activity_payment.class);
            startActivity(paymentIntent);
            finish();
            }
        });

    }
}
