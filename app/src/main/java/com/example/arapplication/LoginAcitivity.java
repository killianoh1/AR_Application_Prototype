package com.example.arapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.sql.Timestamp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginAcitivity extends AppCompatActivity {

    /** Title: Login With Google Using Firebase In Android App | Google Login In Android
     * Author: EasyLearn
     * Date: 2019
     * Availability: https://www.youtube.com/watch?v=E1eqRNTZqDM
     */

    private SignInButton mGoogleBtn;
    private static final int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;

    private FirebaseAnalytics mFirebaseAnalytics;

    private String dateAndTime;

    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 2
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=2
     */

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

        /** Title: Login With Google Using Firebase In Android App | Google Login In Android
         * Author: EasyLearn
         * Date: 2019
         * Availability: https://www.youtube.com/watch?v=E1eqRNTZqDM
         */

        mGoogleBtn = findViewById(R.id.googleBtn);


        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 2
         * Author: Akshaye JH
         * Date: 2018
         * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=2
         */

        mAuth = FirebaseAuth.getInstance();

        loginEmailText = findViewById(R.id.reg_email);
        loginPassText = findViewById(R.id.reg_confirm_password);
        loginBtn = findViewById(R.id.reg_btn);
        loginRegBtn = findViewById(R.id.login_reg_btn);

        loginProgress = findViewById(R.id.reg_progress);

        /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 2
         * Author: Akshaye JH
         * Date: 2018
         * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=2
         */

        loginRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent regIntent = new Intent(LoginAcitivity.this, RegisterActivity.class);
                startActivity(regIntent);

            }
        });
        /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 2
         * Author: Akshaye JH
         * Date: 2018
         * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=2
         */


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = loginEmailText.getText().toString();
                String loginPass = loginPassText.getText().toString();

                /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 2
                 * Author: Akshaye JH
                 * Date: 2018
                 * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=2
                 */


                if (!TextUtils.isEmpty(loginEmail) && !TextUtils.isEmpty(loginPass)) {
                    loginProgress.setVisibility(View.VISIBLE);

                    mAuth.signInWithEmailAndPassword(loginEmail, loginPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 2
                             * Author: Akshaye JH
                             * Date: 2018
                             * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=2
                             */
                            if (task.isSuccessful()) {




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



    /** Title: Login With Google Using Firebase In Android App | Google Login In Android
     * Author: EasyLearn
     * Date: 2019
     * Availability: https://www.youtube.com/watch?v=E1eqRNTZqDM
     */


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    /** Title: Login With Google Using Firebase In Android App | Google Login In Android
     * Author: EasyLearn
     * Date: 2019
     * Availability: https://www.youtube.com/watch?v=E1eqRNTZqDM
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }

    /** Title: Login With Google Using Firebase In Android App | Google Login In Android
     * Author: EasyLearn
     * Date: 2019
     * Availability: https://www.youtube.com/watch?v=E1eqRNTZqDM
     */

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);

            Toast.makeText(this, "Google account signed in", Toast.LENGTH_SHORT).show();
            FirebaseGoogleAuth(acc);

        } catch (ApiException e){
            FirebaseGoogleAuth(null);
            Toast.makeText(this, "Google account not signed in", Toast.LENGTH_SHORT).show();

        }

    }

    /** Title: Login With Google Using Firebase In Android App | Google Login In Android
     * Author: EasyLearn
     * Date: 2019
     * Availability: https://www.youtube.com/watch?v=E1eqRNTZqDM
     */

    private void FirebaseGoogleAuth(GoogleSignInAccount acct){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Toast.makeText(LoginAcitivity.this, "Success", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = mAuth.getCurrentUser();

                    updateUI(user);
                }
                else {
                    Toast.makeText(LoginAcitivity.this, "Fail", Toast.LENGTH_SHORT).show();

                    updateUI(null);
                }
            }
        });

    }

    /** Title: Login With Google Using Firebase In Android App | Google Login In Android
     * Author: EasyLearn
     * Date: 2019
     * Availability: https://www.youtube.com/watch?v=E1eqRNTZqDM
     */

    private void updateUI(FirebaseUser fUser){
        sendToMain();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (account != null){

            String personEmail = account.getEmail();

            Toast.makeText(this, personEmail, Toast.LENGTH_SHORT).show();
        }
    }



    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 2
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=2
     */

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser != null) {

            sendToMain();

        }
    }

    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 2
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=2
     */

    private void sendToMain() {

        Intent mainIntent = new Intent(LoginAcitivity.this, MenuApp.class);
        startActivity(mainIntent);
        finish();


    }

    }

