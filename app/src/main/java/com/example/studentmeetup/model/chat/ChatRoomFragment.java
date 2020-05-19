package com.example.studentmeetup.model.chat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.studentmeetup.R;
import com.example.studentmeetup.model.chat.models.Data;
import com.example.studentmeetup.model.chat.models.Message;
import com.example.studentmeetup.model.chat.models.MessageType;
import com.example.studentmeetup.model.chat.models.SendMessage;
import com.example.studentmeetup.view.FragmentSessionRoom;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class ChatRoomFragment extends Fragment {

    RecyclerView recyclerView;
    EditText editTextMessage;
    Button sendButton;
    Socket socket;
    String userName;
    String roomName;
    String TAG = "CHatRoomFragment";
    List<Message> messagesList;
    ChatRoomAdapter chatRoomAdapter;


    Gson gson;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.chat_room_fragment, container, false);

        try{

            socket = IO.socket("https://studentmeetup.herokuapp.com/");
        }catch(Exception e){
            e.printStackTrace();
            Log.i("fail", "Fail to connect");
        }

        socket.connect();

        //Register all the listeners and callback here
        socket.on(Socket.EVENT_CONNECT, onConnect);
        socket.on("newUserToChatRoom", onNewUser); // To know if the new user entered the room.
        socket.on("updateChat", onUpdateChat); // To update if someone send a message to chatroom
        socket.on("userLeftChatRoom", onUserLeft); // To know if the user left the chatroom.

        recyclerView = view.findViewById(R.id.recycler_view_chat);
        editTextMessage = view.findViewById(R.id.edit_text_message);
        sendButton = view.findViewById(R.id.button_send);

        messagesList = new ArrayList<>();
         gson = new Gson();

        userName = ((FragmentSessionRoom)getParentFragment()).getUserNickname();
        roomName = ((FragmentSessionRoom)getParentFragment()).getSessionId();

        try{
            Bundle bundle = getArguments();
            userName =
            roomName = bundle.getString("chatID");
        }catch(Exception e){
            e.printStackTrace();
        }

        chatRoomAdapter = new ChatRoomAdapter(messagesList);
        recyclerView.setAdapter(chatRoomAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        //trying to connect to chat


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });




        return view;
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            Data data = new Data(userName, roomName);
            String jsonData = gson.toJson(data);
            socket.emit("subscribe", jsonData);
        }
    };

    private Emitter.Listener onNewUser = new Emitter.Listener() {
        @Override
        public void call(Object... args) {


            String name;
            try{
                name = (String)args[0];
                Message userJoined = new Message("User Joined!!", name, MessageType.INFORMATION);
                Log.i("onNewUser", "User Joined!!" + name);
                addItemToRecyclerView(userJoined);
            }catch(Exception e){
                Log.i("error in onNewUser", "error in onNewUser");
                e.printStackTrace();
            }
        }
    };

    private Emitter.Listener onUserLeft = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            String name;
            try{
                name = (String)args[0];
                Message userLeft = new Message("User Left!!", name, MessageType.INFORMATION);
                Log.i("onUserLeft", "User left!!" + name);
                addItemToRecyclerView(userLeft);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    };

    private Emitter.Listener onUpdateChat = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            String data = null;
            Log.i("In on updatechat", "Lets see");

            try{
                data = (String)args[0];
                Log.i("New message receibed", ": " + data);
            }catch(Exception e){
                e.printStackTrace();
            }
            SendMessage packet = gson.fromJson(data, SendMessage.class);
            Message newMessage = new Message(packet.getContent(), packet.getUserName(), MessageType.RECEIVED);

            addItemToRecyclerView(newMessage);
        }
    };

    private void sendMessage(){
        String content = editTextMessage.getText().toString();
        SendMessage sendData = new SendMessage(userName, content, roomName);
        String jsonMessage = gson.toJson(sendData);
        socket.emit("newMessage", jsonMessage);
        editTextMessage.setText("");
        Message sending = new Message(content, userName, MessageType.MINE);
        editTextMessage.setText("");
        addItemToRecyclerView(sending);
    }

    //Since this function is inside of the listener,
    //You need to do it on UIThread!
    private void addItemToRecyclerView(final Message messsage){
        chatRoomAdapter.setMessageType(messsage.getType());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                messagesList.add(messsage);
                chatRoomAdapter.notifyItemInserted(messagesList.size());

                recyclerView.scrollToPosition(messagesList.size() - 1);

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Data data = new Data(userName, roomName);
        String jsonData = gson.toJson(data);

        //Before disconnecting, send "unsubscribe" event to server so that
        //server can send "userLeftChatRoom" event to other users in chatroom
        socket.emit("unsubscribe", jsonData);
        socket.disconnect();
    }
}
