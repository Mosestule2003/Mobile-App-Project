package com.example.carveal;

import android.os.Parcel;
import android.os.Parcelable;

public class BookingData implements Parcelable {
    public String date;
    public String time;
    public String location;
    public int totalPrice;
    public long appointmentTime;

    public BookingData(String date, String time, String location, int totalPrice, long appointmentTime) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.totalPrice = totalPrice;
        this.appointmentTime = appointmentTime;
    }

    public BookingData(String date, String time, String location, int totalPrice) {
        this(date, time, location, totalPrice, System.currentTimeMillis() + (30 * 60 * 1000));
    }

    protected BookingData(Parcel in) {
        date = in.readString();
        time = in.readString();
        location = in.readString();
        totalPrice = in.readInt();
        appointmentTime = in.readLong();
    }

    public static final Creator<BookingData> CREATOR = new Creator<BookingData>() {
        @Override
        public BookingData createFromParcel(Parcel in) {
            return new BookingData(in);
        }

        @Override
        public BookingData[] newArray(int size) {
            return new BookingData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(location);
        dest.writeInt(totalPrice);
        dest.writeLong(appointmentTime);
    }

    // NEW GETTERS:
    public String getLocation() {
        return location;
    }

    public long getAppointmentTime() {
        return appointmentTime;
    }
}
