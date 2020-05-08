package com.example.studentmeetup.view;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.viewmodel.ViewModelSessions;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class FragmentSessionPreview extends Fragment {

    private TextView mTvDescription;
    private TextView mTvDate;
    private TextView mTvTime;
    private TextView mTvPeople;
    private TextView mTvAdmin;
    private TextView mTvTitle;
    private Button mBtnJoin;

    private ViewModelSessions sessionViewModel;
    private Session session;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_session_preview, container, false);

        mTvDescription = view.findViewById(R.id.text_view_description);
        mTvDate = view.findViewById(R.id.text_view_date);
        mTvTime = view.findViewById(R.id.text_view_time);
        mTvPeople = view.findViewById(R.id.text_view_people);
        mTvAdmin = view.findViewById(R.id.text_view_admin);
        mTvTitle = view.findViewById(R.id.text_view_title);
        mBtnJoin = view.findViewById(R.id.button_join_session);


        sessionViewModel = new ViewModelProvider(requireActivity()).get(ViewModelSessions.class);
        session = sessionViewModel.getCurrentSession().getValue();

        mTvTitle.setText(session.getTitle());
        mTvDate.setText(session.getDate());
        mTvTime.setText(session.getTime());
        mTvPeople.setText("1");
        //todo fix this number of people in session
        mTvAdmin.setText(session.getAdminName());
        mTvDescription.setText(session.getDescription());

        mBtnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.navigateTo(R.id.action_nav_session_preview_to_nav_session_room);
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
