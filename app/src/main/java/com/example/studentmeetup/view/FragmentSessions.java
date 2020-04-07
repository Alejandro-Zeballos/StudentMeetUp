package com.example.studentmeetup.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.studentmeetup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSessions extends Fragment {


    public FragmentSessions() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sessions, container, false);

        return view;
    }

}
