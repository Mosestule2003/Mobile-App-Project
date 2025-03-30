package com.example.carveal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllFragment#} factory method to
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment {
     RecyclerView listRecyView;
     ArrayList<CarModel> carModels = new ArrayList<>();
     int[] imageID = {R.drawable.feature_1, R.drawable.feature_2, R.drawable.feature_3};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        AllFragment.CarListThread listThread = new AllFragment.CarListThread();
        listThread.start();

        listRecyView = view.findViewById(R.id.listRecyView);
        LinearLayoutManager listLin = new LinearLayoutManager(getContext());
        listRecyView.setLayoutManager(listLin);

        return view;
    }

    class CarListThread extends Thread {
        public void run() {
            String[] models = getResources().getStringArray(R.array.model);
            String[] mileages = getResources().getStringArray(R.array.mileage);
            String[] prices = getResources().getStringArray(R.array.price);

            for (int i = 0; i < models.length; i++) {
                carModels.add(new CarModel(models[i], imageID[i],
                        Integer.parseInt(mileages[i]),
                        Integer.parseInt(prices[i])));
            }
            listRecyView.post(new Runnable() {
                @Override
                public void run() {
                    CarListAdapter adapter = new CarListAdapter(getContext(), carModels);
                    listRecyView.setAdapter(adapter);
                }
            });
        }
    }
}