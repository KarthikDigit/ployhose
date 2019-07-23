package com.aclocationtrack.dashboard.customer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;

public class AddCustomerActivity extends BaseActivity {


    private static final String EXTRA_KEY_TITLE = "title";


    public static void startActivity(Context context, String title) {

        Intent intent = new Intent(context, AddCustomerActivity.class);
        intent.putExtra(EXTRA_KEY_TITLE, title);

        context.startActivity(intent);

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);


        String title = "Add Customer";

        if (getIntent() != null && getIntent().hasExtra(EXTRA_KEY_TITLE)) {

            title = getIntent().getStringExtra(EXTRA_KEY_TITLE);
        }

        setBackButtonEnabledAndTitle(title);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, AddCustomerFragment.getInstance(true)).commit();
    }
}
