package com.aclocationtrack.auth.splash_screen_login_signup;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;
import com.aclocationtrack.dashboard.PolyhoseDashboardActivity;
import com.aclocationtrack.location.LocationService;
import com.aclocationtrack.auth.login.LoginActivity;
import com.aclocationtrack.auth.signup.SignUpActivity;

import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class LoginSignUpActivity extends BaseActivity {


    private static final String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int REQUEST_CODE = 43;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_up);
        setButterKnife();

//        String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImRlYjJkZTIyYTg5M2RjY2Q2YThhNWE4YzRmZGMwMTMzZGZjNmVlYWQwMmZjNDZhNzcyYjc5NjZlNzcxNzEzOWFhMDE0NTQ4ZjVmNjliYTNkIn0.eyJhdWQiOiIxIiwianRpIjoiZGViMmRlMjJhODkzZGNjZDZhOGE1YThjNGZkYzAxMzNkZmM2ZWVhZDAyZmM0NmE3NzJiNzk2NmU3NzE3MTM5YWEwMTQ1NDhmNWY2OWJhM2QiLCJpYXQiOjE1MzE3MzI3NjEsIm5iZiI6MTUzMTczMjc2MSwiZXhwIjoxNTYzMjY4NzYxLCJzdWIiOiIxOSIsInNjb3BlcyI6W119.sgNR_sHL1MYdPinlYdD-eYBWlVZmNFP1c3AwVv8vfg4daemVlvnw7AAqOSp1kRUzqVoBaWMAwnltGNfRNhgzsiTnw8OQ_DtnCYjZBpnuofyfJHb-EzB2MPb7jVW9LuBDucOoszpYJHv3qAHiBSy8lwXYQdsBsBnZCwEzia551GmQMVlBV9pHRe8Md1Bqtes67N3CHxID15rCwFf7f5R8EvXKAZbBzP0Z7ErBaQ0Mfiax0H_7JNkeBElg86eiAoCl3kcNtcc_SQf4He3DVSm8c8iIP9WilRMKEbqFZwCQL--n6ydv_ZWB9HXA3kr4rqyvDmaYBtxCvdlvHcRcwMJ1ZQzzlePH4affEGu2XHLNcgSIO0NSbnrJwdCEtBoMPnNrw8q4NJAr1zFy_RbtVayxginjMcl-nWTuO1HjMf5hrkitScNIMIkDqIMXWyeJSIVSPS2cNqC220WkBuS-11HRP9EgK9Jqu1qRTn3doatAREbMVkU2fSM0dA2KrWgn0md6KvNmMIXN1oyViuMWCCaG5ANYpBGQ-ocHxnJxQHEXs-UmAFeZakxWk6i_uYqh2hKNCda98SWvZkhhWND8LmhAFL-4tARg_YRgW0Krq1wzc5lh1F7Vt8F_6tbGyq2h4lgKC7FjxfGKno8Wc5OKl1094S1m9WTy45GiJFnG09Nt7qA";

//        dataSource.saveSession(token,"Bearer","Dealer","1",19,"Thirumal","thirumal@gmail.com","9600749362");

//        locationtestPermission();

        callActivity();

    }


    private void callActivity() {
        if (dataSource.isLoggedIn()) {

            startActivity(new Intent(LoginSignUpActivity.this, PolyhoseDashboardActivity.class));
            finish();
        }
    }

    @AfterPermissionGranted(REQUEST_CODE)
    public void locationtestPermission() {

        if (EasyPermissions.hasPermissions(this, perms)) {
//            // Already have permission, do the thing
            callActivity();
            Intent intent = new Intent(this, LocationService.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else startService(intent);


//            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.location_rationale),
                    REQUEST_CODE, perms);
        }
    }


    @OnClick({R.id.btn_login, R.id.btn_create_account})
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_login:

                startActivity(new Intent(LoginSignUpActivity.this, LoginActivity.class));

                break;

            case R.id.btn_create_account:

                startActivity(new Intent(LoginSignUpActivity.this, SignUpActivity.class));

                break;

        }

    }
}
