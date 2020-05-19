package com.example.studentmeetup.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.studentmeetup.R;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.viewmodel.ViewModelSessions;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentProfileList extends Fragment {

    private String TAG = "FragmentSessions ";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView mTvUserNotFound;
    private ViewModelSessions sessionViewModel;
    private ViewModelUser userModel;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profiles_list, container, false);

        mTvUserNotFound = view.findViewById(R.id.tv_user_not_found);
        recyclerView = view.findViewById(R.id.recycler_view_profiles);
        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);


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
        String sessionTag = sessionViewModel.getSessionTags();
        if(sessionTag.length()!=0){

        }

        //getting user list from model and model from database





    }

}
