package com.example.studentmeetup.model.chat.models;

public class Data {
    private String userName;
    private String roomName;

    public Data(String userName, String chatId){
        this.userName = userName;
        this.roomName = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChatId() {
        return roomName;
    }

    public void setChatId(String chatId) {
        this.roomName = chatId;
    }
}
