package com.example.studentmeetup.model;

public enum Course {
    IT,
    Busisness;

    public static int getIndex(Course c){
        if(c == IT){
            return 0;
        }
        return 1;
    }
}
