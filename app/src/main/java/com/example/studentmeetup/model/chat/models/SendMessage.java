package com.example.studentmeetup.model.chat.models;

public class SendMessage {

    private String userName;
    private String messageContent;
    private String roomName;

    public SendMessage(String userName, String content, String roomName) {
        this.userName = userName;
        this.messageContent = content;
        this.roomName = roomName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return messageContent;
    }

    public void setContent(String content) {
        this.messageContent = content;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
