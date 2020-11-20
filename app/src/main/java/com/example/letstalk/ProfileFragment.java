package com.example.letstalk;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ProfileFragment extends Fragment {

    EditText username , password, confirm_password;
    Button save, cancel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        username = view.findViewById(R.id.usernameprofile);
        password = view.findViewById(R.id.password_profile);
        confirm_password = view.findViewById(R.id.confirmpassword_profile);
        save = view.findViewById(R.id.save);
        cancel = view.findViewById(R.id.cancel);

        save.setOnClickListener(
                V -> {

                }
        );

        cancel.setOnClickListener(
                V -> {

                }
        );

        return view;
    }
}