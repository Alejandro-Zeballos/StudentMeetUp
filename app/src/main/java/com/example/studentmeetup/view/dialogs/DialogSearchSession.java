package com.example.studentmeetup.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.model.Session;
import com.example.studentmeetup.view.FragmentLogin;
import com.example.studentmeetup.view.SessionsAdapter;
import com.example.studentmeetup.viewmodel.ViewModelSessions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class DialogSearchSession extends DialogFragment {

    ViewModelSessions sessionViewModel;
    private EditText editTextTags;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        sessionViewModel = new ViewModelProvider(requireActivity()).get(ViewModelSessions.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_search_session, null);
        editTextTags = view.findViewById(R.id.edit_text_tags);
        //inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                //add action buttons
                .setPositiveButton(R.string.button_search_session, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(editTextTags.getText().toString().equals("")){
                            Toast.makeText(getContext(),getString(R.string.tags_empty_message), Toast.LENGTH_LONG).show();
                        }else{
                            sessionViewModel.setSessionTags(editTextTags.getText().toString());
                            MainActivity.navigateTo(R.id.action_nav_search_session_to_nav_sessions);
                        }


                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DialogSearchSession.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    //method to search user
    private void searchSession(){
        Log.i("DialogSearchSession == ", "Inside dialog search");



    }
}
