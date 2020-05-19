package com.example.studentmeetup.view.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studentmeetup.MainActivity;
import com.example.studentmeetup.R;
import com.example.studentmeetup.view.FragmentMyProfile;
import com.example.studentmeetup.viewmodel.ViewModelSessions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class DialogInputPass extends DialogFragment {

    EditText mEditTextPass;
    NoticeDialogListener listener;


    public interface NoticeDialogListener {
        public void onDialogPositiveClick(String pass);
    }

    public DialogInputPass(){

    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_input_pass, null);
        mEditTextPass = view.findViewById(R.id.edit_text_password);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //get the layout inflater

        //inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(view)
                //add action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i("On Ok click", mEditTextPass.getText().toString());
                        if(mEditTextPass.getText().toString().equals("")){
                            Toast.makeText(getContext(),getString(R.string.tags_empty_message), Toast.LENGTH_LONG).show();
                        }else{
                            // Verify that the host activity implements the callback interface
                            try {
                                Log.i("On try", mEditTextPass.getText().toString());
                                // Instantiate the NoticeDialogListener so we can send events to the host
                                listener.onDialogPositiveClick(mEditTextPass.getText().toString());

                            } catch (ClassCastException e) {
                                // The activity doesn't implement the interface, throw exception
                                throw new ClassCastException(getActivity().toString()
                                        + " must implement NoticeDialogListener");
                            }

                        }


                    }
                })
                .setNegativeButton(R.string.button_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DialogInputPass.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString()
                    + " must implement NoticeDialogListener");
        }
    }
}
