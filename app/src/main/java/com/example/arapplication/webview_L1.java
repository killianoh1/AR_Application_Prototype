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
public class webview_L1 extends AppCompatActivity {

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
        setContentView(R.layout.activity_webview__l1);

        /** Title: WebView - Android Studio Tutorial
         * Author: Coding in Flow
         * Date: 2017
         * Code version: -
         * Availability: https://www.youtube.com/watch?v=TUXui5ItBkM
         */

        webview = (WebView)findViewById(R.id.webview_L1);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.orthobullets.com/hand/6003/ligaments-of-the-fingers");

    }

    @Override
    public void onBackPressed() {

        Intent loginIntent = new Intent(webview_L1.this, Ligaments.class);
        startActivity(loginIntent);
        finish();

        Toast.makeText(webview_L1.this, "Returned to camera view", Toast.LENGTH_LONG).show();

    }
}
