package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
 * Author: Akshaye JH
 * Date: 2017
 * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
 */

public class SliderActivity extends AppCompatActivity {

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */

    private ViewPager mSlideViewpager;



    private SliderAdapter sliderAdapter;

    private Button nextBtn;
    private Button backBtn;
    private Button backToMBtn;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_activity);



/** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
 * Author: Akshaye JH
 * Date: 2017
 * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
 */


        mSlideViewpager = findViewById(R.id.slideViewPager);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewpager.setAdapter(sliderAdapter);

        nextBtn = findViewById(R.id.nextBtn);

        backToMBtn = findViewById(R.id.backToMenuBtn);


        backBtn = findViewById(R.id.prevBtn);

        /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
         * Author: Akshaye JH
         * Date: 2017
         * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
         */

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewpager.setCurrentItem( mCurrentPage + 1);
            }
        });

        /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
         * Author: Akshaye JH
         * Date: 2017
         * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
         */

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlideViewpager.setCurrentItem(mCurrentPage - 1);
            }
        });

        /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
         * Author: Akshaye JH
         * Date: 2017
         * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
         */

        backToMBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendtoMain();

            }
        });

        /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
         * Author: Akshaye JH
         * Date: 2017
         * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
         */

        mSlideViewpager.addOnPageChangeListener(viewListener);

    }

    private void sendtoMain() {

        Intent mainIntent = new Intent(SliderActivity.this, MenuApp.class);
        startActivity(mainIntent);
        finish();
    }

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */


    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float positionOffset, int positionOffsetPixels) {

        }

        /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
         * Author: Akshaye JH
         * Date: 2017
         * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
         */

        @Override
        public void onPageSelected(int i) {


            mCurrentPage =i;
            if (i == 0){

                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.INVISIBLE);

                nextBtn.setText("Next");
                backBtn.setText("");

            } else if (i == 4){
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

        /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
         * Author: Akshaye JH
         * Date: 2017
         * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
         */

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


}
