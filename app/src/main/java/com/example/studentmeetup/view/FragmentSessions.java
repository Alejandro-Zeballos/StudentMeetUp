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

import com.example.studentmeetup.R;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.repository.SessionRepository;
import com.example.studentmeetup.viewmodel.ViewModelSessions;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import java.util.List;

import javax.security.auth.callback.Callback;


public class FragmentSessions extends Fragment {

    private static String TAG = "FragmentSessions ";
    public static String SESSION_TAGS = "";

    private RecyclerView recyclerView;
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

        recyclerView = view.findViewById(R.id.recycler_view_sessions);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(getContext());
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
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        //getting session list from model and model from database

        sessionViewModel.getSessionListByTag(SESSION_TAGS).observe(getViewLifecycleOwner(), new Observer<List<Session>>() {
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
