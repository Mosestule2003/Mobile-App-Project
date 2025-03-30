package com.example.carveal;

public class CarModel {
    String model;
    int image, mileage, price;

    public CarModel(String model, int image, int mileage, int price) {
        this.model = model;
        this.image = image;
        this.mileage = mileage;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public int getImage() {
        return image;
    }

    public int getMileage() {
        return mileage;
    }

    public int getPrice() {
        return price;
    }
}
