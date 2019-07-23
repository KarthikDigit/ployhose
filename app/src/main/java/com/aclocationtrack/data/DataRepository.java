package com.aclocationtrack.data;

import android.content.Context;


import com.aclocationtrack.data.helper.NetworkHelper;
import com.aclocationtrack.data.listener.DataListener;
import com.aclocationtrack.data.model.ProfileDetails;
import com.aclocationtrack.data.model.ResetPassword;
import com.aclocationtrack.data.model.request.Deviceinfo;
import com.aclocationtrack.data.model.request.OrderRequest;
import com.aclocationtrack.data.model.request.StockProductInfoReq;
import com.aclocationtrack.data.model.request.StockProductSearchReq;
import com.aclocationtrack.data.model.request.TaskPost;
import com.aclocationtrack.data.model.request.UpdateProfile;
import com.aclocationtrack.data.model.response.Anouncement;
import com.aclocationtrack.data.model.response.AnouncementDetails;
import com.aclocationtrack.data.model.response.Downloads;
import com.aclocationtrack.data.model.response.LoginResponse;
import com.aclocationtrack.data.model.response.Logout;
import com.aclocationtrack.data.model.response.ProductSearch;
import com.aclocationtrack.data.model.response.StockProductSearch;
import com.aclocationtrack.data.model.response.UserDetailsResponse;
import com.aclocationtrack.data.pref.Pref;
import com.aclocationtrack.data.remote.RemoteDataSource;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;

/**
 * Created by yasar on 6/3/18.
 */

public class DataRepository implements DataSource {

    private static final String TAG = "DataRepository";
    private Context context;
    private RemoteDataSource remoteDataSource;
    private Pref preferences;

    public DataRepository(Context context, RemoteDataSource remoteDataSource, Pref preferences) {
        this.context = context;
        this.remoteDataSource = remoteDataSource;
        this.preferences = preferences;
    }

    @Override
    public Observable<LoginResponse> login(String json) {
        return remoteDataSource.login(json);
    }

    @Override
    public Observable<UserDetailsResponse> getUserProfile(Map<String, String> headermap) {
        return remoteDataSource.getUserProfile(headermap);
    }

    @Override
    public Observable<Anouncement> getAllAnnouncement(Map<String, String> headermap) {
        return remoteDataSource.getAllAnnouncement(headermap);
    }

    @Override
    public Observable<AnouncementDetails> getAnnouncementById(Map<String, String> headermap, int id) {
        return remoteDataSource.getAnnouncementById(headermap, id);
    }

    @Override
    public Observable<Downloads> getAllDownloads(Map<String, String> headermap) {
        return remoteDataSource.getAllDownloads(headermap);
    }

    @Override
    public Observable<String> postDeviceInfo(Map<String, String> headermap, Deviceinfo json) {
        return remoteDataSource.postDeviceInfo(headermap, json);
    }

    @Override
    public Observable<Logout> logout(Map<String, String> headermap) {
        return remoteDataSource.logout(headermap);
    }

    @Override
    public Observable<ProfileDetails> getProfile(Map<String, String> headermap) {
        return remoteDataSource.getProfile(headermap);
    }

    @Override
    public void saveCurrentDate(String d) {

        preferences.saveCurrentDate(d);
    }

    @Override
    public String getCurrentDate() {
        return preferences.getCurrentDate();
    }

    @Override
    public void punchEnabled(boolean isEnabled) {

        preferences.punchEnabled(isEnabled);
    }

    @Override
    public boolean isPunchEnabled() {
        return preferences.isPunchEnabled();
    }

    @Override
    public boolean isLoggedIn() {

        return preferences.isLoggedIn();

    }

    @Override
    public void saveSession(String accessToken, String tokenType, String role, String roleId, long userId, String name, String email, String mobileNo, String myOMobile) {
        preferences.saveSession(accessToken, tokenType, role, roleId, userId, name, email, mobileNo, myOMobile);
    }

    @Override
    public void saveAddress(String address1, String address2, String city, String state, String zipcode) {
        preferences.saveAddress(address1, address2, city, state, zipcode);
    }

    @Override
    public String getAddress1() {
        return preferences.getAddress1();
    }

    @Override
    public String getAddress2() {
        return preferences.getAddress2();
    }

    @Override
    public String getCity() {
        return preferences.getCity();
    }

    @Override
    public String getState() {
        return preferences.getState();
    }

    @Override
    public String getZipcode() {
        return preferences.getZipcode();
    }

    @Override
    public String getRoleType() {
        return preferences.getRoleType();
    }

    @Override
    public String getName() {
        return preferences.getName();
    }

    @Override
    public String getMobileNo() {
        return preferences.getMobileNo();
    }

    @Override
    public String getOMobileNo() {
        return preferences.getOMobileNo();

    }

    @Override
    public String getEmail() {
        return preferences.getEmail();
    }

    @Override
    public String getAccessToken() {
        return preferences.getAccessToken();
    }

    @Override
    public Map getUserDetails() {
        return preferences.getUserDetails();
    }

    @Override
    public Map getAuthendicate() {
        return preferences.getAuthendicate();
    }

    @Override
    public void saveTokenAndDeviceID(String token, String deviceId) {
        preferences.saveTokenAndDeviceID(token, deviceId);
    }

    @Override
    public Map<String, String> getTokenAndDeviceID() {
        return preferences.getTokenAndDeviceID();
    }

    @Override
    public void clear() {

        preferences.clear();
    }

    @Override
    public void savePush(boolean isNoti) {
        preferences.savePush(isNoti);
    }

    @Override
    public boolean getPush() {
        return preferences.getPush();
    }


}

