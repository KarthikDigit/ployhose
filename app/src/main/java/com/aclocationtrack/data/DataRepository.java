package com.aclocationtrack.data;

import android.content.Context;


import com.aclocationtrack.data.helper.NetworkHelper;
import com.aclocationtrack.data.listener.DataListener;
import com.aclocationtrack.data.model.ResetPassword;
import com.aclocationtrack.data.model.request.Deviceinfo;
import com.aclocationtrack.data.model.request.OrderRequest;
import com.aclocationtrack.data.model.request.StockProductInfoReq;
import com.aclocationtrack.data.model.request.StockProductSearchReq;
import com.aclocationtrack.data.model.request.TaskPost;
import com.aclocationtrack.data.model.request.UpdateProfile;
import com.aclocationtrack.data.model.response.ProductSearch;
import com.aclocationtrack.data.model.response.StockProductSearch;
import com.aclocationtrack.data.pref.Pref;
import com.aclocationtrack.data.remote.RemoteDataSource;

import java.util.Map;

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

    private TypeRequest typeRequest;

    private enum TypeRequest {

        LOGIN, PROFILE, ALL_PROFILE, OTHERS;

    }


    public DataRepository(Context context, RemoteDataSource remoteDataSource, Pref preferences) {
        this.context = context;
        this.remoteDataSource = remoteDataSource;
        this.preferences = preferences;
    }


    public void login(String json, final DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.LOGIN;
            remoteDataSource.login(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void mobileValidate(String json, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.LOGIN;

            remoteDataSource.mobileValidate(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void getUserProfile(@HeaderMap Map<String, String> map, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.PROFILE;
            remoteDataSource.getUserProfile(map, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void register(String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.register(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void createOrder(Map<String, String> map, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.createOrder(map, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void createOrderForDealer(Map<String, String> map, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.createOrderForDealer(map, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void searchProduc(String json, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.searchProduc(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public Call<ProductSearch> searchProductDirect(@HeaderMap Map<String, String> headermap, String json) {
        return remoteDataSource.searchProductDirect(headermap, json);
    }

    @Override
    public void getProductInfo(Map<String, String> headermap, String json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getProductInfo(headermap, json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void saveCustomerOrder(Map<String, String> headermap, OrderRequest json, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.saveCustomerOrder(headermap, json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void loadMyOrder(Map<String, String> headermap, String page, String status, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.loadMyOrder(headermap, page, status, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void getOrderDetails(Map<String, String> headermap, int page, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getOrderDetails(headermap, page, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public Call<StockProductSearch> stockProductSearch(Map<String, String> headermap, StockProductSearchReq json) {

        return remoteDataSource.stockProductSearch(headermap, json);
    }

    @Override
    public void stockProductInfo(Map<String, String> headermap, StockProductInfoReq json, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.stockProductInfo(headermap, json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void loadAnnouncement(Map<String, String> headermap, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.loadAnnouncement(headermap, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void loadAnnouncementDetails(Map<String, String> headermap, int id, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.loadAnnouncementDetails(headermap, id, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void loadDownloads(Map<String, String> headermap, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.loadDownloads(headermap, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public Call<ResponseBody> downloadFileWithDynamicUrlAsync(String fileUrl) {
        return remoteDataSource.downloadFileWithDynamicUrlAsync(fileUrl);
    }

    @Override
    public void createService(Map<String, String> headermap, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.createService(headermap, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }


    }

    @Override
    public void postService(Map<String, String> headermap, String json, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.postService(headermap, json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }


    }

    @Override
    public void getAllServicesForCustomer(Map<String, String> headermap, int page, int status, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getAllServicesForCustomer(headermap, page, status, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void getServiceDetailsForCustomer(Map<String, String> headermap, int id, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getServiceDetailsForCustomer(headermap, id, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }


    }

    @Override
    public void getServiceDetailsForServiceMan(Map<String, String> headermap, int id, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getServiceDetailsForServiceMan(headermap, id, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }


    }

    @Override
    public void changeServiceManStatus(Map<String, String> headermap, int id, String json, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.changeServiceManStatus(headermap, id, json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }


    }

    @Override
    public void postComments(Map<String, String> headermap, String json, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.postComments(headermap, json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }


    }

    @Override
    public void getTask(Map<String, String> headermap, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getTask(headermap, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void postTask(Map<String, String> headermap, TaskPost json, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {
            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.postTask(headermap, json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void getAllTask(Map<String, String> headermap, int page, int status, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getAllTask(headermap, page, status, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }


    }

    @Override
    public void getTaskDetails(Map<String, String> headermap, int id, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getTaskDetails(headermap, id, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void postDeviceInfo(Map<String, String> headermap, Deviceinfo json, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.postDeviceInfo(headermap, json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void logout(Map<String, String> headermap, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.logout(headermap, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void getProfile(Map<String, String> headermap, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.getProfile(headermap, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void updateProfile(Map<String, String> headermap, UpdateProfile json, DataListener dataListener) {

        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.updateProfile(headermap, json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }

    }

    @Override
    public void changePassword(ResetPassword json, DataListener dataListener) {


        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.changePassword(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }


    }

    @Override
    public void generateOtp(String json, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.generateOtp(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void validateOtp(String json, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.validateOtp(json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void taskStatusUpdate(@HeaderMap Map<String, String> headermap, String json, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.taskStatusUpdate(headermap, json, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    @Override
    public void fetchAllBrand(Map<String, String> headermap, int cateogry_id, DataListener dataListener) {
        if (NetworkHelper.isNetworkAvailable(context)) {

            typeRequest = TypeRequest.OTHERS;
            remoteDataSource.fetchAllBrand(headermap, cateogry_id, setDataListener(dataListener, typeRequest));

        } else {

            dataListener.onNetworkFailure();

        }
    }

    private DataListener setDataListener(final DataListener dataListener, final TypeRequest typeRequest) {

        return new DataListener() {
            @Override
            public void onSuccess(Object object) {


                parserData(object, dataListener, typeRequest);


            }

            @Override
            public void onFail(Throwable s) {
                dataListener.onFail(s);
            }

            @Override
            public void onNetworkFailure() {
                dataListener.onNetworkFailure();
            }
        };
    }


    private <T> void parserData(T o, DataListener dataListener, TypeRequest typeRequest) {

//        switch (typeRequest) {
//
//            case LOGIN:
//
//                Parser.loginParse(o.toString(), dataListener, this);
//
//                break;
//
//            case PROFILE:
//
//                Parser.profileParse(o.toString(), dataListener, this);
//
//                break;
//
//            case ALL_PROFILE:
//
//                Parser.allProfileParse(o.toString(), dataListener, this);
//
//                break;
//
//            case OTHERS:

        dataListener.onSuccess(o);

//                break;
//        }


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

