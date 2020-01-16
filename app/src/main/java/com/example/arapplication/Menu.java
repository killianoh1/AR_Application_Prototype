package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button buttonM,buttonB,buttonL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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

    public void openLigamentsActivity(){
        Intent intent = new Intent(this, ligaments.class);
        startActivity(intent);

    }

    public void openBonesActivity(){
        Intent intent =  new Intent(this, bones.class);
        startActivity(intent);

    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



}
