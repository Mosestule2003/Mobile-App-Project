package com.example.carveal;

public class MessageModel {
    String name, lastMessage, time;
    int avatar;

    public MessageModel(String name, String lastMessage, String time, int avatar) {
        this.name = name;
        this.avatar = avatar;
        this.lastMessage = lastMessage;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getTime() {
        return time;
    }

    public int getAvatar() {
        return avatar;
    }
}
