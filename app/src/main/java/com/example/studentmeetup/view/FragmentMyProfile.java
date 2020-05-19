package com.example.studentmeetup.view;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.helper.MyHelper;
import com.example.studentmeetup.model.ApiResponse;
import com.example.studentmeetup.model.Course;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.view.dialogs.DialogInputPass;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMyProfile extends Fragment implements DialogInputPass.NoticeDialogListener {

    private ImageView mImgEdit;

    private TextView mTvNickname;
    private TextView mTvEventHost;
    private TextView mTvEventJoined;

    private EditText mEtDescription;
    private EditText mEtName;
    private EditText mEtNickname;
    private Spinner mCourse;
    private EditText mEtPassword;
    private TextView mTvEmail;


    private Button mBtnDeleteAccount;
    private Button mBtnBack;
    private User userProfile;
    private ViewModelUser userModel;

    private boolean isBeingModified;

    private String TAG = "FragmentUserProfile: ";

    public FragmentMyProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflating layout
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);

        //getting user view model instance
        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);
        //assigning the viewModel userProfile to local userProfile for easy use
        userProfile = userModel.getProfileUser();


        //Converting widgets to objects(inflating widgets)
        mImgEdit = view.findViewById(R.id.img_edit);
        mTvNickname = view.findViewById(R.id.text_view_nickname);
        mTvEventHost = view.findViewById(R.id.text_view_event_host);
        mTvEventJoined = view.findViewById(R.id.text_view_event_joined);
        mEtDescription = view.findViewById(R.id.edit_text_description);
        mEtName = view.findViewById(R.id.edit_text_name);
        mEtNickname = view.findViewById(R.id.edit_text_nickname);
        mCourse = view.findViewById(R.id.spinner_course);
        mEtPassword = view.findViewById(R.id.edit_text_password);
        mTvEmail = view.findViewById(R.id.text_view_email);

        mBtnDeleteAccount = view.findViewById(R.id.button_delete_account);
        mBtnBack = view.findViewById(R.id.button_back);

        //assigning values to text view fields.(values of profile encountered)
        mTvNickname.setText(userProfile.getNickName());
        mTvEventHost.setText(String.valueOf(userProfile.getSessionsCreated()));
        mTvEventJoined.setText(String.valueOf(userProfile.getSessionsJoined()));
        mEtDescription.setText(userProfile.getDescription());
        mEtName.setText(userProfile.getName());
        mEtNickname.setText(userProfile.getNickName());
        mCourse.setSelection(Course.getIndex(userProfile.getCourse()));
        mEtPassword.setText(userProfile.getPassword());
        mTvEmail.setText(userProfile.getEmail());

        //adding onClickListener to btns

        mImgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateEditFields();
            }
        });

        mBtnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Are you sure?")
                        .setContentText("You won't be able to recover this account!")
                        .setConfirmText("Delete!")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                deleteAccount();
                            }
                        })
                        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
               // MainActivity.navigateTo(R.id.action_nav_user_profile_to_nav_report_user);
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //If the profile is being modified asks if the user wants to save changes
                if(isBeingModified){

                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Applying changes")
                            .setContentText("Do you wish to apply these changes?")
                            .setConfirmText("Yes, Apply them")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    editProfile();
                                    sDialog.dismissWithAnimation();
                                    requireActivity().getOnBackPressedDispatcher().onBackPressed();

                                }
                            })
                            .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    requireActivity().getOnBackPressedDispatcher().onBackPressed();
                                }
                            })
                            .show();

                    //if he doesnt want to modify changes then just go pass to next line and go back to sessions
                }else{
                    //If profile is not being modified then just go back to sessions
                    requireActivity().getOnBackPressedDispatcher().onBackPressed();
                }

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        userProfile = userModel.getProfileUser();
        Log.i(TAG, userProfile.toString());
    }

    //Metodo for deleting account
    public void deleteAccount(){
        userModel.deleteAccount(userProfile).observe(getViewLifecycleOwner(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                if(apiResponse.isSuccessful()){
                    SharedPreferences preferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_file_key), Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear()
                            .commit();
                    Toast.makeText(getContext(),apiResponse.getMessage(), Toast.LENGTH_LONG).show();
                    MainActivity.navigateTo(R.id.action_action_my_profile_to_nav_fragment_login);

                }
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void activateEditFields(){
        mEtDescription.setEnabled(true);
        mEtName.setEnabled(true);
        mEtNickname.setEnabled(true);
        mCourse.setClickable(true);
        isBeingModified = true;
        mBtnBack.setText(getString(R.string.apply_changes));
        mEtPassword.setEnabled(true);
        final Fragment thisFragment = this;
        mEtPassword.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()) {
                    case MotionEvent.ACTION_DOWN :
                        Log.i(TAG, "toching down!!!!");
                        DialogInputPass dialog = new DialogInputPass();
                        dialog.setTargetFragment(thisFragment,0);
                        dialog.show(getParentFragmentManager(),"dialog");
                        break;
                    case MotionEvent.ACTION_UP  :
                        Log.i(TAG, "toching up!!!!");

                        break;

                }
                return true;
            }
        });
    }

    private void editProfile(){
        userProfile.setName(mEtName.getText().toString());
        userProfile.setCourse(Course.valueOf(mCourse.getSelectedItem().toString()));
        userProfile.setNickname(mEtNickname.getText().toString());
        userProfile.setDescription(mEtDescription.getText().toString());
        userProfile.setPassword(mEtPassword.getText().toString());

        Log.i(TAG, "calling editUserProfile with this user: "+userProfile.toString());

        //querying the edit profile to database
        userModel.editUserProfile(userProfile).observe(getViewLifecycleOwner(), new Observer<ApiResponse>() {
            @Override
            public void onChanged(ApiResponse apiResponse) {
                //If response is succesful I'll say so and go back to home
                Log.i(TAG, "Inside editProfile onCHanged called: "+  apiResponse.toString());
                if(apiResponse.isSuccessful()){
                    Toast.makeText(getContext(),apiResponse.getMessage(),Toast.LENGTH_LONG).show();

                }else{
                    //if not i wont go back to home yet
                    Toast.makeText(getContext(),apiResponse.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public void onDialogPositiveClick(String pass) {
        String inputPass = MyHelper.md5(pass);
        Log.i(TAG, "On dialog positive click called, Pasword: " + pass);
        Log.i(TAG, "user pass: "+userProfile.getPassword() + " input pass: "+ inputPass);
        if(inputPass.equals(userProfile.getPassword())){
            Log.i(TAG, "On dialog positive click called, Inside the if statement");
            mEtPassword.setOnTouchListener(null);
            //
            mEtPassword.setEnabled(true);
        }
    }
}
