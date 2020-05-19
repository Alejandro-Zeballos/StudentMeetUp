package com.example.studentmeetup.view.dialogs;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import androidx.annotation.NonNull;

public class MyDatePickerDialog {

    Context context;
    DatePickerDialog dialog;

    public MyDatePickerDialog(Context context){
        this.context = context;
    }

    public void displayDateDialog(final EditText field){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        dialog = new DatePickerDialog(context,android.R.style.Theme_Holo_Dialog,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        field.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }

                }, year, month, day);
        dialog.show();
    }
}
