package com.example.studentmeetup.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.studentmeetup.R;


public class FragmentReportUser extends Fragment {


    private Spinner mSpinnerReason;
    private EditText mEditTextWhy;

    private Button mBtnReport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_report_user, container, false);

        mSpinnerReason = view.findViewById(R.id.spinner);
        mEditTextWhy = view.findViewById(R.id.edit_text_why);
        mBtnReport = view.findViewById(R.id.button_report);


        return view;
    }

}
