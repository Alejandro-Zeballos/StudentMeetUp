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

import com.example.studentmeetup.R;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.viewmodel.ViewModelSessions;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentSessionRoom extends Fragment {

    private TextView mTvDescription;
    private TextView mTvDate;
    private TextView mTvTime;
    private TextView mTvPeople;
    private TextView mTvAdmin;
    private TextView mTvTitle;
    private Button mBtnLeaveSession;

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
        mTvPeople = view.findViewById(R.id.text_view_people);
        mTvAdmin = view.findViewById(R.id.text_view_admin);
        mTvTitle = view.findViewById(R.id.text_view_title);
        mBtnLeaveSession = view.findViewById(R.id.button_leave_session);



        mTvDescription.setText(session.getDescription());
        mTvDate.setText(session.getDate());
        mTvTime.setText(session.getTime());
        mTvTitle.setText(session.getTitle());
        mTvPeople.setText("");
        mTvAdmin.setText(session.getAdminName());




        return view;
    }

    public String getSessionId(){
        return String.valueOf(this.session.getSessionId());
    }

    public String getUserNickname(){
        return this.user.getNickName();
    }
}
