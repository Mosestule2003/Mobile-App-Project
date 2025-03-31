package com.example.carveal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class activity_vehicle_info_analysis extends AppCompatActivity {

    private static final String API_URL = "https://api.aimlapi.com/v1/chat/completions";
    private static final String API_KEY = "cbebe2cb52ba401e8053fa0e12067b46";
    private Button btnCancel, btnConfirm;
    private TextView modelText, yearText, transmissionText, fuelText, concernsText,
                    priceText, mileageText;

    int carImageID;
    String carLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info_analysis);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String carModel = intent.getStringExtra("CAR_MODEL");
        String carPrice = intent.getStringExtra("CAR_PRICE");
        carLocation = intent.getStringExtra("CAR_LOCATION");
        String carMileage = intent.getStringExtra("CAR_MILEAGE");
        String carYear = intent.getStringExtra("CAR_YEAR");
        String carTransmission = intent.getStringExtra("CAR_TRANSMISSION");
        String carFuel = intent.getStringExtra("CAR_FUEL");
        carImageID = intent.getIntExtra("CAR_IMAGE", 0);

        modelText = findViewById(R.id.modelText);
        yearText = findViewById(R.id.yearText);
        transmissionText = findViewById(R.id.transmissionText);
        fuelText = findViewById(R.id.fuelText);
        concernsText = findViewById(R.id.concernsText);
        btnCancel = findViewById(R.id.btnCancel);
        btnConfirm = findViewById(R.id.btnConfirm);
        priceText = findViewById(R.id.priceText);
        mileageText = findViewById(R.id.mileageText);



        modelText.setText(carModel);
        yearText.setText(carYear);
        transmissionText.setText(carTransmission);
        fuelText.setText(carFuel);
        priceText.setText(carPrice);
        mileageText.setText(carMileage);





        fetchPotentialConcerns();
    }

    private void fetchPotentialConcerns() {
        JSONObject payload = new JSONObject();
        try {
            JSONArray messages = new JSONArray();

            JSONObject systemMessage = new JSONObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", "You are an AI assistant who knows everything.");

            JSONObject userMessage = new JSONObject();
            userMessage.put("role", "user");
            userMessage.put("content", "What are the potential concerns with this car model? Model: "
                    + modelText.getText().toString()
                    + ", Year: " + yearText.getText().toString()
                    + ", Transmission: " + transmissionText.getText().toString()
                    + ", Fuel Type: " + fuelText.getText().toString());

            messages.put(systemMessage);
            messages.put(userMessage);

            payload.put("model", "gpt-4o");
            payload.put("messages", messages);
            payload.put("max_tokens", 512);

        } catch (JSONException e) {
            e.printStackTrace();
            concernsText.setText("Error preparing request.");
            return;
        }

        MediaType JSON_TYPE = MediaType.get("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(payload.toString(), JSON_TYPE);

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("API_CALL", "Request failed", e);
                runOnUiThread(() -> concernsText.setText("Failed to fetch analysis."));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    try {
                        JSONObject responseObject = new JSONObject(responseBody);
                        String message = responseObject.getJSONArray("choices")
                                .getJSONObject(0)
                                .getJSONObject("message")
                                .getString("content");
                        runOnUiThread(() -> concernsText.setText(message));
                    } catch (JSONException e) {
                        runOnUiThread(() -> concernsText.setText("Error parsing response."));
                    }
                } else {
                    runOnUiThread(() -> concernsText.setText("Error: " + response.code()));
                }
            }
        });

        btnCancel.setOnClickListener(v -> finish());

        btnConfirm.setOnClickListener(v -> {
            Intent paymentIntent = new Intent(activity_vehicle_info_analysis.this, activity_schedule.class);
            paymentIntent.putExtra("CAR_MODEL",modelText.getText().toString());

            paymentIntent.putExtra("CAR_PRICE", priceText.getText().toString());

            paymentIntent.putExtra("CAR_LOCATION", carLocation);

            paymentIntent.putExtra("CAR_MILEAGE", modelText.getText().toString());

            paymentIntent.putExtra("CAR_YEAR", yearText.getText().toString());

            paymentIntent.putExtra("CAR_TRANSMISSION", transmissionText.getText().toString());

            paymentIntent.putExtra("CAR_FUEL", fuelText.getText().toString());

            paymentIntent.putExtra("CAR_IMAGE", carImageID);
            startActivity(paymentIntent);
            finish();
        });
    }
}
