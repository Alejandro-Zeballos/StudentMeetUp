package com.example.studentmeetup.view;

import android.app.DatePickerDialog;
import android.os.Bundle;
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
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentSearchUser extends Fragment {

    private String TAG = "FragmentSearchUser ";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView mTvUserNotFound;
    private EditText mNickname;
    private Button mBtnSearchUser;
    private Button mBtnBack;
    private ViewModelUser userModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Inflating the layout
        View view = inflater.inflate(R.layout.fragment_search_user, container, false);

        //Inflating widgets
        mTvUserNotFound = view.findViewById(R.id.tv_user_not_found);
        recyclerView = view.findViewById(R.id.recycler_view_profiles);
        recyclerView.setHasFixedSize(true);
        mNickname = view.findViewById(R.id.et_nickname);
        mBtnBack = view.findViewById(R.id.btn_back);
        mBtnSearchUser = view.findViewById(R.id.btn_search_user);

        //Preparing recycler view
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //gettting userModelView instance
        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);

        //adding onclick listener to buttons
        mBtnSearchUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mNickname.getText().length()==0){
                    Toast.makeText(getContext(),getString(R.string.nickname_empty_message), Toast.LENGTH_LONG).show();
                }else{
                    setUpRecyclerView();
                }

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


    private void setUpRecyclerView(){

        mTvUserNotFound.setVisibility(View.GONE);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //getting user list from model and model from database
        userModel.searchUser(mNickname.getText().toString()).observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if(users.size()==0){
                    mTvUserNotFound.setVisibility(View.VISIBLE);
                }
                adapter = new UserAdapter(users, userModel);
                recyclerView.setAdapter(adapter);



            }
        });
    }




}
