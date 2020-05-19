package com.example.studentmeetup.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentmeetup.R;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.viewmodel.ViewModelSessions;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentMySessions extends Fragment {

    private static String TAG = "FragmentMySessions ";

    private RecyclerView recyclerViewJoined;
    private RecyclerView recyclerViewAdmin;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManagerJoined;
    private RecyclerView.LayoutManager layoutManagerAdmin;

    private TextView mNoSessionsJoinedMessage;
    private TextView mNoCreatedSessionsMessage;
    private ImageView mImgEmptyJoined;
    private ImageView mImgEmptyAdmin;

    private ViewModelSessions sessionViewModel;
    private ViewModelUser userViewModel;
    private User user;
    private List<Session> sessionAdminList;
    private List<Session> sessionJoinedList;
    private List<Session> sessionList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionViewModel = new ViewModelProvider(requireActivity()).get(ViewModelSessions.class);
        userViewModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_sessions, container, false);

        //inflating recycler view joined and empty message
        recyclerViewJoined = view.findViewById(R.id.recycler_view_my_sessions);
        mNoSessionsJoinedMessage = view.findViewById(R.id.edit_text_no_joined_sessions);
        mImgEmptyJoined = view.findViewById(R.id.img_empty_joined);
        recyclerViewJoined.setHasFixedSize(true);

        //inflating recycler view admin and empty message
        recyclerViewAdmin = view.findViewById(R.id.recycler_view_sessions_created);
        mNoCreatedSessionsMessage = view.findViewById(R.id.edit_text_no_sessions);
        mImgEmptyAdmin = view.findViewById(R.id.img_empty_admin);
        recyclerViewAdmin.setHasFixedSize(true);

        //assigning horizontal layout manager to recycler views
        layoutManagerJoined = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        layoutManagerAdmin = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewJoined.setLayoutManager(layoutManagerJoined);
        recyclerViewAdmin.setLayoutManager(layoutManagerAdmin);

        //Initializing Session lists
        sessionAdminList = new ArrayList<>();
        sessionJoinedList = new ArrayList<>();

        //retrieving user from userViewModel
        user = userViewModel.getUser().getValue();

        loadSessions();


        Log.i(TAG, "onCreateView complete");



        return view;
    }

    @Override
    public void onResume() {

        super.onResume();

        user = userViewModel.getUser().getValue();



        Log.i(TAG, "Inside onResume");


    }

    //this method will only query getSessionsListbyId and populate adminList or joinedList
    private void loadSessions(){


        sessionViewModel.getSessionListById( user.getId() ).observe(getViewLifecycleOwner(), new Observer<List<Session>>() {
            @Override
            public void onChanged(List<Session> sessions) {

                Log.i(TAG, "onLoad Sessions");
                Log.i(TAG, "sessions size: " + sessions.size());



                for(Session session: sessions ){
                    if(session.getAdminId() == user.getId()){
                        //If the user is the admin of this session
                        sessionAdminList.add(session);
                    }else{
                        sessionJoinedList.add(session);
                    }
                }

                //sestting up admin recycler view
                setUpRecyclerView(recyclerViewAdmin, mNoCreatedSessionsMessage, mImgEmptyAdmin, sessionAdminList);
                //setting up joined recycler view
                setUpRecyclerView(recyclerViewJoined, mNoSessionsJoinedMessage, mImgEmptyJoined, sessionJoinedList);
            }
        });
    }

    private void setUpRecyclerView(RecyclerView recyclerView, TextView emptyMessage, ImageView emptyImage, List<Session> sessionList){

        //getting session list from model and model from database
        Log.i(TAG, "inside setUpRecyclerView");

        if(sessionList.size() != 0){

            adapter = new MySessionsAdapter(getContext(),sessionList, sessionViewModel);
            recyclerView.setAdapter(adapter);
            emptyImage.setVisibility(View.INVISIBLE);
            emptyMessage.setVisibility(View.INVISIBLE);

        }else{
            Log.i(TAG, "making message: "+emptyMessage.getText().toString() + " Visible");
            emptyMessage.setVisibility(View.VISIBLE);
            emptyImage.setVisibility(View.VISIBLE);
        }





    }
}
