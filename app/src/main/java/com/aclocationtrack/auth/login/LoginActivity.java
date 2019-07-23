package com.aclocationtrack.auth.login;

import android.Manifest;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;
import com.aclocationtrack.base.BaseNetworkActivity;
import com.aclocationtrack.common.APIErrorUtil;
import com.aclocationtrack.common.ApiErrorUtils;
import com.aclocationtrack.common.MyCallBackWrapper;
import com.aclocationtrack.dashboard.PolyhoseDashboardActivity;
import com.aclocationtrack.data.model.ProfileDetails;
import com.aclocationtrack.data.model.errorresponse.CommonApiError;
import com.aclocationtrack.data.model.errorresponse.CommonError;
import com.aclocationtrack.data.model.errorresponse.LoginDataApiError;
import com.aclocationtrack.data.model.errorresponse.LoginDataError;
import com.aclocationtrack.data.model.errorresponse.LoginMessageApiError;
import com.aclocationtrack.data.model.errorresponse.LoginStringApiError;
import com.aclocationtrack.data.model.response.APIError;
import com.aclocationtrack.data.model.response.LoginResponse;
import com.aclocationtrack.data.model.response.UserDetailsResponse;
import com.aclocationtrack.utility.KeyboardUtils;
import com.aclocationtrack.utility.TextInputUtil;
import com.aclocationtrack.utility.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.UndeliverableException;
import pub.devrel.easypermissions.AfterPermissionGranted;
import retrofit2.Response;
import timber.log.Timber;

public class LoginActivity extends BaseNetworkActivity {


    private static final String TAG = "LoginActivity";

    private static final String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    private static final int REQUEST_CODE = 43;

    @BindView(R.id.email)
    TextInputLayout mEmail;
    @BindView(R.id.password)
    TextInputLayout mPassword;
    @BindView(R.id.login)
    Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setButterKnife();

        Timber.plant(new Timber.DebugTree());


    }


    @OnClick({R.id.login})
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
//        moveToNextActivity(null);
        callLoginApi();
//            // ...
//        } else {
//            // Do not have permissions, request them now
//            EasyPermissions.requestPermissions(this, getString(R.string.location_rationale),
//                    REQUEST_CODE, perms);
//        }
    }


    private void callLoginApi() {

        final Map<String, Object> login = new HashMap<>();
        login.put("email", mEmail.getEditText().getText().toString());
        login.put("password", mPassword.getEditText().getText().toString());

        dataSource.login(new JSONObject(login).toString())
                .subscribe(new MyCallBackWrapper<LoginResponse>(this, this, true, false) {
                    @Override
                    public void onSuccess(LoginResponse loginResponse) {

                        if (loginResponse.getSuccess()) {
                            getUserProfile(loginResponse);
                        }
                    }
                });


//        dataSource.login(new JSONObject(login).toString(), new DataListener() {
//            @Override
//            public void onSuccess(Object object) {
//
//                LoginActivity.super.onSuccess(object);
//
//
//                final LoginResponse response = (LoginResponse) object;
//
//                if (response.getSuccess()) {
//
//
////                    showToast(response.getMessage());
//
//                    Map<String, String> headers = new HashMap<>();
//
//                    String auth = response.getData().getTokenType() + " " + response.getData().getAccessToken();
//
//                    headers.put("Authorization", auth);
//
//                    headers.put("Accept", "application/json");
//                    showLoading();
//                    dataSource.getUserProfile(headers, new DataListener() {
//                        @Override
//                        public void onSuccess(Object object) {
//                            LoginActivity.super.onSuccess(object);
//
//                            UserDetailsResponse detailsResponse = (UserDetailsResponse) object;
//
//                            if (detailsResponse.getSuccess()) {
//
//
////                                showToast(detailsResponse.getMessage());
//
//                                LoginResponse.Data data = response.getData();
//
//                                UserDetailsResponse.Data userData = detailsResponse.getData();
//
//                                dataSource.saveSession(data.getAccessToken(), data.getTokenType(), data.getRole(), data.getRoleId(), data.getUserId(), userData.getName(), userData.getEmail(), userData.getMobileNo(), mMobile.getEditText().getText().toString());
//
//                                getProfile(response);
//
//                            } else {
//
//                                showToast("Something went wrong, Please try again");
//
//                            }
//
//
//                        }
//
//                        @Override
//                        public void onFail(Throwable throwable) {
////                            LoginActivity.this.onFail(throwable);
//
//                            hideLoading();
//                            profileApiError(throwable);
//
//                        }
//
//                        @Override
//                        public void onNetworkFailure() {
//                            LoginActivity.super.onNetworkFailure();
//                        }
//                    });
//
//
//                } else {
//
//                    showToast("Something went wrong, Please try again");
//                }
//
//
//            }
//
//            @Override
//            public void onFail(Throwable throwable) {
//
//                hideLoading();
//                loginApiError(throwable);
//
////                LoginActivity.this.onFail(throwable);
//
//            }
//
//            @Override
//            public void onNetworkFailure() {
//                LoginActivity.super.onNetworkFailure();
//            }
//        });


    }


    private void getUserProfile(final LoginResponse response) {

        Map<String, String> headers = new HashMap<>();

        String auth = response.getData().getTokenType() + " " + response.getData().getAccessToken();

        headers.put("Authorization", auth);

        headers.put("Accept", "application/json");


        dataSource.getUserProfile(headers).subscribe(new MyCallBackWrapper<UserDetailsResponse>(this, this, true, false) {
            @Override
            public void onSuccess(UserDetailsResponse detailsResponse) {

                if (detailsResponse.getSuccess()) {
//
//
////                                showToast(detailsResponse.getMessage());
//
                    LoginResponse.Data data = response.getData();

                    UserDetailsResponse.Data userData = detailsResponse.getData();

                    dataSource.saveSession(data.getAccessToken(), data.getTokenType(), data.getRole(), data.getRoleId(), data.getUserId(), userData.getName(), userData.getEmail(), userData.getMobileNo(), userData.getMobileNo());

                    getProfile(response);

                } else {

                    showToast("Something went wrong, Please try again");

                }
            }
        });

    }


    private void getProfile(final LoginResponse response) {


        showLoading();


        dataSource.getProfile(dataSource.getAuthendicate()).subscribe(new MyCallBackWrapper<ProfileDetails>(this, this, true, false) {
            @Override
            public void onSuccess(ProfileDetails profileDetails) {

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
            public void onError(Throwable e) {
                super.onError(e);
                moveToNextActivity(response);
            }
        });


    }


//    private void profileApiError(Throwable throwable) {
//
//
//        CommonApiError commonApiError = ApiErrorUtils.getUserProfileApiError(throwable);
//
//        showToast(commonApiError.getMessage());
//
//
//    }
//
//
//    protected void loginApiError(Throwable throwable) {
//
//
//        CommonApiError commonApiError = ApiErrorUtils.parseLoginError(throwable);
//
//        if (commonApiError instanceof LoginMessageApiError) {
//
//            LoginMessageApiError data = (LoginMessageApiError) commonApiError;
//
//            LoginDataApiError dataApiError = data.getData();
//
//            List<String> mobileList = dataApiError.getMobileNo();
//            List<String> passwordList = dataApiError.getPassword();
//
//            if (mobileList != null && !mobileList.isEmpty()) {
//
//                showToast(Utils.getString(dataApiError.getMobileNo()));
//
//            } else if (passwordList != null && !passwordList.isEmpty()) {
//
//                showToast(Utils.getString(dataApiError.getPassword()));
//
//            } else {
//
//                showToast(commonApiError.getMessage());
//            }
//
//
//        } else if (commonApiError instanceof LoginStringApiError) {
//
//
//            if (((LoginStringApiError) commonApiError).getData() != null) {
//
//                showToast(((LoginStringApiError) commonApiError).getData());
//
//            } else {
//
//                showToast(commonApiError.getMessage());
//            }
//
//        } else {
//
//            showToast(commonApiError.getMessage());
//        }
//
//
//    }


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


    private boolean validateFormInputFieldsAll() {

        boolean isValid = true;

        if (TextUtils.isEmpty(mEmail.getEditText().getText())) {
            TextInputUtil.setError(mEmail, getString(R.string.emailempty));
            isValid = false;
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(mEmail.getEditText().getText().toString()).matches())) {
            TextInputUtil.setError(mEmail, getString(R.string.emailnotvalid));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(mEmail);
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

    @Override
    public void parseServerError(Response<?> response, boolean isToastMsg) {


        try {

            String errorBody = response.errorBody().string();

            Gson loginDeserializer = new GsonBuilder().registerTypeAdapter(CommonError.class, new LoginApiErrorResponseDeserializer()).create();

            CommonError commonError = loginDeserializer.fromJson(errorBody, CommonError.class);


            if (commonError.getData() instanceof LoginDataError) {

                LoginDataError loginDataError = (LoginDataError) commonError.getData();

                if (loginDataError.getEmail() != null && !loginDataError.getEmail().isEmpty()) {

                    showToast(loginDataError.getEmail().get(0));

                } else if (loginDataError.getPassword() != null && !loginDataError.getPassword().isEmpty()) {

                    showToast(loginDataError.getPassword().get(0));

                } else {

                    showToast("Test Something went wrong");
                }


            } else {

                showToast(commonError.getData().toString());
            }


        } catch (IOException e) {
//            e.printStackTrace();

            showToast("Test Something went wrong " + e.getMessage());
        }


//        String errorBody = null;
//        try {
//            if (response.errorBody() != null) {
//                errorBody = response.errorBody().string();
//
//                try {
//
//                    APIError apiError = new Gson().fromJson(errorBody, APIError.class);
//
//                    showToast(apiError.getMessage());
//                } catch (JsonSyntaxException | UndeliverableException e) {
//
//                    Log.e(TAG, "parseServerError: " + e.getMessage());
//                }
//
//
//            }
//        } catch (UndeliverableException | IOException e) {
//            Log.e(TAG, "parseServerError: " + e.getMessage());
//        }


//        APIError apiError = APIErrorUtil.parseErrorTest(APIError.class, response);
//
//        if (apiError != null) {
//            APIError.Data data = apiError.getData();
//            if (data.getMobileNo() != null && !data.getMobileNo().isEmpty()) {
//
//                showToast(data.getMobileNo().get(0));
//            } else {
//                showToast(data.toString());
//            }
//        } else {
//
//            showToast("Something went wrong ");
//        }


    }

    public class LoginApiErrorResponseDeserializer implements JsonDeserializer<CommonError> {
        @Override
        public CommonError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


            if (((JsonObject) json).get("data") instanceof JsonObject) {

                CommonError commonError = new Gson().fromJson(json, CommonError.class);

                LoginDataError loginDataError = new Gson().fromJson(((JsonObject) json).get("data"), LoginDataError.class);

                commonError.setData(loginDataError);

                return commonError;

            } else {
                return new Gson().fromJson(json, CommonError.class);
            }

        }
    }
}
