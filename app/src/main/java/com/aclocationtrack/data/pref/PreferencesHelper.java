package com.aclocationtrack.data.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.aclocationtrack.utility.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yasar on 7/3/18.
 */

public class PreferencesHelper implements Pref {

    private static PreferencesHelper preferences;

    private SharedPreferences sharedPreferences;

    public static final String KEY_ACCESS_TOKEN = "access_token";
    public static final String KEY_TOKEN_TYPE = "token_type";
    public static final String KEY_ROLE = "role";
    public static final String KEY_ROLE_ID = "role_id";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_STATUS = "status";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_MOBILE_NO = "mobile_no";
    public static final String KEY_MOBILE_NO_O = "mobile_no_o";
    public static final String KEY_FCM_TOKEN = "fcmtoken";
    public static final String KEY_DEVICE_ID = "deviceid";
    public static final String KEY_ADDRESS1 = "address1";
    public static final String KEY_ADDRESS2 = "address2";
    public static final String KEY_CITY = "city";
    public static final String KEY_STATE = "state";
    public static final String KEY_ZIPCODE = "zipcode";
    public static final String KEY_ISPUSH_NOTIFICATION = "pushnotification";
    public static final String KEY_CURRENTDATE = "currentdate";
    public static final String KEY_PUNCH_ENABLED = "punch_enabled";


    private PreferencesHelper(Context context) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    public static PreferencesHelper getPreferencesInstance(Context context) {

        if (preferences == null) {
            preferences = new PreferencesHelper(context);
        }

        return preferences;
    }

    @Override
    public void saveSession(String accessToken, String tokenType, String role, String roleId, long userId, String name, String email, String mobileNo, String myOMobile) {

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.putString(KEY_TOKEN_TYPE, tokenType);
        editor.putString(KEY_ROLE, role);
        editor.putString(KEY_ROLE_ID, roleId);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_MOBILE_NO, mobileNo);
        editor.putString(KEY_MOBILE_NO_O, myOMobile);
        editor.putLong(KEY_USER_ID, userId);
        editor.putBoolean(KEY_STATUS, true);
        editor.apply();

    }

    @Override
    public void saveAddress(String address1, String address2, String city, String state, String zipcode) {


        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_ADDRESS1, address1);
        editor.putString(KEY_ADDRESS2, address2);
        editor.putString(KEY_CITY, city);
        editor.putString(KEY_STATE, state);
        editor.putString(KEY_ZIPCODE, zipcode);

        editor.apply();

    }

    @Override
    public String getAddress1() {
        return sharedPreferences.getString(KEY_ADDRESS1, "");
    }

    @Override
    public String getAddress2() {
        return sharedPreferences.getString(KEY_ADDRESS2, "");
    }

    @Override
    public String getCity() {
        return sharedPreferences.getString(KEY_CITY, "");
    }

    @Override
    public String getState() {
        return sharedPreferences.getString(KEY_STATE, "");
    }

    @Override
    public String getZipcode() {
        return sharedPreferences.getString(KEY_ZIPCODE, "");
    }


    @Override
    public String getRoleType() {
        return Utils.getName(sharedPreferences.getString(KEY_ROLE, ""));
    }

    @Override
    public String getName() {
        return Utils.getName(sharedPreferences.getString(KEY_NAME, ""));
    }

    @Override
    public String getMobileNo() {
        return sharedPreferences.getString(KEY_MOBILE_NO, "");
    }

    @Override
    public String getOMobileNo() {
        return sharedPreferences.getString(KEY_MOBILE_NO_O, "");

    }

    @Override
    public String getEmail() {
        return sharedPreferences.getString(KEY_EMAIL, "");
    }

    @Override
    public String getAccessToken() {
        return sharedPreferences.getString(KEY_ACCESS_TOKEN, null);
    }

    @Override
    public Map<Object, Object> getUserDetails() {
        return getSession();
    }

    @Override
    public Map<String, String> getAuthendicate() {


        if (isLoggedIn()) {
            Map<String, String> hashMap = new HashMap<>();

            String auth = sharedPreferences.getString(KEY_TOKEN_TYPE, "") + " " + sharedPreferences.getString(KEY_ACCESS_TOKEN, "");


            hashMap.put("Authorization", auth);
            hashMap.put("Accept", "application/json");

            return hashMap;
        }

        return null;

    }

    @Override
    public void saveTokenAndDeviceID(String token, String deviceId) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FCM_TOKEN, token);
        editor.putString(KEY_DEVICE_ID, deviceId);

        editor.apply();

    }

    @Override
    public Map<String, String> getTokenAndDeviceID() {

        Map<String, String> map = new HashMap<>();

        map.put(KEY_FCM_TOKEN, sharedPreferences.getString(KEY_FCM_TOKEN, ""));
        map.put(KEY_DEVICE_ID, sharedPreferences.getString(KEY_DEVICE_ID, ""));


        return map;
    }


    public Map<Object, Object> getSession() {

        Map<Object, Object> hashMap = new HashMap<>();

        hashMap.put(KEY_ACCESS_TOKEN, sharedPreferences.getString(KEY_ACCESS_TOKEN, null));
        hashMap.put(KEY_TOKEN_TYPE, sharedPreferences.getString(KEY_TOKEN_TYPE, null));
        hashMap.put(KEY_ROLE, sharedPreferences.getString(KEY_ROLE, ""));
        hashMap.put(KEY_ROLE_ID, sharedPreferences.getString(KEY_ROLE_ID, ""));
        hashMap.put(KEY_NAME, sharedPreferences.getString(KEY_NAME, ""));
        hashMap.put(KEY_EMAIL, sharedPreferences.getString(KEY_EMAIL, ""));
        hashMap.put(KEY_MOBILE_NO, sharedPreferences.getString(KEY_MOBILE_NO, ""));
        hashMap.put(KEY_USER_ID, sharedPreferences.getLong(KEY_USER_ID, 0));
        hashMap.put(KEY_STATUS, sharedPreferences.getBoolean(KEY_STATUS, false));

        return hashMap;
    }

    @Override
    public void saveCurrentDate(String d) {

        sharedPreferences.edit().putString(KEY_CURRENTDATE, d).apply();
    }

    @Override
    public String getCurrentDate() {
        return sharedPreferences.getString(KEY_CURRENTDATE, null);
    }

    @Override
    public void punchEnabled(boolean isEnabled) {
        sharedPreferences.edit().putBoolean(KEY_PUNCH_ENABLED, isEnabled).apply();

    }

    @Override
    public boolean isPunchEnabled() {
        return sharedPreferences.getBoolean(KEY_PUNCH_ENABLED, false);
    }

    @Override
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_STATUS, false);
    }

    @Override
    public void clear() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }

    @Override
    public void savePush(boolean isNoti) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_ISPUSH_NOTIFICATION, isNoti);
        editor.apply();
    }

    @Override
    public boolean getPush() {
        return sharedPreferences.getBoolean(KEY_ISPUSH_NOTIFICATION, false);
    }


}
