package com.aclocationtrack.splash_screen_login_signup;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;
import com.aclocationtrack.dashboard.PolyhoseDashboardActivity;
import com.aclocationtrack.location.LocationService;
import com.aclocationtrack.login.LoginActivity;
import com.aclocationtrack.signup.SignUpActivity;

import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SplashScreenActivity extends BaseActivity {


    private static final String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int REQUEST_CODE = 43;

//    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.splash_screen_activity);
//        setButterKnife();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

//        handler.postDelayed(removeCallbacks, 2000);

    }


    Runnable removeCallbacks = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            finish();

        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();

//        handler.removeCallbacks(removeCallbacks);
    }
}
