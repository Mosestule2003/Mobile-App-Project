package com.example.carveal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {
    Context context;
    ArrayList<CarModel> carModels;

    public CarAdapter(Context context, ArrayList<CarModel> CarModels) {
        this.context = context;
        this.carModels = CarModels;
    }

    @NonNull
    @Override
    //giving a look to the rows
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fea_car_item, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    //assigning values to the views
    //based on the position of the RecycleView
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.model.setText(carModels.get(position).getModel());
        holder.photo.setImageResource(carModels.get(position).getImage());
        holder.mileage.setText(carModels.get(position).getMileage() + " km");
        holder.price.setText("$ " + carModels.get(position).getPrice());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CarDetail.class);
                intent.putExtra("CAR_MODEL", carModels.get(position).getModel());

                intent.putExtra("CAR_PRICE", "$" + carModels.get(position).getPrice());

                intent.putExtra("CAR_LOCATION", carModels.get(position).getLocation());

                intent.putExtra("CAR_MILEAGE", carModels.get(position).getMileage() + " km");

                intent.putExtra("CAR_YEAR", carModels.get(position).getYear());

                intent.putExtra("CAR_TRANSMISSION", carModels.get(position).getTransmission());

                intent.putExtra("CAR_FUEL", carModels.get(position).getFuel());
                intent.putExtra("CAR_IMAGE", carModels.get(position).getImage());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    //total number of items
    public int getItemCount() {
        return carModels.size();
    }

    //inner class
    public static class CarViewHolder extends RecyclerView.ViewHolder{
        //grabbing the views from Row layout file
        //kinda like onCreate method
        ImageView photo;
        TextView model, price, mileage;
        CardView card;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.cardView);
            photo = itemView.findViewById(R.id.carImage);
            mileage = itemView.findViewById(R.id.mileage);
            price = itemView.findViewById(R.id.price);
            model = itemView.findViewById(R.id.model);
        }
    }
}