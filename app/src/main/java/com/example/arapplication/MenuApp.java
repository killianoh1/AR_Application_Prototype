package com.example.arapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.google.ar.sceneform.FrameTime;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.sql.Timestamp;
import java.util.Date;

public class MenuApp extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    private int Camera_Permission_code = 1;

    private Button buttonM,buttonB,buttonL;
    private FirebaseAuth mAuth;
    private Toolbar mainToolbar;

    private Button guideB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mainToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Anatomy Insight");

        guideB = findViewById(R.id.guideBtn);



        mAuth = FirebaseAuth.getInstance();

        buttonM = (Button)findViewById(R.id.musclesBtn);

        guideB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSlider();
            }
        });



        buttonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();

                long time = date.getTime();

                Timestamp timestamp = new Timestamp(time);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "musclesBtn" );
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Muscles");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Button");
                bundle.putString(FirebaseAnalytics.Param.METHOD, timestamp.toString());
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

              openMainActivity();


            }
        });

        buttonB = findViewById(R.id.bonesBtn);
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();

                long time = date.getTime();

                Timestamp timestamp = new Timestamp(time);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "bonesBtn" );
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Bones");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Button");
                bundle.putString(FirebaseAnalytics.Param.METHOD, timestamp.toString());
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


                openBonesActivity();
            }
        });

        buttonL = findViewById(R.id.ligamentsBtn);
        buttonL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = new Date();

                long time = date.getTime();

                Timestamp timestamp = new Timestamp(time);
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ligamentsBtn" );
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Ligaments");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Button");
                bundle.putString(FirebaseAnalytics.Param.METHOD, timestamp.toString());
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);



                openLigamentsActivity();
            }
        });


    }

    private void openSlider() {

        Intent intent = new Intent(this, SliderActivity.class);
        startActivity(intent);
        finish();

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




        public void openLigamentsActivity () {


            Date date = new Date();

            long time = date.getTime();

            Timestamp timestamp = new Timestamp(time);

            Bundle params = new Bundle();
            params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Ligaments model");
            params.putString(FirebaseAnalytics.Param.METHOD, timestamp.toString());
            //params.putString("timestamp", timestamp.toString());
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);

            Intent intent = new Intent(this, Ligaments.class);
            startActivity(intent);
            finish();

        }

        public void openBonesActivity () {


            Date date = new Date();

            long time = date.getTime();

            Timestamp timestamp = new Timestamp(time);

            Bundle params = new Bundle();
            params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Bones model");
            params.putString(FirebaseAnalytics.Param.METHOD, timestamp.toString());
            //params.putString("timestamp", timestamp.toString());
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);


            Intent intent = new Intent(this, Bones.class);

            startActivity(intent);

            finish();


        }


        public void openMainActivity () {




            Date date = new Date();

            long time = date.getTime();

            Timestamp timestamp = new Timestamp(time);

            Bundle params = new Bundle();
            params.putString(FirebaseAnalytics.Param.ITEM_NAME, "Muscle model");
            params.putString(FirebaseAnalytics.Param.METHOD, timestamp.toString());
            //params.putString("timestamp", timestamp.toString());
            mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, params);

            Intent intent = new Intent(this, Muscle.class);
            startActivity(intent);
            finish();



        }



    private void openAccount(){
        Intent intent = new Intent(this, accountSetup.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onResume() {

        super.onResume();

        //finish();
        //startActivity(getIntent());

    }

    @Override
    protected void onPause() {
        super.onPause();


    }


    @Override
    protected void onRestart() {
        super.onRestart();

        //Intent previewMessage = new Intent(StampiiStore.this, StampiiStore.class);


    }
}
