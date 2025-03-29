package com.example.carveal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Loading_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_page);

        VideoView videoView = findViewById(R.id.videoView);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.logo_animation);
        videoView.setVideoURI(videoUri);

        videoView.start();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Loading_page.this, sign_in_page.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}
