package com.example.studentmeetup.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmeetup.R;
import com.example.studentmeetup.model.User;
import com.example.studentmeetup.viewmodel.ViewModelUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class DialogSearchUser extends DialogFragment {

    private EditText mEtNickname;
    private ViewModelUser userModel;
    private User userFound;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //inflate and set the layout for the dialog
        View view = inflater.inflate(R.layout.dialog_search_user, null);

        //inflate edit text
        mEtNickname = view.findViewById(R.id.edit_text_nickname);

        //getting user view model instance
        userModel = new ViewModelProvider(requireActivity()).get(ViewModelUser.class);



        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                //add action buttons
                .setPositiveButton(R.string.button_search_user, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //todo the logic of searching an user
                        if(mEtNickname.getText().length()==0){
                            Toast.makeText(getContext(),getString(R.string.nickname_empty_message), Toast.LENGTH_LONG).show();
                        }else{
                            searchUser();
                        }

                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DialogSearchUser.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    //todo this method is been called when this dialog is closed already.. i was thinking I dont have to observe it.. I just call it
    //and whenever the next fragment is open it have to check if nickname was modified.. if so it has to try to catch the userProfile
    //I think it could be done in onResume

    private void searchUser(){
        userModel.searchUser(mEtNickname.getText().toString()).observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user==null){
                    Toast.makeText(getContext(), getString(R.string.user_not_found_message),Toast.LENGTH_LONG).show();
                }else{
                    userFound = user;
                }

            }
        });
    }
}
