package com.polyhose.auth.changepassword;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.polyhose.R;
import com.polyhose.base.BaseActivity;
import com.polyhose.data.model.ResetPassword;
import com.polyhose.utility.KeyboardUtils;
import com.polyhose.utility.TextInputUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity {

    private static final String TAG = "ChangePasswordActivity";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.password)
    TextInputLayout password;
    @BindView(R.id.c_password)
    TextInputLayout cPassword;
    @BindView(R.id.reset)
    Button reset;
    @BindView(R.id.loginView)
    CardView loginView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:

                onBackPressed();

                break;
            case R.id.reset:

                if (validateFormInputFieldsAll()) {
                    KeyboardUtils.hideKeyboard(this);
                    changePassword();
                }

                break;
        }
    }


    private boolean validateFormInputFieldsAll() {

        boolean isValid = true;

        if (TextUtils.isEmpty(password.getEditText().getText())) {
            TextInputUtil.setError(password, getString(R.string.passwordempty));
            isValid = false;
        } else if (!(password.getEditText().getText().toString().length() >= 6)) {
            TextInputUtil.setError(password, getString(R.string.passwordmustbe));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(password);
        }

        if (TextUtils.isEmpty(cPassword.getEditText().getText())) {
            TextInputUtil.setError(cPassword, getString(R.string.confirm_passwordempty));
            isValid = false;
        } else if (!(cPassword.getEditText().getText().toString().length() >= 6)) {
            TextInputUtil.setError(cPassword, getString(R.string.passwordmustbe));
            isValid = false;
        } else if (!TextInputUtil.getText(password).equalsIgnoreCase(TextInputUtil.getText(cPassword))) {
            TextInputUtil.setError(cPassword, getString(R.string.notmatch));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(cPassword);
        }


        return isValid;

    }


    private void changePassword() {


        String mobile_no = getIntent().getStringExtra("mobile_no");

        final Long a = Long.parseLong(mobile_no);
        final ResetPassword resetPassword = new ResetPassword();

        resetPassword.setMobile_no(a);

        resetPassword.setConfirm_password(TextInputUtil.getText(cPassword));

        resetPassword.setPassword(TextInputUtil.getText(password));

        showLoading();


//        Log.e(TAG, "changePassword: " + new Gson().toJson(resetPassword));

//        dataSource.changePassword(resetPassword, new DataListener() {
//            @Override
//            public void onSuccess(Object object) {
//
//                hideLoading();
//                ResponsePassword responsePassword = (ResponsePassword) object;
//
//                if (responsePassword.getSuccess()) {
//
//                    setResult(123, new Intent());
//
//                    showToast(responsePassword.getMessage() + ", please login");
//
//                    finish();
//
//                } else {
//
//                    showToast(responsePassword.getMessage() + " ,Please try again");
//
//                }
//
//            }
//
//            @Override
//            public void onFail(Throwable throwable) {
//                hideLoading();
//                showToast("Something went wrong,Please try again ");
//            }
//
//            @Override
//            public void onNetworkFailure() {
//                hideLoading();
//                showToast("There is no internet");
//            }
//        });


    }
}
