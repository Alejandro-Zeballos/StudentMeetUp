package com.example.studentmeetup.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.ApiResponse;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.view.dialogs.MyDatePickerDialog;
import com.example.studentmeetup.view.dialogs.MyTimePickerDialog;
import com.example.studentmeetup.viewmodel.ViewModelSessions;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FragmentNewSession extends Fragment {

    private String TAG = "FragmentNewSession";

    private EditText mEditTextTitle;
    private EditText mEditTextDate;
    private EditText mEditTextTime;
    private EditText mEditTextLocation;
    private EditText mEditTextDescription;
    private EditText mEditTextTags;
    private Button mButtonCreateSession;

    private ViewModelUser userModel;
    private ViewModelSessions sessionModel;
    private LiveData<User> user;
    private MyDatePickerDialog dateDialog;
    private MyTimePickerDialog timeDialog;


    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_session, container, false);

        mEditTextTitle = view.findViewById(R.id.edit_text_title);
        mEditTextDate = view.findViewById(R.id.edit_text_date);
        mEditTextTime = view.findViewById(R.id.edit_text_time);
        mEditTextLocation = view.findViewById(R.id.edit_text_location);
        mEditTextDescription = view.findViewById(R.id.edit_text_description);
        mButtonCreateSession = view.findViewById(R.id.button_create_session);
        mEditTextTags = view.findViewById(R.id.edit_text_tags);

        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);
        sessionModel = new ViewModelProvider(requireActivity()).get(ViewModelSessions.class);


        mButtonCreateSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSession();
            }
        });
        mEditTextDate.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        dateDialog.displayDateDialog(mEditTextDate);
                        break;
                    case MotionEvent.ACTION_UP  :
                        break;

                }
                return true;
            }
        });

        mEditTextTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        timeDialog.displayTimeDialog(mEditTextTime);
                        break;
                    case MotionEvent.ACTION_UP  :
                        break;

                }

                return true;
            }

        });



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        user = userModel.getUser();
        dateDialog = new MyDatePickerDialog(getContext());
        timeDialog = new MyTimePickerDialog(getContext());
    }



    private void createSession(){
        Session session = new Session.Builder(  mEditTextTitle.getText().toString(),
                                                user.getValue().getCourse().toString(),
                                                mEditTextDate.getText().toString(),
                                                mEditTextTime.getText().toString(),
                                                mEditTextLocation.getText().toString(),
                                                user.getValue().getNickName())
                .setDescription(mEditTextDescription.getText().toString())
                .setTags(mEditTextTags.getText().toString())
                .setAdminName(user.getValue().getNickName())
                .setIdAdmin(user.getValue().getId())
                .build();

        Log.i(TAG, session.toString());
        sessionModel.createSession(session).observe(getViewLifecycleOwner(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {

                if(apiResponse.isSuccessful()){
                    new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Done!")
                            .setContentText("The session was created")
                            .show();
                    MainActivity.navigateTo(R.id.action_nav_new_session_to_nav_my_sessions);
                }

            }
        });



    }


}
