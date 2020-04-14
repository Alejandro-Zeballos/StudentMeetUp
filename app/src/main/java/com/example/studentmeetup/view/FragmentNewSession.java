package com.example.studentmeetup.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.ApiResponse;
import com.example.studentmeetup.model.Course;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.viewmodel.ViewModelSessions;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import java.util.Observable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class FragmentNewSession extends Fragment {

    private String TAG = "FragmentNewSession";

    private EditText mEditTextTitle;
    private EditText mEditTextDate;
    private EditText mEditTextTime;
    private Spinner mSpinnerCourse;
    private EditText mEditTextLocation;
    private EditText mEditTextDescription;
    private EditText mEditTextTags;
    private Button mButtonCreateSession;

    private ViewModelUser userModel;
    private ViewModelSessions sessionModel;
    private LiveData<User> user;

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
        mEditTextTags = view.findViewById(R.id.edit_text_tags);

        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);
        sessionModel = new ViewModelProvider(requireActivity()).get(ViewModelSessions.class);

        user = userModel.getUser();

        mButtonCreateSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSession();
            }
        });


        return view;
    }

    private void createSession(){
        Session session = new Session.Builder(  mEditTextTitle.getText().toString(),
                                                mSpinnerCourse.getSelectedItem().toString(),
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
                Toast.makeText(getContext(), apiResponse.getMessage(), Toast.LENGTH_LONG).show();
                if(apiResponse.isSuccessful()){
                    MainActivity.navigateTo(R.id.action_nav_new_session_to_nav_my_sessions);
                }

            }
        });



    }


}
