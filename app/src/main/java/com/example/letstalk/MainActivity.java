package com.example.letstalk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Button loginbtn , registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //check if user is logged in or not
        if(firebaseUser != null){
            Intent intent = new Intent( MainActivity.this, postActivity.class);
            startActivity(intent);

        }

        loginbtn = findViewById(R.id.login);
        registerbtn = findViewById(R.id.register);

        loginbtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View V){
                        Intent intent = new Intent(MainActivity.this , loginActivity.class);
                        startActivity(intent);
                    }
                }
        );

        registerbtn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View V){
                        Intent intent = new Intent(MainActivity.this , registerActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}