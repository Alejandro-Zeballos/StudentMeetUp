package com.example.studentmeetup.model;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {

    public static String getDate(){
        SimpleDateFormat d = new SimpleDateFormat("dd.MM.yyyy");
        return d.format(new Date());
    }

    public static String getTime(){
        SimpleDateFormat d = new SimpleDateFormat("HH.MM.ss");
        return d.format(new Date());
    }


}
