package com.example.studentmeetup.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

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
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        sessionViewModel = new ViewModelProvider(requireActivity()).get(ViewModelSessions.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        //inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_search_session, null))
                //add action buttons
                .setPositiveButton(R.string.button_search_session, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        searchSession();

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
