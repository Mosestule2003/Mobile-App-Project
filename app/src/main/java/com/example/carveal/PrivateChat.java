package com.example.carveal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PrivateChat extends AppCompatActivity {
    ImageView avatar;
    TextView nameView;
    TextView lastMessage;
    TextView timeView;
    Button back, send;
    EditText input;
    ArrayList<ChatModel> chatModels;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_private_chat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String message = intent.getStringExtra("last");
        String time = intent.getStringExtra("time");
        int image = intent.getIntExtra("avatar", 0);

        int myAvatar = R.drawable.avatar3;

        avatar = findViewById(R.id.avatar);
        nameView = findViewById(R.id.name);
        chatModels = new ArrayList<>();
        //lastMessage = findViewById(R.id.last);
        //timeView = findViewById(R.id.time);

        back = findViewById(R.id.back);
        send = findViewById(R.id.send);
        input = findViewById(R.id.input);
        recyclerView = findViewById(R.id.messBody);

        chatModels.add(new ChatModel(myAvatar, message));
        ChatAdapter adapter = new ChatAdapter(getApplicationContext(), chatModels);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager lin = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        lin.setStackFromEnd(true);
        recyclerView.setLayoutManager(lin);

        avatar.setImageResource(image);
        nameView.setText(name);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                i.putExtra("TAB_POSITION", 1);
                v.getContext().startActivity(i);
            }
        });



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newMessage;
                newMessage = input.getText().toString().trim();
                if (!newMessage.isEmpty()) {
                    chatModels.add(new ChatModel(myAvatar, newMessage));
                    recyclerView.setAdapter(adapter);
                    input.setText("");
                }
            }
        });

    }
}
