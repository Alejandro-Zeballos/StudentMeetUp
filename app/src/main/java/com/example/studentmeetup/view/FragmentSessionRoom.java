package com.example.studentmeetup.view;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.ApiResponse;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.viewmodel.ViewModelSessions;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class FragmentSessionRoom extends Fragment {

    private TextView mTvDescription;
    private TextView mTvDate;
    private TextView mTvTime;
    private TextView mTvAdmin;
    private TextView mTvTitle;
    private TextView mTvLocation;
    private Button mBtnLeaveSession;
    private Button mBtnDeleteSession;
    private Button mBtnEditSession;

    private String TAG= "FragmetSessionRoom";

    private Layout mChatLayout;
    ViewModelSessions sessionModel;
    ViewModelUser userModel;
    Session session;
    User user;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionModel = new ViewModelProvider(requireActivity()).get(ViewModelSessions.class);
        session = sessionModel.getCurrentSession().getValue();
        Log.i(TAG, "Session = " + session.toString());

        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);
        user = userModel.getUser().getValue();
        Log.i(TAG, "user = " + user.toString());



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_session_room, container, false);

        mTvDescription = view.findViewById(R.id.text_view_description);
        mTvDate = view.findViewById(R.id.text_view_date);
        mTvTime = view.findViewById(R.id.text_view_time);
        mTvAdmin = view.findViewById(R.id.text_view_admin);
        mTvTitle = view.findViewById(R.id.text_view_title);
        mTvLocation = view.findViewById(R.id.text_view_location);
        mBtnLeaveSession = view.findViewById(R.id.button_leave_session);
        mBtnDeleteSession = view.findViewById(R.id.button_delete_session);
        mBtnEditSession = view.findViewById(R.id.button_edit);


        //Assigning data to widgets from session
        mTvDescription.setText(session.getDescription());
        mTvDate.setText(session.getDate());
        mTvTime.setText(session.getTime());
        mTvTitle.setText(session.getTitle());
        mTvAdmin.setText(session.getAdminName());
        mTvLocation.setText(session.getLocation());

        //if session admin = user id display admin buttons (edit, delete) and hide user button (leave)
        if(session.getAdminId() == user.getId()){
            displayAdminButtons();
        }else{
            displayUserButtons();
        }


        //Setting up buttons listeners
        mBtnLeaveSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionModel.leaveSession(user, session).observe(getViewLifecycleOwner(), new Observer<ApiResponse>() {
                    @Override
                    public void onChanged(ApiResponse apiResponse) {
                        if(apiResponse.isSuccessful()){
                            Toast.makeText(getContext(), getString(R.string.sesion_leaved_message), Toast.LENGTH_LONG).show();
                            requireActivity().getOnBackPressedDispatcher().onBackPressed();
                        }
                    }
                });
            }
        });

        mBtnEditSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navigateTo(R.id.action_nav_session_room_to_nav_edit_session);
            }
        });

        mBtnDeleteSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Deleting session")
                        .setContentText("Do you wish to delete this session?")
                        .setConfirmText("Yes, Delete it")

                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(final SweetAlertDialog sDialog) {
                                //on acceptance
                                sessionModel.deleteSession(session).observe(getViewLifecycleOwner(), new Observer<ApiResponse>() {
                                    @Override
                                    public void onChanged(ApiResponse apiResponse) {
                                        if(apiResponse.isSuccessful()){
                                            new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                                    .setTitleText("Done!")
                                                    .setContentText("The session was deleted")
                                                    .show();
                                            sDialog.dismiss();
                                            //Toast.makeText(getContext(), getString(R.string.session_deleted_message), Toast.LENGTH_LONG).show();
                                            MainActivity.navigateTo(R.id.action_nav_session_room_to_nav_sessions);

                                        }
                                    }
                                });

                            }
                        })
                        .setCancelButton("No", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();


            }
        });




        return view;
    }

    private void displayAdminButtons() {
        mBtnLeaveSession.setVisibility(View.INVISIBLE);
        mBtnEditSession.setVisibility(View.VISIBLE);
        mBtnDeleteSession.setVisibility(View.VISIBLE);
    }

    private void displayUserButtons() {
        mBtnLeaveSession.setVisibility(View.VISIBLE);
        mBtnEditSession.setVisibility(View.GONE);
        mBtnDeleteSession.setVisibility(View.GONE);
    }

    public String getSessionId(){
        return String.valueOf(this.session.getSessionId());
    }

    public String getUserNickname(){
        return this.user.getNickName();
    }


}
