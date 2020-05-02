package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class webview_L3 extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview__l3);
        webview = (WebView)findViewById(R.id.webview_L3);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.healthline.com/human-body-maps/flexor-retinaculum-of-the-hand#1");

    }


    @Override
    public void onBackPressed() {

        Intent loginIntent = new Intent(webview_L3.this, Ligaments.class);
        startActivity(loginIntent);
        finish();

        Toast.makeText(webview_L3.this, "Returned to camera view", Toast.LENGTH_LONG).show();

    }

}
