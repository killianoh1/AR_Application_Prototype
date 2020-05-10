package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/** Title: WebView - Android Studio Tutorial
 * Author: Coding in Flow
 * Date: 2017
 * Code version: -
 * Availability: https://www.youtube.com/watch?v=TUXui5ItBkM
 */
public class webview_m2 extends AppCompatActivity {

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
        setContentView(R.layout.activity_webview_m2);
        /** Title: WebView - Android Studio Tutorial
         * Author: Coding in Flow
         * Date: 2017
         * Code version: -
         * Availability: https://www.youtube.com/watch?v=TUXui5ItBkM
         */
        webview = (WebView)findViewById(R.id.webview_m2);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://teachmeanatomy.info/upper-limb/muscles/hand/");
    }

    @Override
    public void onBackPressed() {

        Intent loginIntent = new Intent(webview_m2.this, Muscle.class);
        startActivity(loginIntent);
        finish();

    }
}
