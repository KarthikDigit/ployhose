package com.aclocationtrack.data.pref;


import java.util.Map;

import javax.sql.StatementEvent;

/**
 * Created by yasar on 22/3/18.
 */

public interface Pref {


    void saveCurrentDate(String d);

    String getCurrentDate();

    void punchEnabled(boolean isEnabled);

    boolean isPunchEnabled();

    boolean isLoggedIn();

    void saveSession(String accessToken, String tokenType, String role, String roleId, long userId, String name, String email, String mobileNo, String myOMobile);
//    void saveUserInfo(String name, String email, String mobileNo);


    void saveAddress(String address1, String address2, String city, String state, String zipcode);


    String getAddress1();

    String getAddress2();

    String getCity();

    String getState();

    String getZipcode();

    String getRoleType();

    String getName();

    String getMobileNo();

    String getOMobileNo();

    String getEmail();

    String getAccessToken();

    Map getUserDetails();

    Map<String, String> getAuthendicate();

    void saveTokenAndDeviceID(String token, String deviceId);

    Map<String, String> getTokenAndDeviceID();

    void clear();


    void savePush(boolean isNoti);

    boolean getPush();

}
