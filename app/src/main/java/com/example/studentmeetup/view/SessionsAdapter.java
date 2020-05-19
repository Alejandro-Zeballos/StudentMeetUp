package com.example.studentmeetup.view;

import android.annotation.SuppressLint;
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
import com.example.studentmeetup.viewmodel.ViewModelSessions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SessionsAdapter extends RecyclerView.Adapter<SessionsAdapter.ViewHolder> {

    String TAG  ="Session Adapter";
    ViewModelSessions sessionModel;
    List<Session> sessionsList;
    int selectedItem;
    OnRecyclerClick onRecyclerClick;
    Context context;

    public SessionsAdapter(Context context, List<Session> sessionsList, ViewModelSessions sessionModel, OnRecyclerClick onRecyclerClick) {
        this.sessionsList = sessionsList;
        this.sessionModel = sessionModel;
        this.onRecyclerClick = onRecyclerClick;
        selectedItem = 0;
        this.context = context;
    }

    public SessionsAdapter(Context context, List<Session> sessionsList, ViewModelSessions sessionModel) {
        this.sessionsList = sessionsList;
        this.sessionModel = sessionModel;
        selectedItem = 0;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the custom layout
        View sessionView = inflater.inflate(R.layout.item_session, parent, false);

        //return a new holder instance
        ViewHolder viewHolder = new ViewHolder(sessionView);


        return viewHolder;
    }

    // Involves populating data into the item through holder
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Session itemSession = sessionsList.get(position);
        Log.i(TAG, "onBindViewHolder called");

        //Replace the contents of a view (invoked by the layout manager)
        holder.titleTextView.setText(itemSession.getTitle());
        holder.locationTextView.setText(itemSession.getLocation());
        holder.courseTextView.setText(itemSession.getCourse());
        holder.dateTextView.setText(itemSession.getDate());

        holder.parentLayout.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        if (selectedItem == position) {
            holder.parentLayout.setCardBackgroundColor(context.getResources().getColor(R.color.colorPrimaryDark));
        }



        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(v.getContext(), "position: " + position  +" id: "+ sessionsList.get(position).getSessionId(), Toast.LENGTH_LONG).show();


                int previousItem = selectedItem;
                selectedItem = position;

                notifyItemChanged(previousItem);
                notifyItemChanged(position);

                onRecyclerClick.setClick(position);

                //MainActivity.navigateTo(R.id.action_nav_sessions_to_nav_session_preview);

            }
        });



    }


    @Override
    public int getItemCount() {
        return sessionsList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder{

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView titleTextView;
        public TextView locationTextView;
        public TextView courseTextView;
        public TextView dateTextView;

        CardView parentLayout;

        public ViewHolder(View itemView){
            super(itemView);

            titleTextView = itemView.findViewById(R.id.text_view_title);
            locationTextView = itemView.findViewById(R.id.text_view_location);
            courseTextView = itemView.findViewById(R.id.text_view_course);
            dateTextView = itemView.findViewById(R.id.text_view_date);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }


    }


}
