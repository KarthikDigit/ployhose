package com.polyhose.auth.login;

import android.Manifest;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.polyhose.R;
import com.polyhose.base.BaseNetworkActivity;
import com.polyhose.common.MyCallBackWrapper;
import com.polyhose.dashboard.PolyhoseDashboardActivity;
import com.polyhose.data.model.request.LoginRequest;
import com.polyhose.data.model.response.LoginApiResponse;
import com.polyhose.utility.KeyboardUtils;
import com.polyhose.utility.TextInputUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
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
                if (validateFormInputFieldsAll()) {
                    KeyboardUtils.hideKeyboard(this);
//                    showToast("Valid");

//                    startActivity(new Intent(LoginActivity.this, DealerDashboardActivity.class));
//                    callLoginApi();
                    locationtestPermission();
                } else {

                    showToast(getString(R.string.allfieldsrequired));
                }

                break;


        }


    }

    @AfterPermissionGranted(REQUEST_CODE)
    public void locationtestPermission() {

        if (EasyPermissions.hasPermissions(this, perms)) {
//            callLoginApi();
//        moveToNextActivity(null);
            callLoginApi();
//            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.location_rationale),
                    REQUEST_CODE, perms);
        }
    }


    private void callLoginApi() {


        final LoginRequest request = new LoginRequest(mEmail.getEditText().getText().toString(), mPassword.getEditText().getText().toString());
//        request.setEmail(mEmail.getEditText().getText().toString());
//        request.setPassword(mPassword.getEditText().getText().toString());


        dataSource.login(request)
                .subscribe(new MyCallBackWrapper<LoginApiResponse>(this, this, true, false) {
                    @Override
                    public void onSuccess(LoginApiResponse response) {

                        if (response.getStatus().toLowerCase().equalsIgnoreCase("200")) {


                            dataSource.saveSession(response.getUserName(), response.getApikey(), String.valueOf(response.getUserID()), response.getRoleName(), String.valueOf(response.getRoleId()), String.valueOf(response.getRegionID()));

                            if (dataSource.isLoggedIn()) {

                                moveToNextActivity(response);

                            } else {

                                showToast("Something went wrong");
                            }

                        } else {

                            showToast(response.getMessage());
                        }
                    }
                });

    }


    private void moveToNextActivity(LoginApiResponse response) {


        startActivity(new Intent(LoginActivity.this, PolyhoseDashboardActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        finish();

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
        }
//        else if (!(mPassword.getEditText().getText().toString().length() >= 6)) {
//            TextInputUtil.setError(mPassword, getString(R.string.passwordmustbe));
//            isValid = false;
//        }
        else {
            TextInputUtil.setDisableError(mPassword);
        }

        return isValid;

    }

    @Override
    public void parseServerError(Response<?> response, boolean isToastMsg) {


        try {

            String errorBody = response.errorBody().string();


            showToast(errorBody);

//            JSONObject jsonObject = new JSONObject(errorBody);
//            JSONObject jsonObject1 = jsonObject.optJSONObject("data");
//            if (jsonObject1 != null) {
//                for (Iterator<String> it = jsonObject1.keys(); it.hasNext(); ) {
//                    String key = it.next();
//
//                    showToast(jsonObject1.getJSONArray(key).getString(0));
//                    return;
//                }
//            } else {
//                showToast(jsonObject.optString("data"));
//            }


        } catch (IOException e) {
//            e.printStackTrace();

            showToast("Something went wrong " + e.getMessage());
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

//    public static class LoginApiErrorResponseDeserializer implements JsonDeserializer<CommonError> {
//        @Override
//        public CommonError deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//
//
//            if (((JsonObject) json).get("data") instanceof JsonObject) {
//
//                CommonError commonError = new Gson().fromJson(json, CommonError.class);
//
//                LoginDataError loginDataError = new Gson().fromJson(((JsonObject) json).get("data"), LoginDataError.class);
//
//                commonError.setData(loginDataError);
//
//                return commonError;
//
//            } else {
//                return new Gson().fromJson(json, CommonError.class);
//            }
//
//        }
//    }
}
