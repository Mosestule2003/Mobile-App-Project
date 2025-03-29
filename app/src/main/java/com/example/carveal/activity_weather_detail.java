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

        String location = "New York";

        WeatherService.checkWeather(location, new WeatherService.WeatherCallback() {
            @Override
            public void onWeatherResult(String location, String condition, double temperature, boolean isFavorable) {
                String weatherDetails = "Location: " + location + "\n" +
                        "Condition: " + condition + "\n" +
                        "Temperature: " + temperature + "°C\n" +
                        "Weather is favorable: " + (isFavorable ? "Yes" : "No");

                detailText.setText(weatherDetails);
            }
        });
    }
}
