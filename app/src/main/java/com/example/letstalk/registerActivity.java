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
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class registerActivity extends AppCompatActivity {
    EditText username, email, password, confirm_password;
    Button register_btn, cancel_btn, login_btn;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    String email_auth , password_auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        username = findViewById(R.id.username_txt);
        email = findViewById(R.id.email_txt);
        password = findViewById(R.id.password_txt);
        confirm_password = findViewById(R.id.confirmpassword_txt);
        register_btn = findViewById(R.id.register_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        login_btn = findViewById(R.id.login_btn);

        firebaseAuth = FirebaseAuth.getInstance();

        //login btn
        login_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(registerActivity.this,loginActivity.class);
                        startActivity(intent);
                    }
                }
        );

        //Register btn
        register_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        String uname = username.getText().toString();
                        String mail  = email.getText().toString();
                        String pword = password.getText().toString();
                        String confirmpword = confirm_password.getText().toString();

                        if(TextUtils.isEmpty(uname) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(pword) || TextUtils.isEmpty(confirmpword)){
                            Toast.makeText(getApplicationContext(),"Fill all required data",Toast.LENGTH_SHORT).show();

                        }else if (pword == confirmpword ){
                            Toast.makeText(getApplicationContext(),"Password are not matching",Toast.LENGTH_SHORT).show();

                        }else if(pword.length() < 6){
                            Toast.makeText(getApplicationContext(),"Password is too short",Toast.LENGTH_SHORT).show();

                        }else {
                            register(uname,mail,pword);
                        }



                    }
                }
        );

        //Cancel btn
        cancel_btn.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(registerActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
        );



    }


    public void register (final String Username, String mail, String password){
        firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    firebaseUser = firebaseAuth.getCurrentUser();
                    String userid;
                    if (firebaseUser != null) {
                        userid = firebaseUser.getUid();


                        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(userid);


                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("id", userid);
                        hashMap.put("Username", Username);
                        hashMap.put("Image", "default");

                        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(registerActivity.this,loginActivity.class);
                                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    } else{
                        Toast.makeText(getApplicationContext(),"check your email",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}