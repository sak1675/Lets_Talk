package com.example.letstalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class postActivity extends AppCompatActivity {
    ImageButton homebtn, searchbtn, addpostbtn, profilebtn;
    TextView usr_name;

    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        usr_name = findViewById(R.id.usr_name);
        homebtn = findViewById(R.id.homebtn);
        searchbtn = findViewById(R.id.searchbtn);
        addpostbtn = findViewById(R.id.add_postbtn);
        profilebtn = findViewById(R.id.profile_btn);


        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(firebaseUser.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Create a new Fragment to be placed in the activity layout
        Fragment fragment = new HomeFragment();
        // Add the fragment to the 'fragment_container' Layout present in Activity
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();


        homebtn.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public  void onClick (View v){
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Create a new Fragment to be placed in the activity layout
                        Fragment fragment = new HomeFragment();
                        // Add the fragment to the 'fragment_container' Layout present in Activity
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();

                    }
                }
        );
        searchbtn.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public  void onClick (View v){
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Create a new Fragment to be placed in the activity layout
                        Fragment fragment = new SearchFragment();
                        // Add the fragment to the 'fragment_container' Layout present in Activity
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );
        addpostbtn.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public  void onClick (View v){
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Create a new Fragment to be placed in the activity layout
                        Fragment fragment = new AddpostFragment();
                        // Add the fragment to the 'fragment_container' Layout present in Activity
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );
        profilebtn.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public  void onClick (View v){
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        // Create a new Fragment to be placed in the activity layout
                        Fragment fragment = new ProfileFragment();
                        // Add the fragment to the 'fragment_container' Layout present in Activity
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }
        );
    }
}