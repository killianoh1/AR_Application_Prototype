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

    /** Title: Get started with Google Analytics
     * Author: Firebase
     * Date: 2020
     * Version: 2.0
     * Availability: https://firebase.google.com/docs/analytics/get-started?platform=android
     */

    private FirebaseAnalytics mFirebaseAnalytics;


    private Button buttonM,buttonB,buttonL;
    private FirebaseAuth mAuth;

    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 7
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=7
     */
    private Toolbar mainToolbar;

    private Button guideB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /** Title: Get started with Google Analytics
         * Author: Firebase
         * Date: 2020
         * Version: 2.0
         * Availability: https://firebase.google.com/docs/analytics/get-started?platform=android
         */

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 5
         * Author: Akshaye JH
         * Date: 2018
         * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=5
         */
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
    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 7
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=7
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 7
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=7
     */


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



            Intent intent = new Intent(this, Ligaments.class);
            startActivity(intent);
            finish();

        }

        public void openBonesActivity () {




            Intent intent = new Intent(this, Bones.class);

            startActivity(intent);

            finish();


        }


        public void openMainActivity () {




            Intent intent = new Intent(this, Muscle.class);
            startActivity(intent);
            finish();



        }


    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 7
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=7
     */
    private void openAccount(){
        Intent intent = new Intent(this, accountSetup.class);
        startActivity(intent);
        finish();

    }
    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 7
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=7
     */

    @Override
    protected void onResume() {

        super.onResume();

        /** Title: Get started with Google Analytics
         * Author: Firebase
         * Date: 2020
         * Version: 2.0
         * Availability: https://firebase.google.com/docs/analytics/get-started?platform=android
         */

        mFirebaseAnalytics.setCurrentScreen(this, getClass().getSimpleName(), "Menu Screen");


    }
    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 7
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=7
     */

    @Override
    protected void onPause() {
        super.onPause();


    }
    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 7
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=7
     */

    @Override
    protected void onRestart() {
        super.onRestart();




    }
}
