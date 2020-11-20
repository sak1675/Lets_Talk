package com.example.letstalk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    EditText login_email, login_password;
    Button login_btn, cancel_btn, register_btn, help_here;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        login_email = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_login_btn);
        cancel_btn = findViewById(R.id.login_cancel_btn);
        register_btn = findViewById(R.id.login_register_btn);
        help_here = findViewById(R.id.help_here);

        //register_btn
        register_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(loginActivity.this,registerActivity.class);
                        startActivity(intent);

                    }
                }
        );

        //login_btn
        login_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        String mail = login_email.getText().toString();
                        String pword = login_password.getText().toString();

                        if(TextUtils.isEmpty(mail) || TextUtils.isEmpty(pword)){
                            Toast.makeText(getApplicationContext(), "Fill all required data", Toast.LENGTH_SHORT).show();
                        }else {
                            firebaseAuth.signInWithEmailAndPassword(mail,pword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(loginActivity.this, postActivity.class);
                                        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(),"login fail",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }

                    }
                }
        );

        //cancel_btn
        cancel_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(loginActivity.this,MainActivity.class);
                        startActivity(intent);

                    }
                }
        );

        //help_here_btn
        help_here.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(loginActivity.this,helpActivity.class);
                        startActivity(intent);

                    }
                }
        );

    }
}