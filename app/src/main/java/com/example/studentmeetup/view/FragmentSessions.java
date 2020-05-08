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

import com.example.studentmeetup.R;
import com.example.studentmeetup.model.Session;
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sessions, container, false);

        mBtnDeleteFilter = view.findViewById(R.id.btn_delete_filter);
        recyclerView = view.findViewById(R.id.recycler_view_sessions);
        recyclerView.setHasFixedSize(true);


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
        //sessionViewModel = new ViewModelSessions();

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

                Log.i(TAG, "onCHanged on setUpRecyclerVIew");
                Log.i(TAG, "sessions size: " + sessions.size());
                adapter = new SessionsAdapter(sessions, sessionViewModel);
                recyclerView.setAdapter(adapter);
            }
        });


    }

}
