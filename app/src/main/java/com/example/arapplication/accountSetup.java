package com.example.arapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

/** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 5
 * Author: Akshaye JH
 * Date: 2018
 * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=5
 */

public class accountSetup extends AppCompatActivity {


    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 5
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=5
     */

    private Toolbar mainToolbar;
    private FirebaseAuth firebaseAuth;
    private EditText setupName;
    private EditText setupSchool;
    private EditText setupYear;
    private Button setup_Button;

    private GoogleSignInClient mGoogleSignInClient;

    private FirebaseFirestore firebaseFirestore;

    private String user_id;


    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 5
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=5
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);



        /** Title: Login With Google Using Firebase In Android App | Google Login In Android
         * Author: EasyLearn
         * Date: 2019
         * Availability: https://www.youtube.com/watch?v=E1eqRNTZqDM
         */

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 5
         * Author: Akshaye JH
         * Date: 2018
         * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=5
         */

        firebaseAuth = FirebaseAuth.getInstance();

        user_id = firebaseAuth.getCurrentUser().getUid();

        firebaseFirestore = FirebaseFirestore.getInstance();

        mainToolbar = findViewById(R.id.main_toolbar);

        setSupportActionBar(mainToolbar);

        getSupportActionBar().setTitle("Account");

        setupName = findViewById(R.id.setup_Name);

        setupYear = findViewById(R.id.setup_School_Year);

        setupSchool = findViewById(R.id.setup_School);

        setup_Button = findViewById(R.id.setup_Button);

/** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 5
 * Author: Akshaye JH
 * Date: 2018
 * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=5
 */
        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                       String name = task.getResult().getString("name");
                       String school = task.getResult().getString("School");
                       String school_year = task.getResult().getString("School Year");

                       setupName.setText(name);
                       setupSchool.setText(school);
                       setupYear.setText(school_year);


                    }

                }
                else{

                    String error = task.getException().getMessage();
                    Toast.makeText(accountSetup.this, "Firestore retrieve error", Toast.LENGTH_SHORT).show();

                }

            }
        });

/** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 5
 * Author: Akshaye JH
 * Date: 2018
 * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=5
 */
        setup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user_name = setupName.getText().toString();
                String user_school = setupSchool.getText().toString();
                String user_school_year = setupYear.getText().toString();

                if(!TextUtils.isEmpty(user_name) && !TextUtils.isEmpty(user_school) && !TextUtils.isEmpty(user_school_year) ){

                    user_id = firebaseAuth.getCurrentUser().getUid();

                    Map<String, String> userMap = new HashMap<>();
                    userMap.put("name", user_name);
                    userMap.put("School", user_school);
                    userMap.put("School Year", user_school_year);

                    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 5
                     * Author: Akshaye JH
                     * Date: 2018
                     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=5
                     */

                    firebaseFirestore.collection("Users").document(user_id).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(accountSetup.this, "Account updated", Toast.LENGTH_SHORT).show();
                                Intent menuintent = new Intent(accountSetup.this, MenuApp.class);
                                startActivity(menuintent);
                                finish();

                            }
                            else{

                                String error = task.getException().getMessage();
                                Toast.makeText(accountSetup.this, "Firestore error", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });




                }
                else{

                    Toast.makeText(accountSetup.this, "Enter all fields", Toast.LENGTH_SHORT).show();

                }

            }
        });






    }

    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 5
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=sDf7NKROoDM&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=5
     */

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {

            Intent Loginintent = new Intent(this, LoginAcitivity.class);
            startActivity(Loginintent);
            finish();

        }

    }
    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 3
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=YfoTdnI3IKs&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=3
     */

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.account_menu, menu);
        return true;

    }

    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 3
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=YfoTdnI3IKs&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=3
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout_btn:
                logOut();
                return true;

            case R.id.accountToMenu:
                sendtoMenu();
                return true;

            default:
                return false;


        }

    }
    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 3
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=YfoTdnI3IKs&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=3
     */

    private void logOut() {

        firebaseAuth.signOut();

        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        sendToLogin();
                    }
                });

        sendToLogin();
    }

    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 3
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=YfoTdnI3IKs&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=3
     */

    private void sendToLogin() {

        Intent loginIntent = new Intent(this, LoginAcitivity.class);
        startActivity(loginIntent);
        finish();

    }


    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 3
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=YfoTdnI3IKs&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=3
     */

    private void sendtoMenu(){
        Intent loginIntent = new Intent(this, MenuApp.class);
        startActivity(loginIntent);
        finish();

    }


}
