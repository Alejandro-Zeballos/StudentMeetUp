package com.example.studentmeetup.model.chat.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private String message;
    private String sender;
    private String time;
    private MessageType type;

    public Message(String message, String sender, MessageType type) {
        this.message = message;
        this.sender = sender;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentTime = sdf.format(new Date());
        this.time = currentTime;
        this.type = type;

    }

    @Override
    public String toString(){
        return "message: "+message+" sender:" + sender+ " Type: "+type;
    }



    public String getMessage() {
        return message;
    }
     public String getTime(){
        return this.time;
     }

    public MessageType getType() {
        return type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
