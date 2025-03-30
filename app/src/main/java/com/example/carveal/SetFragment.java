package com.example.carveal;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SetFragment#} factory method to
 * create an instance of this fragment.
 */
public class SetFragment extends Fragment {

    EditText usernameInput, phoneNumberInput, emailInput,addressInput;
    CheckBox notificationsCheckbox;
    Button saveButton;
    SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_set, container, false);

        usernameInput = view.findViewById(R.id.usernameInput);
        phoneNumberInput = view.findViewById(R.id.phoneInput);
        emailInput = view.findViewById(R.id.emailInput);
        addressInput = view.findViewById(R.id.addressInput);
        notificationsCheckbox = view.findViewById(R.id.notificationsCheckbox);
        saveButton = view.findViewById(R.id.saveButton);

        sharedPreferences = requireActivity().getSharedPreferences("AppSettings", getContext().MODE_PRIVATE);

        usernameInput.setText(sharedPreferences.getString("username", "Admin"));
        phoneNumberInput.setText(sharedPreferences.getString("phoneNumber", "123-456-7890"));
        emailInput.setText(sharedPreferences.getString("email", "admin@example.com"));
        addressInput.setText(sharedPreferences.getString("address", "123 Main St, Kamloops, BC"));

        notificationsCheckbox.setChecked(sharedPreferences.getBoolean("notifications", true));


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", usernameInput.getText().toString().trim());
                editor.putString("phoneNumber", phoneNumberInput.getText().toString().trim());
                editor.putString("email", emailInput.getText().toString().trim());
                editor.putString("address", addressInput.getText().toString().trim());
                editor.putBoolean("notifications", notificationsCheckbox.isChecked());
                editor.apply();
            }
        });

        return view;
    }
}