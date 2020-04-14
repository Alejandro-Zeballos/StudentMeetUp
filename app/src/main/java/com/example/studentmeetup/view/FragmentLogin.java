package com.example.studentmeetup.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.viewmodel.ViewModelUser;


public class FragmentLogin extends Fragment {


    public static final String MESSAGE_ID = "This is an Id message";

    private EditText mEditTextUserName;
    private EditText mEditTextPass;
    private Button mButtonLogin;
    private Button mButtonRegister;

    private ViewModelUser userModel;


    public FragmentLogin() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mEditTextUserName = view.findViewById(R.id.edit_text_email);
        mEditTextPass = view.findViewById(R.id.edit_text_password);
        mButtonLogin = view.findViewById(R.id.button_login);
        mButtonRegister = view.findViewById(R.id.button_register);


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userModel.login(mEditTextUserName.getText().toString().trim(),
                        mEditTextPass.getText().toString().trim())
                        .observe(getViewLifecycleOwner(), new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        Log.i("In the on CHanged", "in fragmentLogin");
                        if(user!= null){
                            Log.i("In the on CHanged", "Calling LoadMainActivity with");
                            Log.i("In the on CHanged", user.toString());
                            loadMainActivity();
                        }else{
                            Log.i("In the on CHanged", "trowin user null");
                        }
                    }
                });

            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).navigateTo(R.id.action_nav_fragment_login_to_nav_fragment_registration);

            }
        });


        return view;
    }



    private void loadMainActivity(){

        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        ((MainActivity)getActivity()).navigateTo(R.id.action_nav_fragment_login_to_nav_sessions);
    }






}
