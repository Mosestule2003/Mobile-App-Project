package com.example.carveal;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import android.widget.ImageButton;

public class activity_payment extends AppCompatActivity {

    private RadioGroup paymentOptions;
    private CardView selectedCard;
    private TextView cardNumber, cardHolderName;
    private EditText inputCardNumber, inputCardHolder;
    private Button confirmPayment, btnCancel;
    private ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);

        View rootView = findViewById(R.id.main);
        if (rootView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(rootView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        paymentOptions = findViewById(R.id.payment_options);
        selectedCard = findViewById(R.id.selected_card);
        cardNumber = findViewById(R.id.card_number);
        cardHolderName = findViewById(R.id.card_holder_name);
        inputCardNumber = findViewById(R.id.input_card_number);
        inputCardHolder = findViewById(R.id.input_card_holder);
        btnCancel = findViewById(R.id.btnCancel);
        btnBack = findViewById(R.id.back_button);
        confirmPayment = findViewById(R.id.btnConfirm);

        paymentOptions.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radio_mastercard) {
                selectedCard.setVisibility(View.VISIBLE);
                cardNumber.setText("2894 - 4389 - 4432 - 9432");
                cardHolderName.setText("Moses Tule");
                selectedCard.setCardBackgroundColor(getResources().getColor(R.color.primary));
            } else {
                selectedCard.setVisibility(View.GONE);
            }
        });

        confirmPayment.setOnClickListener(v -> {
            Intent confirmIntent = new Intent(activity_payment.this, activity_notification.class);
            startActivity(confirmIntent);
            finish();
        });

        btnCancel.setOnClickListener(v -> finish());

        btnBack.setOnClickListener(v -> {
            Intent backIntent = new Intent(activity_payment.this, activity_confirmation.class);
            startActivity(backIntent);
            finish();
        });
    }
}
