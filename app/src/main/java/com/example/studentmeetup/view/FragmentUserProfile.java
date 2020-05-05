package com.example.studentmeetup.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.viewmodel.ViewModelUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentUserProfile extends Fragment {


    private TextView mTvNickname;
    private TextView mTvDescription;
    private TextView mTvEventHost;
    private TextView mTvEventJoined;
    private Button mBtnReport;
    private Button mBtnBack;
    private User userProfile;
    private ViewModelUser userModel;

    public FragmentUserProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflating layout
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);


        //Converting widgets to objects(inflating widgets)
        mTvNickname = view.findViewById(R.id.text_view_nickname);
        mTvDescription = view.findViewById(R.id.text_view_description);
        mTvEventHost = view.findViewById(R.id.text_view_event_host);
        mTvEventJoined = view.findViewById(R.id.text_view_event_joined);
        mBtnReport = view.findViewById(R.id.button_report);
        mBtnBack = view.findViewById(R.id.button_back);

        //getting user view model instance
        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);
        //assigning the viewModel userProfile to local userProfile for easy use
        userProfile = userModel.getProfileUser();

        //assigning values to text view fields.(values of profile encountered)
        mTvNickname.setText(userProfile.getNickName());
        mTvDescription.setText(userProfile.getDescription());
        mTvEventHost.setText(userProfile.getSessionsCreated());
        mTvEventJoined.setText(userProfile.getSessionsJoined());

        //adding onClickListener to btns
        mBtnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navigateTo(R.id.action_nav_user_profile_to_nav_report_user);
            }
        });

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navigateTo(R.id.action_nav_report_user_to_nav_sessions);
            }
        });

        return view;
    }

}
