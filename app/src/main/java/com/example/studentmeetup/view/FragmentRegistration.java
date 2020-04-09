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

public class FragmentRegistration extends Fragment {

    private EditText mEditTextTitle;
    private EditText mEditTextNickname;
    private EditText mEditTextPass;
    private Spinner mSpinnerCourse;
    private EditText mEditTextRepeatPass;
    private EditText mEditTextDescription;
    private Button mBtnRegister;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        mEditTextTitle = view.findViewById(R.id.edit_text_title);
        mEditTextNickname = view.findViewById(R.id.edit_text_nickname);
        mEditTextPass = view.findViewById(R.id.edit_text_password);
        mEditTextRepeatPass = view.findViewById(R.id.edit_text_repeat_pass);
        mEditTextDescription = view.findViewById(R.id.edit_text_description);
        mSpinnerCourse = view.findViewById(R.id.spinner_course);
        mBtnRegister = view.findViewById(R.id.button_register);


        return view;
    }
}
