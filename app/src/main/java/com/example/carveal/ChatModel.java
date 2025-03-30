package com.example.carveal;

public class ChatModel {
    int myAvatar;
    String myChat;

    public ChatModel(int myAvatar, String myChat) {
        this.myAvatar = myAvatar;
        this.myChat = myChat;
    }

    public int getMyAvatar() {
        return myAvatar;
    }

    public String getMyChat() {
        return myChat;
    }
}
