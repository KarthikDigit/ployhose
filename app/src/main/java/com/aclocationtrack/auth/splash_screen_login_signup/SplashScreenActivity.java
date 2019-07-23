package com.aclocationtrack.auth.splash_screen_login_signup;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import com.aclocationtrack.base.BaseActivity;
import com.aclocationtrack.auth.login.LoginActivity;
import com.aclocationtrack.dashboard.PolyhoseDashboardActivity;

public class SplashScreenActivity extends BaseActivity {


    private static final String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int REQUEST_CODE = 43;

//    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.splash_screen_activity);
//        setButterKnife();


//        if (dataSource.isLoggedIn()) {
//
//            Intent intent = new Intent(this, PolyhoseDashboardActivity.class);
//            startActivity(intent);
//            finish();
//
//        } else {

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
//        }

//        handler.postDelayed(removeCallbacks, 2000);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

//        handler.removeCallbacks(removeCallbacks);
    }
}
