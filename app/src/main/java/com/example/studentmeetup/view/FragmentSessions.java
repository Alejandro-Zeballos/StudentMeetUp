package com.example.studentmeetup.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.ApiResponse;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.model.repository.SessionRepository;
import com.example.studentmeetup.viewmodel.ViewModelSessions;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import java.util.List;

import javax.security.auth.callback.Callback;


public class FragmentSessions extends Fragment {

    private static String TAG = "FragmentSessions ";

    private RecyclerView recyclerView;
    private Button mBtnDeleteFilter;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ViewModelSessions sessionViewModel;
    private ViewModelUser userModel;
    private OnRecyclerClick onRecyclerClick;
    private Session sessionSelected;
    private List<Session> sessionList;
    private User user;

    private TextView mTvDescription;
    private TextView mTvDate;
    private TextView mTvTime;
    private TextView mTvPeople;
    private TextView mTvAdmin;
    private TextView mTvTitle;
    private TextView mTvLocation;
    private TextView mTvNoSessionsMessage;
    private ImageView mImgEmpty;
    private Button mBtnJoin;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onRecyclerClick = new OnRecyclerClick() {
            @Override
            public void setClick(int position) {
                sessionSelected = sessionList.get(position);
                Log.i("On FragmentSessions", "Calling setClick() with position" + position);
                setUpSessionPreview();
            }
        };

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sessions, container, false);

        mBtnDeleteFilter = view.findViewById(R.id.btn_delete_filter);
        recyclerView = view.findViewById(R.id.recycler_view_sessions);
        recyclerView.setHasFixedSize(true);

        mTvDescription = view.findViewById(R.id.text_view_description);
        mTvDate = view.findViewById(R.id.text_view_date);
        mTvTime = view.findViewById(R.id.text_view_time);
        mTvPeople = view.findViewById(R.id.text_view_people);
        mTvLocation = view.findViewById(R.id.text_view_location);
        mTvAdmin = view.findViewById(R.id.text_view_admin);
        mTvTitle = view.findViewById(R.id.text_view_title);
        mBtnJoin = view.findViewById(R.id.button_join_session);
        mTvNoSessionsMessage = view.findViewById(R.id.text_view_no_sessions);
        mImgEmpty = view.findViewById(R.id.img_empty_sessions);

        mBtnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionViewModel.joinSession(user, sessionSelected).observe(getViewLifecycleOwner(), new Observer<ApiResponse>() {
                    @Override
                    public void onChanged(ApiResponse apiResponse) {
                        if(apiResponse.isSuccessful()){
                            sessionViewModel.setCurrentSession(sessionSelected);
                            MainActivity.navigateTo(R.id.action_nav_sessions_to_nav_session_room);
                        }
                    }
                });

            }
        });

        mBtnDeleteFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionViewModel.setSessionTags("");
                setUpRecyclerView();
                mBtnDeleteFilter.setVisibility(View.GONE);
            }
        });

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        sessionViewModel = new ViewModelProvider(requireActivity()).get(ViewModelSessions.class);
        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);
        user = userModel.getUser().getValue();


        //getting session list from model

        Log.i(TAG, "onCreateView complete");


        return view;
    }

    @Override
    public void onResume() {

        super.onResume();
        setUpRecyclerView();
        Log.i(TAG, "Inside onResume");


    }

    private void setUpSessionPreview(){
        mTvTitle.setText(sessionSelected.getTitle());
        mTvDate.setText(sessionSelected.getDate());
        mTvTime.setText(sessionSelected.getTime());
        mTvDescription.setText(sessionSelected.getDescription());
        mTvAdmin.setText(sessionSelected.getAdminName());
        mTvLocation.setText(sessionSelected.getLocation());

    }

    private void setUpRecyclerView(){
        String sessionTag = sessionViewModel.getSessionTags();
        if(sessionTag.length()!=0){
            mBtnDeleteFilter.setText(getString(R.string.delete_filter_text)+sessionTag);
            mBtnDeleteFilter.setVisibility(View.VISIBLE);
        }

        //getting session list from model and model from database

        sessionViewModel.getSessionListByTag().observe(getViewLifecycleOwner(), new Observer<List<Session>>() {
            @Override
            public void onChanged(List<Session> sessions) {
                sessionList = sessions;
                adapter = new SessionsAdapter(getContext(),sessionList, sessionViewModel, onRecyclerClick);
                recyclerView.setAdapter(adapter);
                if(sessionSelected==null){
                    try{
                        hideNoSessionMessage();
                        sessionSelected = sessionList.get(0);
                        setUpSessionPreview();
                    }catch (Exception e){
                        e.printStackTrace();
                        showNoSessionMessage();
                        sessionSelected = null;
                    }



                }

            }
        });


    }

    private void showNoSessionMessage(){
        mTvNoSessionsMessage.setVisibility(View.VISIBLE);
        mImgEmpty.setVisibility(View.VISIBLE);
    }
    private void hideNoSessionMessage(){
        mTvNoSessionsMessage.setVisibility(View.INVISIBLE);
        mImgEmpty.setVisibility(View.INVISIBLE);
    }
}
