package com.example.studentmeetup.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.Reason;
import com.example.studentmeetup.model.Report;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import java.text.SimpleDateFormat;
import java.util.Date;


public class FragmentReportUser extends Fragment {

    private String TAG = "FragmentReportUser :";
    private ViewModelUser userModel;
    private Spinner mSpinnerReason;
    private EditText mEditTextWhy;

    private Button mBtnCancel;
    private Button mBtnReport;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflating fragment
        View view = inflater.inflate(R.layout.fragment_report_user, container, false);

        //Inflating widgets
        mSpinnerReason = view.findViewById(R.id.spinner);
        mEditTextWhy = view.findViewById(R.id.edit_text_why);
        mBtnReport = view.findViewById(R.id.button_report);
        mBtnCancel = view.findViewById(R.id.button_cancel);

        //setting buttons on click Listeners
        mBtnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Report report = createReport();
                Log.i(TAG,report.toString());
            }
        });

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navigateTo(R.id.action_nav_report_user_to_nav_sessions);
            }
        });



        return view;
    }

    private Report createReport(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd 'at' HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        int reporteeId = userModel.getUser().getValue().getId();
        int reportedId = userModel.getProfileUser().getId();
        Report report = new Report.Builder(reporteeId, reportedId)
                .setDate(currentDateandTime)
                .setReason(Reason.valueOf(mSpinnerReason.getSelectedItem().toString()))
                .setReasonDescription(mEditTextWhy.getText().toString())
                .build();
        return report;
    }
}
