package com.example.carveal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    ArrayList<CarModel> carModels = new ArrayList<>();

    int[] imageID = {R.drawable.feature_1, R.drawable.feature_2, R.drawable.feature_3};
    RecyclerView feaRecyView;
    TabLayout listTab;
    ViewPager2 listPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CarThread carThread = new CarThread();
        carThread.start();



        feaRecyView = view.findViewById(R.id.feaRecyView);
        LinearLayoutManager lin = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        feaRecyView.setLayoutManager(lin);

        listTab = view.findViewById(R.id.listTab);
        listPager = view.findViewById(R.id.listPager);

        //all type car
        TabLayout.Tab allType = listTab.newTab();
        allType.setText("All");
        listTab.addTab(allType);
        //coupe
        TabLayout.Tab coupe = listTab.newTab();
        coupe.setText("Coupe");
        listTab.addTab(coupe);
        //hatchback
        TabLayout.Tab suv = listTab.newTab();
        suv.setText("SUV");
        listTab.addTab(suv);
        //pickup
        TabLayout.Tab pickup = listTab.newTab();
        pickup.setText("Pickup Truck");
        listTab.addTab(pickup);

        //set adapter
        CarTypeAdapter carTypeAdapter = new CarTypeAdapter(getChildFragmentManager(), getLifecycle());
        listPager.setAdapter(carTypeAdapter);

        listTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                listPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

    class CarThread extends Thread {

        public void run() {
            String [] models = getResources().getStringArray(R.array.model);
            String [] mileages = getResources().getStringArray(R.array.mileage);
            String [] prices = getResources().getStringArray(R.array.price);

            for (int i = 0; i < models.length; i++) {
                carModels.add(new CarModel(models[i], imageID[i],
                        Integer.parseInt(mileages[i]),
                        Integer.parseInt(prices[i])));
            }

            feaRecyView.post(new Runnable() {
                @Override
                public void run() {
                    CarAdapter adapter = new CarAdapter(getContext(), carModels);
                    feaRecyView.setAdapter(adapter);
                }
            });
        }

    }


}

