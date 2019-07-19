package com.aclocationtrack.dashboard.profile;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;
import com.aclocationtrack.data.listener.DataListener;
import com.aclocationtrack.data.model.ProfileDetails;
import com.aclocationtrack.data.model.request.UpdateProfile;
import com.aclocationtrack.utility.TextInputUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class ProfileActivity extends BaseActivity {

    private static final String TAG = "ProfileActivity";


    @BindView(R.id.mobile_no)
    TextInputLayout mobileNo;
    @BindView(R.id.email)
    TextInputLayout email;
    @BindView(R.id.name)
    TextInputLayout name;
    @BindView(R.id.address1)
    TextInputLayout address1;
    @BindView(R.id.address2)
    TextInputLayout address2;
    @BindView(R.id.city)
    TextInputLayout city;
    @BindView(R.id.zipcode)
    TextInputLayout zipcode;
    @BindView(R.id.state)
    Spinner state;

    private List<ProfileDetails.Data.State> stateList = new ArrayList<>();
    private ArrayAdapter<ProfileDetails.Data.State> stateAdaper;

    private boolean isEditable = false;


    @BindViews({R.id.email, R.id.statetxt, R.id.name, R.id.address1, R.id.address2, R.id.city, R.id.state, R.id.zipcode})
    List<View> views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setBackButtonEnabledAndTitle(getString(R.string.profile));
        setButterKnife();

        mobileNo.setEnabled(false);
        ButterKnife.apply(views, ENABLED, isEditable);


        stateAdaper = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stateList);

        stateAdaper.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        state.setAdapter(stateAdaper);


        mobileNo.getEditText().setText(dataSource.getMobileNo());


        loadData();
    }


    private void loadData() {


        showLoading();

        dataSource.getProfile(dataSource.getAuthendicate(), new DataListener() {
            @Override
            public void onSuccess(Object object) {

                hideLoading();

                ProfileDetails profileDetails = (ProfileDetails) object;
                ProfileDetails.Data data = profileDetails.getData();

                ProfileDetails.Data.User user = data.getUser();

                name.getEditText().setText(user.getName() != null ? user.getName() : "");

                email.getEditText().setText(user.getEmail() != null ? user.getEmail() : dataSource.getEmail());
                city.getEditText().setText(user.getCity() != null ? user.getCity().toString() : dataSource.getCity());

                address1.getEditText().setText(user.getAddress1() != null ? user.getAddress1().toString() : dataSource.getAddress1());
                address2.getEditText().setText(user.getAddress2() != null ? user.getAddress2().toString() : dataSource.getAddress2());
                zipcode.getEditText().setText(user.getZipCode() != null ? user.getZipCode().toString() : dataSource.getZipcode());

                int sId = user.getStateId() != null ? Integer.parseInt(user.getStateId().toString()) : 0;

                stateAdaper.addAll(data.getStates());


                setSpinnerToValue(state, sId);


                if (sId == 0) {

                    String s = dataSource.getState();

                    setSpinnerToValue(state, s);

                }


            }

            @Override
            public void onFail(Throwable throwable) {
                hideLoading();
            }

            @Override
            public void onNetworkFailure() {
                hideLoading();
            }
        });


    }

    public void setSpinnerToValue(Spinner spinner, int value) {
        int index = 0;
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (stateList.get(i).getId() == value) {
                index = i;
                break; // terminate loop
            }
        }
        spinner.setSelection(index);
    }

    public void setSpinnerToValue(Spinner spinner, String value) {
        int index = 0;
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (stateList.get(i).getStateName().toLowerCase().equalsIgnoreCase(value.toLowerCase())) {
                index = i;
                break; // terminate loop
            }
        }
        spinner.setSelection(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.save_edit_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if (isEditable) menu.findItem(R.id.save).setIcon(R.drawable.ic_check_black_24dp);
        else menu.findItem(R.id.save).setIcon(R.drawable.ic_edit_black_24dp);

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.save:


                if (isEditable) {

                    if (isValidate()) {
                        updateProfileDataToServer();
                    } else {
                        return false;
                    }


                }

                isEditable = !isEditable;
                ButterKnife.apply(views, ENABLED, isEditable);
                invalidateOptionsMenu();


                break;

        }

        return super.onOptionsItemSelected(item);
    }


    private void updateProfileDataToServer() {


        UpdateProfile updateProfile = new UpdateProfile();
        final ProfileDetails.Data.State states = stateAdaper.getItem(state.getSelectedItemPosition());
        updateProfile.setAddress_1(TextInputUtil.getText(address1));
        updateProfile.setAddress_2(TextInputUtil.getText(address2));
        updateProfile.setCity(TextInputUtil.getText(city));
        updateProfile.setEmail(TextInputUtil.getText(email));
        updateProfile.setName(TextInputUtil.getText(name));
        updateProfile.setZip_code(TextInputUtil.getText(zipcode));
        updateProfile.setState(states.getId());


        showLoading();

        dataSource.updateProfile(dataSource.getAuthendicate(), updateProfile, new DataListener() {
            @Override
            public void onSuccess(Object object) {

                hideLoading();

                Log.e(TAG, "onSuccess: " + new Gson().toJson(object));

                showToast("Profile updated successfully");

                dataSource.saveAddress(TextInputUtil.getText(address1), TextInputUtil.getText(address2), TextInputUtil.getText(city), states.getStateName(), TextInputUtil.getText(zipcode));

            }

            @Override
            public void onFail(Throwable throwable) {
                hideLoading();
            }

            @Override
            public void onNetworkFailure() {

                hideLoading();
            }
        });


    }


    private boolean isValidate() {

        boolean isValid = true;

//        if (TextUtils.isEmpty(mobileNo.getEditText().getText())) {
//            TextInputUtil.setError(mobileNo, getString(R.string.mobileempty));
//            isValid = false;
//        } else if (!(mobileNo.getEditText().getText().toString().length() >= 10)) {
//            TextInputUtil.setError(mobileNo, getString(R.string.mobile_must_be));
//            isValid = false;
//        } else {
//            TextInputUtil.setDisableError(mobileNo);
//        }

        if (TextUtils.isEmpty(email.getEditText().getText())) {
            TextInputUtil.setError(email, getString(R.string.emailempty));
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getEditText().getText().toString()).matches()) {
            TextInputUtil.setError(email, getString(R.string.emailnotvalid));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(email);
        }

        if (TextUtils.isEmpty(name.getEditText().getText())) {
            TextInputUtil.setError(name, getString(R.string.nameempty));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(name);
        }

        if (TextUtils.isEmpty(address1.getEditText().getText())) {
            TextInputUtil.setError(address1, getString(R.string.addressempty));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(address1);
        }

        if (TextUtils.isEmpty(city.getEditText().getText())) {
            TextInputUtil.setError(city, getString(R.string.city_empty));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(city);
        }


        if (TextUtils.isEmpty(zipcode.getEditText().getText())) {
            TextInputUtil.setError(zipcode, getString(R.string.zipcode_empty));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(zipcode);
        }


        return isValid;

    }


    static final ButterKnife.Setter<View, Boolean> ENABLED = new ButterKnife.Setter<View, Boolean>() {
        @Override
        public void set(View view, Boolean value, int index) {
            view.setEnabled(value);
        }
    };
}
