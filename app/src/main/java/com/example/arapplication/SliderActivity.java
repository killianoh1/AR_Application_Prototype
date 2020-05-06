package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SliderActivity extends AppCompatActivity {

    private ViewPager mSlideViewpager;

    private Toolbar mainToolbar;

    private SliderAdapter sliderAdapter;

    private Button nextBtn;
    private Button backBtn;
    private Button backToMBtn;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_activity);






        mSlideViewpager = findViewById(R.id.slideViewPager);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewpager.setAdapter(sliderAdapter);

        nextBtn = findViewById(R.id.nextBtn);

        backToMBtn = findViewById(R.id.backToMenuBtn);


        backBtn = findViewById(R.id.prevBtn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewpager.setCurrentItem( mCurrentPage + 1);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewpager.setCurrentItem(mCurrentPage - 1);
            }
        });

        backToMBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendtoMain();

            }
        });


        mSlideViewpager.addOnPageChangeListener(viewListener);

    }

    private void sendtoMain() {

        Intent mainIntent = new Intent(SliderActivity.this, MenuApp.class);
        startActivity(mainIntent);
        finish();
    }


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {



            mCurrentPage =i;
            if (i == 0){

                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.INVISIBLE);

                nextBtn.setText("Next");
                backBtn.setText("");

            } else if (i == 3){
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                nextBtn.setVisibility(View.INVISIBLE);


                nextBtn.setText("Next");
                backBtn.setText("Back");
            }

            else {


                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setVisibility(View.VISIBLE);

                nextBtn.setText("Next");
                backBtn.setText("Back");
            }

        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


}
