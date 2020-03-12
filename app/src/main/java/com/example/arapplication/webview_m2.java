package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview_m2 extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_m2);

        webview = (WebView)findViewById(R.id.webview_m2);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.google.com");
    }

    @Override
    public void onBackPressed() {

        Intent loginIntent = new Intent(webview_m2.this, MainActivity.class);
        startActivity(loginIntent);
        finish();

    }
}
