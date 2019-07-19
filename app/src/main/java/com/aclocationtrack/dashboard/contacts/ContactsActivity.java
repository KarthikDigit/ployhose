package com.aclocationtrack.dashboard.contacts;

import android.os.Bundle;
import android.webkit.WebView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;

public class ContactsActivity extends BaseActivity {


    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        setBackButtonEnabledAndTitle(getString(R.string.contact));


        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://crm.theaczone.com/api/contactweb");

    }
}
