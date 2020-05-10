package com.example.arapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 4
 * Author: Akshaye JH
 * Date: 2018
 * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=4
 */
public class RegisterActivity extends AppCompatActivity {

    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 4
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=4
     */

    private EditText reg_email_field;
    private EditText reg_pass_field;
    private EditText reg_confirm_pass_field;
    private Button reg_btn;
    private Button reg_login_btn;

    private ProgressBar reg_progress;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 4
         * Author: Akshaye JH
         * Date: 2018
         * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=4
         */

        mAuth = FirebaseAuth.getInstance();

        reg_email_field = findViewById(R.id.reg_email);
        reg_pass_field = findViewById(R.id.reg_password);
        reg_confirm_pass_field = findViewById(R.id.reg_confirm_password);
        reg_btn = findViewById(R.id.reg_btn);
        reg_login_btn = findViewById(R.id.login_reg_btn);

        reg_progress = findViewById(R.id.reg_progress);

        reg_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendToLogin();
            }
        });

        /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 4
         * Author: Akshaye JH
         * Date: 2018
         * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=4
         */

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = reg_email_field.getText().toString();
                String pass = reg_pass_field.getText().toString();
                String confirm_pass = reg_confirm_pass_field.getText().toString();

                /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 4
                 * Author: Akshaye JH
                 * Date: 2018
                 * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=4
                 */

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) & !TextUtils.isEmpty(confirm_pass)){
                    reg_progress.setVisibility(View.VISIBLE);

                    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 4
                     * Author: Akshaye JH
                     * Date: 2018
                     * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=4
                     */

                    if(pass.equals(confirm_pass)){


                        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 4
                                 * Author: Akshaye JH
                                 * Date: 2018
                                 * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=4
                                 */


                                if(task.isSuccessful()){

                                    Intent setupIntent = new Intent(RegisterActivity.this, accountSetup.class);
                                    startActivity(setupIntent);
                                    finish();

                                } else {

                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(RegisterActivity.this, "Error : " + errorMessage, Toast.LENGTH_LONG).show();

                                }

                            }
                        });
                    } else {

                        Toast.makeText(RegisterActivity.this, "Confirm Password and Password Field doesn't match.", Toast.LENGTH_LONG).show();

                    }
                }
                else{
                    Toast.makeText(RegisterActivity.this, "Enter All Fields", Toast.LENGTH_LONG).show();
                }


            }
        });


    }

    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 4
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=4
     */

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            sendToMain();

        }

    }

    /** Title: Android Blog App 2018 - Android Studio Firebase Tutorials - Part 4
     * Author: Akshaye JH
     * Date: 2018
     * Availability: https://www.youtube.com/watch?v=WN4Xec0bNmo&list=PLGCjwl1RrtcR4ptHvrc_PQIxDBB5MGiJA&index=4
     */

    private void sendToMain() {

        Intent mainIntent = new Intent(RegisterActivity.this, MenuApp.class);
        startActivity(mainIntent);
        finish();

    }

    private void sendToLogin(){

        Intent mainIntent = new Intent(RegisterActivity.this, LoginAcitivity.class);
        startActivity(mainIntent);
        finish();

    }


}
