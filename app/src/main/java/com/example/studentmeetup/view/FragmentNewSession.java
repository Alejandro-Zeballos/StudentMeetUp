package com.example.studentmeetup.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.studentmeetup.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentNewSession extends Fragment {

    private EditText mEditTextTitle;
    private EditText mEditTextDate;
    private EditText mEditTextTime;
    private Spinner mSpinnerCourse;
    private EditText mEditTextLocation;
    private EditText mEditTextDescription;
    private Button mButtonCreateSession;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_session, container, false);

        mEditTextTitle = view.findViewById(R.id.edit_text_title);
        mEditTextDate = view.findViewById(R.id.edit_text_date);
        mEditTextTime = view.findViewById(R.id.edit_text_time);
        mEditTextLocation = view.findViewById(R.id.edit_text_location);
        mEditTextDescription = view.findViewById(R.id.edit_text_description);
        mSpinnerCourse = view.findViewById(R.id.spinner_course);
        mButtonCreateSession = view.findViewById(R.id.button_create_session);


        return view;
    }
}
