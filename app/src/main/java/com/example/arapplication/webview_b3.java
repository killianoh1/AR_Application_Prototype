package com.example.arapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview_b3 extends AppCompatActivity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_b3);

        webview = (WebView)findViewById(R.id.webview_b3);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl("https://www.britannica.com/science/human-skeleton/Hands-and-feet");

    }

    @Override
    public void onBackPressed() {

        Intent loginIntent = new Intent(webview_b3.this, bones.class);
        startActivity(loginIntent);
        finish();

    }
}
