package com.example.carveal;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class activity_weather_detail extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        TextView detailText = findViewById(R.id.detailText);
        detailText.setText("Weather Details");
        // Display weather details using the passed BookingData.
    }
}
