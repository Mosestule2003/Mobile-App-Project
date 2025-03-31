package com.example.carveal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

public class activity_schedule extends Activity {

    private CalendarView calendarView;
    private RadioGroup timeGroup;
    private Spinner spinnerLocation;
    private String selectedDate, selectedTime, selectedLocation;
    String carModel, carPrice, carLocation, carMileage, carYear, carTransmission, carFuel;
    int carImageID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        calendarView = findViewById(R.id.calendarView);
        timeGroup = findViewById(R.id.timeGroup);
        spinnerLocation = findViewById(R.id.spinnerLocation);

        selectedDate = new java.text.SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, month + 1, year);
        });

        Intent frontIntent = getIntent();
        carModel = frontIntent.getStringExtra("CAR_MODEL");
        carPrice = frontIntent.getStringExtra("CAR_PRICE");
        carLocation = frontIntent.getStringExtra("CAR_LOCATION");
        carMileage = frontIntent.getStringExtra("CAR_MILEAGE");
        carYear = frontIntent.getStringExtra("CAR_YEAR");
        carTransmission = frontIntent.getStringExtra("CAR_TRANSMISSION");
        carFuel = frontIntent.getStringExtra("CAR_FUEL");
        carImageID = frontIntent.getIntExtra("CAR_IMAGE", 0);

        // Setup Spinner for Location
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.locations,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(adapter);

        findViewById(R.id.btnConfirm).setOnClickListener(v -> {
            getSelectedTime();
            selectedLocation = spinnerLocation.getSelectedItem().toString();

            if (selectedTime == null || selectedTime.isEmpty()) {
                Toast.makeText(this, "Please select a pickup time", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(activity_schedule.this, activity_confirmation.class);
            intent.putExtra("date", selectedDate);
            intent.putExtra("time", selectedTime);
            intent.putExtra("location", selectedLocation);

            intent.putExtra("CAR_MODEL",carModel);

            intent.putExtra("CAR_PRICE", carPrice);

            intent.putExtra("CAR_LOCATION", carLocation);

            intent.putExtra("CAR_MILEAGE", carMileage);

            intent.putExtra("CAR_YEAR", carYear);

            intent.putExtra("CAR_TRANSMISSION", carTransmission);

            intent.putExtra("CAR_FUEL", carFuel);

            intent.putExtra("CAR_IMAGE", carImageID);

            startActivity(intent);
        });

        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());
    }

    private void getSelectedTime() {
        int selectedId = timeGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.rbMorning) {
            selectedTime = "09:00 AM";
        } else if (selectedId == R.id.rbAfternoon) {
            selectedTime = "12:00 PM";
        } else if (selectedId == R.id.rbEvening) {
            selectedTime = "05:00 PM";
        } else {
            selectedTime = "";
            Toast.makeText(this, "Please select a time slot", Toast.LENGTH_SHORT).show();
        }
    }
}