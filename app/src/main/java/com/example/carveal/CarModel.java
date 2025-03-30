package com.example.carveal;

public class CarModel {
    String model, year, fuel, transmission, location;
    int image, mileage, price;

    public CarModel(String model, String year, String fuel, String transmission, String location,
                    int image, int mileage, int price) {
        this.model = model;
        this.year = year;
        this.fuel = fuel;
        this.transmission = transmission;
        this.location = location;
        this.image = image;
        this.mileage = mileage;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getFuel() {
        return fuel;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getLocation() {
        return location;
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
