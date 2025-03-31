package com.example.carveal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_confirmation extends Activity {

    private TextView txtDate, txtTime, txtLocation;
    private Button btnCancel, btnConfirm;
    private ImageButton btnBack;
    int carImageID;
    ImageView carImage;
    TextView modelText, priceText, priceText2, chargeText, totalText;
    String carPrice;


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

        priceText = findViewById(R.id.priceText);
        carImage = findViewById(R.id.carImage);
        priceText2 = findViewById(R.id.priceText2);
        modelText = findViewById(R.id.modelText);
        chargeText = findViewById(R.id.chargeText);
        totalText = findViewById(R.id.totalText);

        Intent intent = getIntent();
        if (intent != null) {
            String date = intent.getStringExtra("date");
            String time = intent.getStringExtra("time");
            String location = intent.getStringExtra("location");

            String carModel = intent.getStringExtra("CAR_MODEL");
            carPrice = intent.getStringExtra("CAR_PRICE");
            String carLocation = intent.getStringExtra("CAR_LOCATION");
            String carMileage = intent.getStringExtra("CAR_MILEAGE");
            String carYear = intent.getStringExtra("CAR_YEAR");
            String carTransmission = intent.getStringExtra("CAR_TRANSMISSION");
            String carFuel = intent.getStringExtra("CAR_FUEL");
            carImageID = intent.getIntExtra("CAR_IMAGE", 0);

            if (date != null) txtDate.setText(date);
            if (time != null) txtTime.setText(time);
            if (location != null) txtLocation.setText(location);

            PriceThread priceThread = new PriceThread();
            priceThread.start();
            modelText.setText(carModel);
            priceText2.setText(carPrice);
            carImage.setImageResource(carImageID);
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

    class PriceThread extends Thread {
        public void run() {
            int price = Integer.parseInt(carPrice.substring(1));
            int charge = price*6/100;
            int total = price + charge;

            priceText.post(new Runnable() {
                @Override
                public void run() {
                    priceText.setText("$" + price);
                }
            });

            chargeText.post(new Runnable() {
                @Override
                public void run() {
                    chargeText.setText("$" + charge);
                }
            });

            totalText.post(new Runnable() {
                @Override
                public void run() {
                    totalText.setText("$" + total);
                }
            });
        }
    }
}
