package com.example.studentmeetup.model.chat;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.studentmeetup.R;
import com.example.studentmeetup.model.chat.models.Message;
import com.example.studentmeetup.model.chat.models.MessageType;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ViewHolder> {

    private String TAG = "ChatRoomAdapter";
    private MessageType messageType;
    private List<Message> messages;

    public ChatRoomAdapter(List<Message> messageList) {
        this.messages = messageList;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i(TAG, messages.get(getItemCount()-1).toString());
        Log.i(TAG, "onCreateViewHolder called viewType= "+viewType);
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the custom layout
        int layoutResource = R.layout.item_info_message;

        switch (viewType){
            case 0:
                layoutResource = R.layout.item_my_message;
                break;
            case 1:
                layoutResource = R.layout.item_message;
                break;
        }
        View sessionView = inflater.inflate(layoutResource, parent, false);

        //return a new holder instance
        ViewHolder viewHolder = new ViewHolder(sessionView);


        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Log.i(TAG, "onBindViewHolder called");

        //Replace the contents of a view (invoked by the layout manager)
        holder.tv_message.setText(messages.get(position).getMessage());
        holder.tv_sender.setText(messages.get(position).getSender());
        holder.tv_time.setText(messages.get(position).getTime());


    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        MessageType type = messages.get(position).getType();
        switch (type){
            case MINE:
                viewType =  0;
                break;
            case RECEIVED:
                viewType = 1;
                break;
                default:
                    viewType = 2;
        }

        return viewType;
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {

        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tv_message;
        public TextView tv_sender;
        public TextView tv_time;
        public MessageType messageType;

        LinearLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);



            Log.i(TAG, "ViewHolder called");

            tv_message = itemView.findViewById(R.id.text_view_message);
            tv_sender = itemView.findViewById(R.id.text_view_sender);
            tv_time = itemView.findViewById(R.id.text_view_time);
            parentLayout = itemView.findViewById(R.id.layout_chat_room);

        }
//        public ViewHolder(View itemView) {
//            super(itemView);
//
//            Log.i(TAG, "ViewHolder called");
//
//            tv_message = itemView.findViewById(R.id.text_view_message);
//            tv_sender = itemView.findViewById(R.id.text_view_sender);
//            tv_time = itemView.findViewById(R.id.text_view_time);
//            parentLayout = itemView.findViewById(R.id.layout_chat_room);
//
//        }


    }


}

