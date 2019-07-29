package com.polyhose.dashboard.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.polyhose.R;
import com.polyhose.base.BaseActivity;

public class AddCustomerActivity extends BaseActivity {


    private static final String EXTRA_KEY_TITLE = "title";
    private static final String EXTRA_KEY_FROM_TASK = "from_task";
    private static final String EXTRA_KEY_CUSTOMER_UPDATE = "customer_update";
    private static final String EXTRA_KEY_CUSTOMER_ID = "customer_id";
    private static final String EXTRA_KEY_CUSTOMER_POSITION = "customer_position";


    public static void startActivity(Context context, boolean isFromTask, boolean isCustomerUpdate, long cusomerId, long position, String title) {

        Intent intent = new Intent(context, AddCustomerActivity.class);
        intent.putExtra(EXTRA_KEY_TITLE, title);
        intent.putExtra(EXTRA_KEY_FROM_TASK, isFromTask);
        intent.putExtra(EXTRA_KEY_CUSTOMER_UPDATE, isCustomerUpdate);
        intent.putExtra(EXTRA_KEY_CUSTOMER_ID, cusomerId);
        intent.putExtra(EXTRA_KEY_CUSTOMER_POSITION, position);

        context.startActivity(intent);

    }


    public static Intent getIntent(Context context, boolean isFromTask, boolean isCustomerUpdate, long cusomerId, long position, String title) {

        Intent intent = new Intent(context, AddCustomerActivity.class);
        intent.putExtra(EXTRA_KEY_TITLE, title);
        intent.putExtra(EXTRA_KEY_FROM_TASK, isFromTask);
        intent.putExtra(EXTRA_KEY_CUSTOMER_UPDATE, isCustomerUpdate);
        intent.putExtra(EXTRA_KEY_CUSTOMER_ID, cusomerId);
        intent.putExtra(EXTRA_KEY_CUSTOMER_POSITION, position);

        return intent;

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);


        String title = "Add Customers";

        if (getIntent() != null && getIntent().hasExtra(EXTRA_KEY_TITLE)) {

            title = getIntent().getStringExtra(EXTRA_KEY_TITLE);
        }

        setBackButtonEnabledAndTitle(title);

        boolean isFromTask = true;
        boolean isCustomerUpdate = false;
        long customerId = -1;
        long customerPosition = -1;

        if (getIntent() != null && getIntent().hasExtra(EXTRA_KEY_FROM_TASK)) {

            isFromTask = getIntent().getBooleanExtra(EXTRA_KEY_FROM_TASK, false);
        }

        if (getIntent() != null && getIntent().hasExtra(EXTRA_KEY_CUSTOMER_UPDATE)) {

            isCustomerUpdate = getIntent().getBooleanExtra(EXTRA_KEY_CUSTOMER_UPDATE, false);
        }

        if (getIntent() != null && getIntent().hasExtra(EXTRA_KEY_CUSTOMER_ID)) {

            customerId = getIntent().getLongExtra(EXTRA_KEY_CUSTOMER_ID, 0);
        }

        if (getIntent() != null && getIntent().hasExtra(EXTRA_KEY_CUSTOMER_POSITION)) {

            customerPosition = getIntent().getLongExtra(EXTRA_KEY_CUSTOMER_POSITION, -1);
        }


        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, AddCustomerFragment.getInstance(isFromTask, isCustomerUpdate, customerId, customerPosition)).commit();
    }
}
