package com.aclocationtrack.dashboard.customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;

public class AddCustomerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        setBackButtonEnabledAndTitle("Add Customer");

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, AddCustomerFragment.getInstance(true)).commit();
    }
}
