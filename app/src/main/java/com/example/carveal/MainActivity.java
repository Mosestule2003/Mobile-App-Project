package com.example.carveal;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout navTab;
    ViewPager2 viewpager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        int tabPosition = intent.getIntExtra("TAB_POSITION", 0);


        navTab = findViewById(R.id.navTab);
        viewpager2 = findViewById(R.id.viewpager2);

        //home tab
        TabLayout.Tab homeTab = navTab.newTab();
        homeTab.setIcon(R.drawable.baseline_home_24);
        navTab.addTab(homeTab);
        //message tab
        TabLayout.Tab messTab = navTab.newTab();
        messTab.setIcon(R.drawable.baseline_chat_24);
        navTab.addTab(messTab);
        //notification tab
        TabLayout.Tab notiTab = navTab.newTab();
        notiTab.setIcon(R.drawable.baseline_circle_notifications_24);
        navTab.addTab(notiTab);
        //setting tab
        TabLayout.Tab setTab = navTab.newTab();
        setTab.setIcon(R.drawable.baseline_density_medium_24);
        navTab.addTab(setTab);

        //set adapter
        FragAdapter fragAdapter = new FragAdapter(getSupportFragmentManager(), getLifecycle());
        viewpager2.setAdapter(fragAdapter);

        viewpager2.setCurrentItem(tabPosition);
        navTab.selectTab(navTab.getTabAt(tabPosition));

        //switching between tabs
        navTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //callback
        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                // This method is called when a page is scrolled
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Select the corresponding tab when a page is selected
                navTab.selectTab(navTab.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                // This method is called when the page scroll state changes
            }
        });

    }
}