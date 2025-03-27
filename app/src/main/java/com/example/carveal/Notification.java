package com.example.carveal;

public class Notification {
    private String title;
    private String message;
    private long timestamp;
    private String type;
    private BookingData bookingData;

    public Notification(String title, String message, long timestamp, String type) {
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
        this.bookingData = null;
    }

    public Notification(String title, String message, long timestamp, String type, BookingData bookingData) {
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
        this.bookingData = bookingData;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public boolean hasBookingData() {
        return bookingData != null;
    }

    public BookingData getBookingData() {
        return bookingData;
    }
}
