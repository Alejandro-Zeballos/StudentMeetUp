package com.example.studentmeetup.model.chat.models;



public enum MessageType {
    RECEIVED, MINE, INFORMATION;

    private static MessageType type;

    public static void setTypeSelected(MessageType type){
        type = type;
    }

    public static MessageType getTypeSelected(){
        return type;
    }

}
