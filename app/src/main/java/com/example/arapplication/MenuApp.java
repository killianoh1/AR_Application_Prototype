package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;


import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuApp extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    private Button buttonM,buttonB,buttonL;
    private FirebaseAuth mAuth;
    private Toolbar mainToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Anatomy Insight");



        mAuth = FirebaseAuth.getInstance();

        buttonM = (Button)findViewById(R.id.musclesBtn);
        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "muscleBtn" );
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Muscle");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Button");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                openMainActivity();
            }
        });

        buttonB = findViewById(R.id.bonesBtn);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "bonesBtn" );
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Bones");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Button");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                openBonesActivity();
            }
        });

        buttonL = findViewById(R.id.ligamentsBtn);
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ligamentsBtn" );
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Ligaments");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Button");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

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

            case R.id.sendAccount:
                openAccount();
                return true;

            default:
                return false;


        }

    }


    


    public void openLigamentsActivity(){
        Intent intent = new Intent(this, Ligaments.class);
        startActivity(intent);
        finish();

    }

    public void openBonesActivity(){
        Intent intent =  new Intent(this, Bones.class);
        startActivity(intent);
        finish();

    }


    public void openMainActivity(){
        Intent intent = new Intent(this, Muscle.class);
        startActivity(intent);
        finish();
    }

    private void openAccount(){
        Intent intent = new Intent(this, accountSetup.class);
        startActivity(intent);
        finish();

    }



}
