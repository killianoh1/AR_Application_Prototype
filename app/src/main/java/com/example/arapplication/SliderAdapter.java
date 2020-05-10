package com.example.arapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;



/** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
 * Author: Akshaye JH
 * Date: 2017
 * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
 */

public class SliderAdapter extends PagerAdapter {

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */

    Context context;
    LayoutInflater layoutInflater;

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */

    public SliderAdapter(Context context){
        this.context = context;
    }

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */

    public int[] slide_images = {
            R.drawable.intro1_box,
            R.drawable.intro2_web,
            R.drawable.intro3,
            R.drawable.intro2_cam,
            R.drawable.account_help



    };

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */

    public String [] slide_headings= {

            "Starting Camera View",
            "Model Rendered",
            "WebView",
            "Take Photo",
            "Signing Out"


    };

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */


    public String[] slide_descs = {

            "Place the target image within the black box frame to have the hand model appear" ,

            "Once the model appears feel free to click on any of the labels to learn more about the model!",

            "After selecting a label,  a web page will pop up relating to that label you selected. Press the back button on your mobile to return" +
                    "to the original camera view.",

            "You can also take a photo of the hand model by pressing the camera button when the model appears. The photo will automatically save to your device.",

            "To log out, access your account page from the menu and select the dots icon located in the corner."

    };

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;

    }

    /** Title: Android Slide / Walkthrough / Onboarding Screen Design - Android Studio
     * Author: Akshaye JH
     * Date: 2017
     * Availability: https://www.youtube.com/watch?v=byLKoPgB7yA
     */

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((RelativeLayout)object);

    }
}