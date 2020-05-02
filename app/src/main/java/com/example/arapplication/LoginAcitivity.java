package com.example.arapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.sql.Timestamp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginAcitivity extends AppCompatActivity {




    private FirebaseAnalytics mFirebaseAnalytics;

    private String dateAndTime;


    private EditText loginEmailText;
    private EditText loginPassText;
    private Button loginBtn;
    private Button loginRegBtn;

    private FirebaseAuth mAuth;

    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acitivity);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        mAuth = FirebaseAuth.getInstance();

        loginEmailText = findViewById(R.id.reg_email);
        loginPassText = findViewById(R.id.reg_confirm_password);
        loginBtn = findViewById(R.id.reg_btn);
        loginRegBtn = findViewById(R.id.login_reg_btn);

        loginProgress = findViewById(R.id.reg_progress);

        loginRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regIntent = new Intent(LoginAcitivity.this, RegisterActivity.class);
                startActivity(regIntent);

            }
        });



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = loginEmailText.getText().toString();
                String loginPass = loginPassText.getText().toString();


                if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)) {
                    loginProgress.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {

                                Date date = new Date();

                                long time = date.getTime();

                                Timestamp timestamp = new Timestamp(time);

                                Bundle params = new Bundle();
                                params.putString(FirebaseAnalytics.Param.METHOD, "email");
                                params.putString("timestamp", timestamp.toString());
                                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);

                                Toast.makeText(LoginAcitivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                sendToMain();

                            } else {

                                String errorMessage = task.getException().getMessage();
                                Toast.makeText(LoginAcitivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();


                            }


                        }
                    });


                }else{
                    Toast.makeText(LoginAcitivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }






    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {

            sendToMain();

        }
    }

    private void sendToMain() {

        Intent mainIntent = new Intent(LoginAcitivity.this, MenuApp.class);
        startActivity(mainIntent);
        finish();


    }

    }

