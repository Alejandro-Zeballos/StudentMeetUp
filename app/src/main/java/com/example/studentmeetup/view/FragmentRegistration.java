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
import com.example.studentmeetup.helper.MyHelper;
import com.example.studentmeetup.model.ApiResponse;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FragmentRegistration extends Fragment {

    private EditText mEditTextName;
    private EditText mEditTextNickname;
    private EditText mEditTextPass;
    private Spinner mSpinnerCourse;
    private EditText mEditTextRepeatPass;
    private EditText mEditTextDescription;
    private EditText mEditTextEmail;
    private Button mBtnRegister;

    ViewModelUser userModel;

    public FragmentRegistration(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        mEditTextName = view.findViewById(R.id.edit_text_password);
        mEditTextEmail = view.findViewById(R.id.edit_text_email);
        mEditTextNickname = view.findViewById(R.id.edit_text_nickname);
        mEditTextPass = view.findViewById(R.id.edit_text_pass);
        mEditTextRepeatPass = view.findViewById(R.id.edit_text_repeat_pass);
        mEditTextDescription = view.findViewById(R.id.edit_text_description);
        mSpinnerCourse = view.findViewById(R.id.spinner_course);
        mBtnRegister = view.findViewById(R.id.button_register);



        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!mEditTextPass.getText().toString().equals(mEditTextRepeatPass.getText().toString())){
                    Toast.makeText(getContext(), "Password and Repeat Password must match", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!MyHelper.isPassComplex(mEditTextPass.getText().toString())){
                    Toast.makeText(getContext(), "Password is not valid,8 caracheters, At least 1 number, At least 1 uppercase ", Toast.LENGTH_LONG).show();
                return;
                }
                userModel.register(mEditTextEmail.getText().toString(),
                        mEditTextName.getText().toString(),
                        mEditTextNickname.getText().toString(),
                        mEditTextPass.getText().toString(),
                        mEditTextRepeatPass.getText().toString(),
                        mEditTextDescription.getText().toString(),
                        mSpinnerCourse.getSelectedItem().toString())
                .observe(getViewLifecycleOwner(), new Observer<ApiResponse>() {
                    @Override
                    public void onChanged(ApiResponse apiResponse) {
                        Log.i("In the observer", "in RegistrationFragment");
                        if(apiResponse.isSuccessful()){
                            new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Done!")
                                    .setContentText("The account was created")
                                    .show();
                            ((MainActivity)getActivity()).navigateTo(R.id.action_nav_fragment_registration_to_nav_fragment_login);
                        }else{
                            Toast.makeText(getContext(), apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


        return view;
    }
}
