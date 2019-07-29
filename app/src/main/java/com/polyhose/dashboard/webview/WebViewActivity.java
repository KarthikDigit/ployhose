package com.polyhose.dashboard.webview;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.polyhose.R;
import com.polyhose.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        setBackButtonEnabledAndTitle("Details");

        String urlOriginal = getIntent().getStringExtra("url");

        String ex = urlOriginal.substring(urlOriginal.length() - 4);

        String url = "https://docs.google.com/viewer?url=" + urlOriginal;
//        String doc = "<iframe src='http://docs.google.com/viewer?url=" + url + "&embedded=true' width='100%' height='100%'  style='border: none;'></iframe>";


        WebView wv = (WebView) findViewById(R.id.webView);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setAllowFileAccessFromFileURLs(true);
        wv.getSettings().setAllowUniversalAccessFromFileURLs(true);
        wv.getSettings().setBuiltInZoomControls(true);
//        wv.setWebChromeClient(new WebChromeClient());

//        wv.getSettings().setPluginsEnabled(true);
        wv.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                view.loadUrl(url);

//                showLoading();
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                hideLoading();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                hideLoading();
            }


        });
        wv.getSettings().setAllowFileAccess(true);

        if (ex.toLowerCase().equalsIgnoreCase(".jpg") || ex.toLowerCase().equalsIgnoreCase(".png")) {
            wv.loadUrl(urlOriginal);
        } else {

            wv.loadUrl(url);
        }
//        wv.loadData(doc, "text/html", "UTF-8");

    }
}
