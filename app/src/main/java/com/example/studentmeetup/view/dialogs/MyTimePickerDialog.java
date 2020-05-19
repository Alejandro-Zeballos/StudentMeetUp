package com.example.studentmeetup.view.dialogs;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class MyTimePickerDialog {

    Context context;
    TimePickerDialog timeDialog;

    public MyTimePickerDialog(Context context){
        this.context = context;
    }


    public void displayTimeDialog(final EditText timeField){
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog

        timeDialog = new TimePickerDialog(context,android.R.style.Theme_Holo_Dialog,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        timeField.setText(sHour + ":" + sMinute);
                    }

                }, hour, minutes, true);
        timeDialog.show();
    }
}
