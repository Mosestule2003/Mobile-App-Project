package com.example.carveal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CarDetail extends AppCompatActivity {
    Button back, contact, purchase;
    TextView modelTextView, priceTextView, locationTextView, mileageTextView, yearTextView,
            transmissionTextView, fuelTextView;
    ImageView carImage;
    int carImageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_car_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String carModel = intent.getStringExtra("CAR_MODEL");
        String carPrice = intent.getStringExtra("CAR_PRICE");
        String carLocation = intent.getStringExtra("CAR_LOCATION");
        String carMileage = intent.getStringExtra("CAR_MILEAGE");
        String carYear = intent.getStringExtra("CAR_YEAR");
        String carTransmission = intent.getStringExtra("CAR_TRANSMISSION");
        String carFuel = intent.getStringExtra("CAR_FUEL");
        carImageID = intent.getIntExtra("CAR_IMAGE", 0);


        modelTextView = findViewById(R.id.model);
        modelTextView.setText(carModel);

        priceTextView = findViewById(R.id.price);
        priceTextView.setText(carPrice);

        locationTextView = findViewById(R.id.location);
        locationTextView.setText(carLocation);

        mileageTextView = findViewById(R.id.car_mileage);
        mileageTextView.setText(carMileage);

        yearTextView = findViewById(R.id.car_year);
        yearTextView.setText(carYear);

        transmissionTextView = findViewById(R.id.car_transmission);
        transmissionTextView.setText(carTransmission);

        fuelTextView = findViewById(R.id.car_fuel);
        fuelTextView.setText(carFuel);

        carImage = findViewById(R.id.car_image);
        carImage.setImageResource(carImageID);


        back = findViewById(R.id.button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(i);
            }
        });

        contact = findViewById(R.id.contactBtn);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

                //Set the title
                builder.setTitle("Contact Seller");
                //Set the message you want to display
                builder.setMessage("Choose one to proceed.");
                builder.setCancelable(false);
                builder.setPositiveButton("Schedule Viewing", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //start intent schedule viewing
                        Intent infoIntent = new Intent(v.getContext(), activity_vehicle_info_analysis.class);
                        infoIntent.putExtra("CAR_MODEL",modelTextView.getText().toString());

                        infoIntent.putExtra("CAR_PRICE", priceTextView.getText().toString());

                        infoIntent.putExtra("CAR_LOCATION", locationTextView.getText().toString());

                        infoIntent.putExtra("CAR_MILEAGE", mileageTextView.getText().toString());

                        infoIntent.putExtra("CAR_YEAR", yearTextView.getText().toString());

                        infoIntent.putExtra("CAR_TRANSMISSION", transmissionTextView.getText().toString());

                        infoIntent.putExtra("CAR_FUEL", fuelTextView.getText().toString());

                        infoIntent.putExtra("CAR_IMAGE", carImageID);

                        startActivity(infoIntent);

                    }
                });

                builder.setNegativeButton("Message Seller", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                        intent.putExtra("TAB_POSITION", 1);
                        startActivity(intent);
                    }
                });

                //Creating dialog box
                AlertDialog dialog  = builder.create();
                dialog.show();
            }
        });

        purchase = findViewById(R.id.purchaseBtn);
        purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start intent purchase
                Intent infoIntent = new Intent(v.getContext(), activity_vehicle_info_analysis.class);
                infoIntent.putExtra("CAR_MODEL",modelTextView.getText().toString());

                infoIntent.putExtra("CAR_PRICE", priceTextView.getText().toString());

                infoIntent.putExtra("CAR_LOCATION", locationTextView.getText().toString());

                infoIntent.putExtra("CAR_MILEAGE", mileageTextView.getText().toString());

                infoIntent.putExtra("CAR_YEAR", yearTextView.getText().toString());

                infoIntent.putExtra("CAR_TRANSMISSION", transmissionTextView.getText().toString());

                infoIntent.putExtra("CAR_FUEL", fuelTextView.getText().toString());

                infoIntent.putExtra("CAR_IMAGE", carImageID);

                startActivity(infoIntent);
            }
        });

    }
}