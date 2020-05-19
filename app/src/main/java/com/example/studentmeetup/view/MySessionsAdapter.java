package com.example.studentmeetup.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.viewmodel.ViewModelSessions;

import java.util.List;

import androidx.annotation.NonNull;



public class MySessionsAdapter extends SessionsAdapter{



    public MySessionsAdapter(Context context, List<Session> sessionsList, ViewModelSessions sessionModel) {
        super(context, sessionsList, sessionModel);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Session itemSession = sessionsList.get(position);
        Log.i(TAG, "onBindViewHolder called");

        //Replace the contents of a view (invoked by the layout manager)
        holder.titleTextView.setText(itemSession.getTitle());
        holder.locationTextView.setText(itemSession.getLocation());
        holder.courseTextView.setText(itemSession.getCourse());
        holder.dateTextView.setText(itemSession.getDate());

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "position: " + position  +" id: "+ sessionsList.get(position).getSessionId(), Toast.LENGTH_LONG).show();

                sessionModel.setCurrentSession(sessionsList.get(position));

                MainActivity.navigateTo(R.id.action_nav_my_sessions_to_nav_session_room);

            }
        });
    }
}
