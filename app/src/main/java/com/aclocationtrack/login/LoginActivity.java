package com.aclocationtrack.login;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;
import com.aclocationtrack.common.ApiErrorUtils;
import com.aclocationtrack.dashboard.PolyhoseDashboardActivity;
import com.aclocationtrack.data.listener.DataListener;
import com.aclocationtrack.data.model.ProfileDetails;
import com.aclocationtrack.data.model.errorresponse.CommonApiError;
import com.aclocationtrack.data.model.errorresponse.LoginDataApiError;
import com.aclocationtrack.data.model.errorresponse.LoginMessageApiError;
import com.aclocationtrack.data.model.errorresponse.LoginStringApiError;
import com.aclocationtrack.data.model.response.LoginResponse;
import com.aclocationtrack.data.model.response.UserDetailsResponse;
import com.aclocationtrack.forgotpassword.ForgotPasswordActivity;
import com.aclocationtrack.location.LocationService;
import com.aclocationtrack.signup.SignUpActivity;
import com.aclocationtrack.utility.KeyboardUtils;
import com.aclocationtrack.utility.TextInputUtil;
import com.aclocationtrack.utility.Utils;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class LoginActivity extends BaseActivity {


    private static final String TAG = "LoginActivity";

    private static final String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int REQUEST_CODE = 43;

    @BindView(R.id.mobile)
    TextInputLayout mMobile;
    @BindView(R.id.password)
    TextInputLayout mPassword;
    @BindView(R.id.login)
    Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setButterKnife();


    }


    @OnClick({ R.id.login})
    public void OnClickView(View view) {

        switch (view.getId()) {


            case R.id.login:

//                startActivity(new Intent(LoginActivity.this, DashboardScreenActivity.class));
//                if (validateFormInputFieldsAll()) {
                KeyboardUtils.hideKeyboard(this);
//                    showToast("Valid");

//                    startActivity(new Intent(LoginActivity.this, DealerDashboardActivity.class));
//                    callLoginApi();
                locationtestPermission();
//                } else {
//
//                    showToast(getString(R.string.allfieldsrequired));
//                }

                break;


        }


    }

    @AfterPermissionGranted(REQUEST_CODE)
    public void locationtestPermission() {

//        if (EasyPermissions.hasPermissions(this, perms)) {
//            callLoginApi();
            moveToNextActivity(null);
//            // ...
//        } else {
//            // Do not have permissions, request them now
//            EasyPermissions.requestPermissions(this, getString(R.string.location_rationale),
//                    REQUEST_CODE, perms);
//        }
    }


    private void callLoginApi() {

        final Map<String, Object> login = new HashMap<>();

        login.put("mobile_no", Long.parseLong(mMobile.getEditText().getText().toString()));
        login.put("password", mPassword.getEditText().getText().toString());

//        showLoading();

//        dataSource.login(new JSONObject(login).toString(), this);


        showLoading();
        dataSource.login(new JSONObject(login).toString(), new DataListener() {
            @Override
            public void onSuccess(Object object) {

                LoginActivity.super.onSuccess(object);


                final LoginResponse response = (LoginResponse) object;

                if (response.getSuccess()) {


//                    showToast(response.getMessage());

                    Map<String, String> headers = new HashMap<>();

                    String auth = response.getData().getTokenType() + " " + response.getData().getAccessToken();

                    headers.put("Authorization", auth);

                    headers.put("Accept", "application/json");
                    showLoading();
                    dataSource.getUserProfile(headers, new DataListener() {
                        @Override
                        public void onSuccess(Object object) {
                            LoginActivity.super.onSuccess(object);

                            UserDetailsResponse detailsResponse = (UserDetailsResponse) object;

                            if (detailsResponse.getSuccess()) {


//                                showToast(detailsResponse.getMessage());

                                LoginResponse.Data data = response.getData();

                                UserDetailsResponse.Data userData = detailsResponse.getData();

                                dataSource.saveSession(data.getAccessToken(), data.getTokenType(), data.getRole(), data.getRoleId(), data.getUserId(), userData.getName(), userData.getEmail(), userData.getMobileNo(), mMobile.getEditText().getText().toString());

                                getProfile(response);

                            } else {

                                showToast("Something went wrong, Please try again");

                            }


                        }

                        @Override
                        public void onFail(Throwable throwable) {
//                            LoginActivity.this.onFail(throwable);

                            hideLoading();
                            profileApiError(throwable);

                        }

                        @Override
                        public void onNetworkFailure() {
                            LoginActivity.super.onNetworkFailure();
                        }
                    });


                } else {

                    showToast("Something went wrong, Please try again");
                }


            }

            @Override
            public void onFail(Throwable throwable) {

                hideLoading();
                loginApiError(throwable);

//                LoginActivity.this.onFail(throwable);

            }

            @Override
            public void onNetworkFailure() {
                LoginActivity.super.onNetworkFailure();
            }
        });


    }


    private void profileApiError(Throwable throwable) {


        CommonApiError commonApiError = ApiErrorUtils.getUserProfileApiError(throwable);

        showToast(commonApiError.getMessage());


    }


    protected void loginApiError(Throwable throwable) {


        CommonApiError commonApiError = ApiErrorUtils.parseLoginError(throwable);

        if (commonApiError instanceof LoginMessageApiError) {

            LoginMessageApiError data = (LoginMessageApiError) commonApiError;

            LoginDataApiError dataApiError = data.getData();

            List<String> mobileList = dataApiError.getMobileNo();
            List<String> passwordList = dataApiError.getPassword();

            if (mobileList != null && !mobileList.isEmpty()) {

                showToast(Utils.getString(dataApiError.getMobileNo()));

            } else if (passwordList != null && !passwordList.isEmpty()) {

                showToast(Utils.getString(dataApiError.getPassword()));

            } else {

                showToast(commonApiError.getMessage());
            }


        } else if (commonApiError instanceof LoginStringApiError) {


            if (((LoginStringApiError) commonApiError).getData() != null) {

                showToast(((LoginStringApiError) commonApiError).getData());

            } else {

                showToast(commonApiError.getMessage());
            }

        } else {

            showToast(commonApiError.getMessage());
        }


    }


    private void getProfile(final LoginResponse response) {


        showLoading();

        dataSource.getProfile(dataSource.getAuthendicate(), new DataListener() {
            @Override
            public void onSuccess(Object object) {

                hideLoading();

                ProfileDetails profileDetails = (ProfileDetails) object;
                ProfileDetails.Data data = profileDetails.getData();

                ProfileDetails.Data.User user = data.getUser();

                String name = user.getName() != null ? user.getName() : "";

                String email = user.getEmail() != null ? user.getEmail() : "";
                String city = user.getCity() != null ? user.getCity().toString() : "";

                String address1 = user.getAddress1() != null ? user.getAddress1().toString() : "";
                String address2 = user.getAddress2() != null ? user.getAddress2().toString() : "";
                String zipcode = user.getZipCode() != null ? user.getZipCode().toString() : "";

                int sId = user.getStateId() != null ? Integer.parseInt(user.getStateId()) : 0;

                List<ProfileDetails.Data.State> stateList = profileDetails.getData().getStates();

                int position = 0;

                for (int i = 0; i < stateList.size(); i++) {

                    if (stateList.get(i).getId() == sId) {
                        position = i;
                        break;
                    }

                }

                String state = stateList.get(position).getStateName();

                dataSource.saveAddress(address1, address2, city, state, zipcode);

                moveToNextActivity(response);


            }

            @Override
            public void onFail(Throwable throwable) {
                hideLoading();

                profileApiError(throwable);
                moveToNextActivity(response);
            }

            @Override
            public void onNetworkFailure() {
                hideLoading();

            }
        });


    }


    private void moveToNextActivity(LoginResponse response) {


        startActivity(new Intent(LoginActivity.this, PolyhoseDashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        finish();


//        if (dataSource.isLoggedIn()) {
//
//            Intent intent = new Intent(this, LocationService.class);
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                startForegroundService(intent);
//            } else startService(intent);
//
//
//            showToast(response.getMessage());
//
//            if (dataSource.getRoleType().toLowerCase().equalsIgnoreCase("ServiceRep".toLowerCase())) {
//
////                startActivity(new Intent(getApplicationContext(), ServiceManActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
//
//                Intent i = new Intent(getApplicationContext(), ServiceManActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra("EXIT", true);
//                startActivity(i);
//                finish();
////                finish();
//
//            } else if (dataSource.getRoleType().toLowerCase().equalsIgnoreCase("SalesRep".toLowerCase())) {
//
////                                        startActivity(new Intent(LoginActivity.this, SalesManActivity.class));
//                startActivity(new Intent(LoginActivity.this, SalesManDashboardScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//
//                finish();
//
//            } else if (dataSource.getRoleType().toLowerCase().equalsIgnoreCase("Customer".toLowerCase())) {
//
////                startActivity(new Intent(LoginSignUpActivity.this, SalesManActivity.class));
//                startActivity(new Intent(LoginActivity.this, DashboardScreenCustomerActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//
//                finish();
//
//            } else {
//
//                startActivity(new Intent(LoginActivity.this, DashboardScreenActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//
//                finish();
//            }
//
//        } else {
//            showToast("Something went wrong");
//        }


    }


//    @Override
//    public void onSuccess(Object object) {
//        super.onSuccess(object);
//
//        if (object instanceof LoginResponse) {
//
//            LoginResponse response = (LoginResponse) object;
//
//            if (response.getSuccess()) {
//
//
//                LoginResponse.Data data = response.getData();
//
////                dataSource.saveSession(data.getAccessToken(), data.getTokenType(), data.getRole(), data.getRoleId(), data.getUserId());
//
//                if (dataSource.isLoggedIn()) {
//                    showToast(response.getMessage());
//
//                    startActivity(new Intent(LoginActivity.this, DashboardScreenActivity.class));
//
//                    finish();
//
//                } else {
//                    showToast("Something went wrong");
//                }
//
//
//            } else {
//                showToast("Something went wrong");
//            }
//
//
//        } else {
//
//            showToast("Something went wrong");
//
//        }
//
//    }

    @Override
    public void onFail(Throwable throwable) {
        super.onFail(throwable);

        if (!(throwable instanceof HttpException)) {

            showToast(throwable.getMessage());

            Log.e(TAG, "onFail: " + throwable.getMessage());

        }

    }

    private boolean validateFormInputFieldsAll() {

        boolean isValid = true;

        if (TextUtils.isEmpty(mMobile.getEditText().getText())) {
            TextInputUtil.setError(mMobile, getString(R.string.mobileempty));
            isValid = false;
        } else if (!(mMobile.getEditText().getText().toString().length() >= 10)) {
            TextInputUtil.setError(mMobile, getString(R.string.mobile_must_be));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(mMobile);
        }

        if (TextUtils.isEmpty(mPassword.getEditText().getText())) {
            TextInputUtil.setError(mPassword, getString(R.string.passwordempty));
            isValid = false;
        } else if (!(mPassword.getEditText().getText().toString().length() >= 6)) {
            TextInputUtil.setError(mPassword, getString(R.string.passwordmustbe));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(mPassword);
        }

        return isValid;

    }
}
