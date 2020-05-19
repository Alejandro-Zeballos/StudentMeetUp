package com.example.studentmeetup.view;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.viewmodel.ViewModelSessions;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private String TAG  ="Session Adapter";
    private ViewModelUser userModel;
    private List<User> userList;

    public UserAdapter(List<User> userList, ViewModelUser userModel){
        this.userList = userList;
        this.userModel = userModel;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the custom layout
        View sessionView = inflater.inflate(R.layout.item_profile, parent, false);

        //return a new holder instance
        ViewHolder viewHolder = new ViewHolder(sessionView);


        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Log.i(TAG, "onBindViewHolder called");

        //Replace the contents of a view (invoked by the layout manager)
        holder.tv_nickname.setText(userList.get(position).getNickName());
        holder.tv_description.setText(userList.get(position).getDescription());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userModel.setProfileSelected(userList.get(position));
                Log.i(TAG, userList.get(position).toString());
                MainActivity.navigateTo(R.id.action_nav_search_user_to_nav_user_profile);

            }
        });



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder{

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tv_nickname;
        public TextView tv_description;


        LinearLayout parentLayout;

        public ViewHolder(View itemView){
            super(itemView);

            tv_nickname = itemView.findViewById(R.id.item_profile_nickname);
            tv_description = itemView.findViewById(R.id.item_profile_description);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }


    }


}
