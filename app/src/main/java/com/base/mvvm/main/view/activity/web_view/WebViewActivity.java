package com.base.mvvm.main.view.activity.web_view;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.base.mvvm.R;
import com.base.mvvm.common.view.base.BaseActivity;
import com.base.mvvm.databinding.ActivityWebViewBinding;

public class WebViewActivity extends BaseActivity<ActivityWebViewBinding> {

    private WebViewAppInterface webViewClient;

    @Override
    protected int getLayoutRes() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        return R.layout.activity_web_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding.webView.loadUrl("https://google.com");

        WebSettings webSettings = dataBinding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        dataBinding.webView.addJavascriptInterface(new WebViewAppInterface(this), "Android");
        dataBinding.webView.setWebViewClient(new WebViewAppInterface(this));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && dataBinding.webView.canGoBack()) {
            dataBinding.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

/*    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (request.getUrl().getHost().equals("htps://google.com")) {
                // This is my website, so do not override; let my WebView load the page
                return false;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
            startActivity(intent);

            return true;
        }
    }*/

}