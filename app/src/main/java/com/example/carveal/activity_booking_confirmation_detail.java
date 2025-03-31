package com.example.carveal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_booking_confirmation_detail extends Activity {

    private TextView txtDate, txtTime, txtLocation;
    private ImageButton btnBack;
    int carImageID;
    ImageView carImage;
    TextView modelText, yearText, transmissionText, fuelText,
            priceText, mileageText, priceText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation_detail);

        txtDate = findViewById(R.id.date);
        txtTime = findViewById(R.id.time);
        txtLocation = findViewById(R.id.location);
        btnBack = findViewById(R.id.btnBack);

        /*modelText = findViewById(R.id.modelText);
        yearText = findViewById(R.id.yearText);
        transmissionText = findViewById(R.id.transmissionText);
        fuelText = findViewById(R.id.fuelText);
        mileageText = findViewById(R.id.mileageText);*/

        priceText = findViewById(R.id.priceText);
        carImage = findViewById(R.id.carImage);
        priceText2 = findViewById(R.id.priceText2);

        Intent intent = getIntent();
        if (intent != null) {
            String date = intent.getStringExtra("date");
            String time = intent.getStringExtra("time");
            String location = intent.getStringExtra("location");

            String carModel = intent.getStringExtra("CAR_MODEL");
            String carPrice = intent.getStringExtra("CAR_PRICE");
            String carLocation = intent.getStringExtra("CAR_LOCATION");
            String carMileage = intent.getStringExtra("CAR_MILEAGE");
            String carYear = intent.getStringExtra("CAR_YEAR");
            String carTransmission = intent.getStringExtra("CAR_TRANSMISSION");
            String carFuel = intent.getStringExtra("CAR_FUEL");
            carImageID = intent.getIntExtra("CAR_IMAGE", 0);

            if (date != null) txtDate.setText(date);
            if (time != null) txtTime.setText(time);
            if (location != null) txtLocation.setText(location);

            priceText2.setText(carPrice);
            priceText.setText(carPrice);
            carImage.setImageResource(carImageID);

        }

        btnBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(activity_booking_confirmation_detail.this, MainActivity.class);
            backIntent.putExtra("TAB_POSITION",2);
            startActivity(backIntent);
            finish();
        });
    }
}
