package com.example.studentmeetup.view;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentmeetup.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSessionRoom extends Fragment {

    private TextView mTvDescription;
    private TextView mTvDate;
    private TextView mTvTime;
    private TextView mTvPeople;
    private TextView mTvAdmin;
    private TextView mTvTitle;
    private Button mBtnLeaveSession;

    private Layout mChatLayout;




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




        return view;
    }
}
