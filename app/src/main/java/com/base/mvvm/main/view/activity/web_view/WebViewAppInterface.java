package com.base.mvvm.main.view.activity.web_view;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewAppInterface extends WebViewClient {
    private Context context;

    public WebViewAppInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
    }



}
