package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/** Title: WebView - Android Studio Tutorial
 * Author: Coding in Flow
 * Date: 2017
 * Code version: -
 * Availability: https://www.youtube.com/watch?v=TUXui5ItBkM
 */

public class webview_m1 extends AppCompatActivity {

    /** Title: WebView - Android Studio Tutorial
     * Author: Coding in Flow
     * Date: 2017
     * Code version: -
     * Availability: https://www.youtube.com/watch?v=TUXui5ItBkM
     */

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_m1);

        /** Title: WebView - Android Studio Tutorial
         * Author: Coding in Flow
         * Date: 2017
         * Code version: -
         * Availability: https://www.youtube.com/watch?v=TUXui5ItBkM
         */
        webview = (WebView)findViewById(R.id.webview_m1);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://teachmeanatomy.info/upper-limb/muscles/hand/");


    }

    @Override
    public void onBackPressed() {

        Intent loginIntent = new Intent(webview_m1.this, Muscle.class);
        startActivity(loginIntent);
        finish();

        Toast.makeText(webview_m1.this, "Returned to camera view", Toast.LENGTH_LONG).show();

    }
}
