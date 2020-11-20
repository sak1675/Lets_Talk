package com.example.letstalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class helpActivity extends AppCompatActivity {

    Button resetbtn, unableloginbtn, changeusrnamebtn, feedbackbtn;
    EditText resetemailtxt;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        resetbtn = findViewById(R.id.resetbtn);
        unableloginbtn = findViewById(R.id.noaccountbtn);
        changeusrnamebtn = findViewById(R.id.changeusrnamebtn);
        feedbackbtn = findViewById(R.id.feedbackbtn);
        resetemailtxt = findViewById(R.id.resetemail);

        firebaseAuth = FirebaseAuth.getInstance();

        resetbtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        String email = resetemailtxt.getText().toString();

                        if (email.equals("")) {
                            Toast.makeText(getApplicationContext(),"Enter email for reset email",Toast.LENGTH_SHORT).show();

                        }else{
                            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){

                                        Toast.makeText(getApplicationContext(),"Reset email is sent. Check your email",Toast.LENGTH_SHORT).show();

                                    }else{

                                        String error = task.getException().getMessage();
                                        Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }
                    }
                }
        );

        unableloginbtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(helpActivity.this,registerActivity.class);
                        startActivity(intent);


                    }
                }
        );

        changeusrnamebtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){

                    }
                }
        );

        feedbackbtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){

                    }
                }
        );

    }
}