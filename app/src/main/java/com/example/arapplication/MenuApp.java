package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuApp extends AppCompatActivity {

    private Button buttonM,buttonB,buttonL;
    private FirebaseAuth mAuth;
    private Toolbar mainToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Anatomy Insight");



        mAuth = FirebaseAuth.getInstance();

        buttonM = (Button)findViewById(R.id.musclesBtn);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        buttonB = findViewById(R.id.bonesBtn);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBonesActivity();
            }
        });

        buttonL = findViewById(R.id.ligamentsBtn);
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLigamentsActivity();
            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {

            Intent Loginintent = new Intent(MenuApp.this, LoginAcitivity.class);
            startActivity(Loginintent);
            finish();

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout_btn:
                logOut();
                return true;

            default:
                return false;
        }
    }

    private void logOut() {

        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {

        Intent loginIntent = new Intent(MenuApp.this, LoginAcitivity.class);
        startActivity(loginIntent);
        finish();

    }


    public void openLigamentsActivity(){
        Intent intent = new Intent(this, ligaments.class);
        startActivity(intent);
        finish();

    }

    public void openBonesActivity(){
        Intent intent =  new Intent(this, bones.class);
        startActivity(intent);
        finish();

    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }



}
