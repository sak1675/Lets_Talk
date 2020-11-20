package com.example.letstalk;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class AddpostFragment extends Fragment {
    EditText status;
    ImageButton clear;
    Button post;
    DatabaseReference databaseReference;
    Integer post_no = new Random().nextInt();
    String postname = Integer.toString(post_no);

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_addpost, container, false);

        status = view.findViewById(R.id.status);
        clear = view.findViewById(R.id.clearbtn);
        post = view.findViewById(R.id.postbtn);



        post.setOnClickListener(
                v -> {
                    if (status.getText().toString().equals("") ) {
                        Toast.makeText(getContext(), "No Data", Toast.LENGTH_SHORT).show();

                    } else{
                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Posts").child("post"+post_no);
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange( DataSnapshot snapshot) {
                                snapshot.getRef().child("status").setValue(status.getText().toString());
                                snapshot.getRef().child("postName").setValue(postname);



                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                // Create a new Fragment to be placed in the activity layout
                                Fragment fragment = new HomeFragment();
                                // Add the fragment to the 'fragment_container' Layout present in Activity
                                fragmentTransaction.replace(R.id.fragment_container, fragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();

                                Toast.makeText(getContext(),"post Added",Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                }
        );

        clear.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        status.setText("");
                    }
                }
        );


        return view;
    }
}