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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.ApiResponse;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.view.dialogs.MyDatePickerDialog;
import com.example.studentmeetup.view.dialogs.MyTimePickerDialog;
import com.example.studentmeetup.viewmodel.ViewModelSessions;

import java.io.Console;
import java.util.regex.Pattern;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class FragmentEditSession extends Fragment {

    private EditText mEditTextTitle;
    private EditText mEditTextDate;
    private EditText mEditTextTime;
    private EditText mEditTextLocation;
    private EditText mEditTextDescription;
    private EditText mEditTextTags;
    private Button mBtnEditSession;
    private NavController navController;
    private MyDatePickerDialog dateDialog;
    private MyTimePickerDialog timeDialog;
    private Session currentSession;
    private ViewModelSessions sessionModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.popBackStack(R.id.nav_my_sessions, false);
                // Handle the back button event
            }
        };

        sessionModel = new ViewModelProvider(requireActivity()).get(ViewModelSessions.class);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_session, container, false);
        //Converting widgets into usable objects
        mEditTextTitle = view.findViewById(R.id.edit_text_title);
        mEditTextDate = view.findViewById(R.id.edit_text_date);
        mEditTextTime = view.findViewById(R.id.edit_text_time);
        mEditTextLocation = view.findViewById(R.id.edit_text_location);
        mEditTextDescription = view.findViewById(R.id.edit_text_description);
        mEditTextTags = view.findViewById(R.id.edit_text_tags);
        mBtnEditSession = view.findViewById(R.id.button_edit_session);

        //Retrieving current session
        currentSession = sessionModel.getCurrentSession().getValue();

        //Changing opening values with current session values
        mEditTextTitle.setText(currentSession.getTitle());
        mEditTextDate.setText(currentSession.getDate());
        mEditTextTime.setText(currentSession.getTime());
        mEditTextLocation.setText(currentSession.getLocation());
        mEditTextDescription.setText(currentSession.getDescription());
        mEditTextTags.setText(currentSession.getTags());

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


        mBtnEditSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating new session object with values changed
                Session updatedSession = currentSession;
                updatedSession.setTitle(mEditTextTitle.getText().toString());
                updatedSession.setDate(mEditTextDate.getText().toString());
                updatedSession.setTime(mEditTextTime.getText().toString());
                updatedSession.setLocation(mEditTextLocation.getText().toString());
                updatedSession.setDescription(mEditTextDescription.getText().toString());
                updatedSession.setTags(mEditTextTags.getText().toString());
                //updating the current session
                sessionModel.updateSession(updatedSession).observe(getViewLifecycleOwner(), new Observer<ApiResponse>() {
                    @Override
                    public void onChanged(ApiResponse apiResponse) {
                        if(apiResponse.isSuccessful()){
                            Toast.makeText(getContext(),getString(R.string.session_updated_message),Toast.LENGTH_LONG).show();
                            MainActivity.navigateTo(R.id.action_nav_edit_session_to_nav_manage_sessions);
                        }else{
                            Log.e("FragmentEditSession == ", apiResponse.getError());
                            Toast.makeText(getContext(),getString(R.string.session_no_updated_message),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        return view;
    }
    private boolean isEmpty(String input){
        if(input.trim().equals("")){
            return true;
        }
        return false;
    }

    private boolean isValidInput(String input){

        if( Pattern.matches("[$&+,:;=?@#|'<>.-^*()%!]", input)){
            return false;
        }

        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        dateDialog = new MyDatePickerDialog(getContext());
        timeDialog = new MyTimePickerDialog(getContext());
    }
}
