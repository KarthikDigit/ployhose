package com.polyhose.data.remote;


import com.polyhose.data.model.request.ContactPersonRequest;
import com.polyhose.data.model.request.CustomerDetailsRequest;
import com.polyhose.data.model.request.CustomerRequest;
import com.polyhose.data.model.request.PunchOut;
import com.polyhose.data.model.request.TaskRequest;
import com.polyhose.data.model.request.UpdateCustomerDetailsRequest;
import com.polyhose.data.model.response.Attendance;
import com.polyhose.data.model.response.CompanyType;
import com.polyhose.data.model.response.ContactPerson;
import com.polyhose.data.model.response.CustomerApiResponse;
import com.polyhose.data.model.response.CustomerDetailsResponse;
import com.polyhose.data.model.response.CustomerType;
import com.polyhose.data.model.response.Customers;
import com.polyhose.data.model.ProfileDetails;
import com.polyhose.data.model.request.AnnouncemnetDetailRequest;
import com.polyhose.data.model.request.Deviceinfo;
import com.polyhose.data.model.request.LoginRequest;
import com.polyhose.data.model.request.LogoutRequest;
import com.polyhose.data.model.response.Anouncement;
import com.polyhose.data.model.response.AnouncementDetails;
import com.polyhose.data.model.response.Customer;
import com.polyhose.data.model.response.Downloads;
import com.polyhose.data.model.response.IndustrialType;
import com.polyhose.data.model.response.LoginApiResponse;
import com.polyhose.data.model.request.PunchIn;
import com.polyhose.data.model.response.PunchInResponse;
import com.polyhose.data.model.response.Region;
import com.polyhose.data.model.response.States;
import com.polyhose.data.model.response.Task;
import com.polyhose.data.model.response.TaskResponse;
import com.polyhose.data.model.response.UserDetailsResponse;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by yasar on 6/3/18.
 */

public interface ApiService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("CRMLogin/POST")
    Observable<LoginApiResponse> login(@Body LoginRequest json);

    //    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("auth/check")
//    Observable<MobileValidate> mobileValidate(@Body String json);
//
    @GET("user")
    Observable<UserDetailsResponse> getUserProfile(@HeaderMap Map<String, String> headermap);

    //
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("auth/signup")
//    Observable<RegisterResponse> register(@Body String json);
//
//    @GET("orders/create")
//    Observable<CreateOrderResponse> createOrder(@HeaderMap Map<String, String> headermap);
//
//    @GET("orders/create")
//    Observable<CreateOrderForDealer> createOrderForDealer(@HeaderMap Map<String, String> headermap);
//
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("productsearch")
//    Observable<String> searchProduct(@Body String json);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("productsearch")
//    Call<ProductSearch> searchProductDirect(@HeaderMap Map<String, String> headermap, @Body String json);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("productsearchinfo")
//    Observable<ProductInfo> getProductInfo(@HeaderMap Map<String, String> headermap, @Body String json);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("orders")
//    Observable<OrderConfirm> saveCustomerOrder(@HeaderMap Map<String, String> headermap, @Body OrderRequest json);
//
//    @GET("orders")
//    Observable<MyOrderResponse> loadMyOrder(@HeaderMap Map<String, String> headermap, @Query("page") String page, @Query("status") String status);
//
//    @GET("orders/{id}")
//    Observable<OrderDetailsInfo> getOrderDetails(@HeaderMap Map<String, String> headermap, @Path("id") int id);
//
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("productstocksearch")
//    Call<StockProductSearch> stockProductSearch(@HeaderMap Map<String, String> headermap, @Body StockProductSearchReq json);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("productstock")
//    Observable<StockProductInfo> stockProductInfo(@HeaderMap Map<String, String> headermap, @Body StockProductInfoReq json);
//


//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("api.php?action=addcustomer")
//    Observable<CustomerDetailsResponse> postCustomer(@Body CustomerDetailsRequest customerDetailsRequest);

    //

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("MyApiAttendance/PostIn")
    Observable<PunchInResponse> punchIN(@Body PunchIn punchIn);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("MyApiAttendance/PostOut")
    Observable<PunchInResponse> punchOUT(@Body PunchOut punchOut);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("CRMCustomer/Post")
    Observable<CustomerDetailsResponse> postCustomer(@Body CustomerDetailsRequest customerDetailsRequest);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("CRMCustomer/CustomerEdit")
    Observable<CustomerDetailsResponse> updateCustomer(@Body CustomerDetailsRequest updateCustomerDetailsRequest);


    @GET("CRMCustomerType/GetCoutomerType")
    Observable<List<CustomerType>> getCustomerType(@Query("ApiKey") String apiKey);

    @GET("CRMCompanyType/GetCompanyType")
    Observable<List<CompanyType>> getCompanyType(@Query("ApiKey") String apiKey);

    @GET("CRMIndustrialType/GetIndustrialType")
    Observable<List<IndustrialType>> getIndustrialType(@Query("ApiKey") String apiKey);

    @GET("CRMRegion/GetRegionDetails")
    Observable<List<Region>> getAllRegion(@Query("ApiKey") String apiKey);

    @GET("CRMStateList/GetStateList/{regionId}")
    Observable<List<States>> getAllStates(@Path("regionId") String regionId, @Query("ApiKey") String apiKey);

    @GET("CRMAttendance/Get/{userId}")
    Observable<List<Attendance>> getAllAttendance(@Path("userId") String userId, @Query("ApiKey") String apiKey);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("MyApiTask/Post")
    Observable<TaskResponse> postTask(@Body TaskRequest taskRequest);

    @GET("CRMTask/GetTaskDetails/{userId}")
    Observable<List<Task>> getAllTasks(@Path("userId") String userId, @Query("ApiKey") String apiKey);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("CRMCustomer/CustomerList")
    Observable<List<Customers>> getAllCustomers(@Body CustomerRequest customerRequest);

    @GET("CRMCustomer/Get/{customerId}")
    Observable<List<Customer>> getCustomerById(@Path("customerId") String customerId, @Query("ApiKey") String apiKey);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("CRMContactperson/ContactpersonList")
    Observable<List<ContactPerson>> getAllContactPerson(@Body ContactPersonRequest request);

    @GET("CRMAnnouncement/GetAnnouncementlist")
    Observable<List<Anouncement>> getAllAnnouncement(@Query("RoleId") String RoleId, @Query("RegionId") String RegionId, @Query("ApiKey") String apiKey);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("CRMAnnouncement/Post")
    Observable<List<AnouncementDetails>> getAnnouncementById(@Body AnnouncemnetDetailRequest request);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("CRMDownloadList/GetDownloadList")
    Observable<List<Downloads>> getAllDownloads(@Query("ApiKey") String apiKey);

    //    @Streaming
//    @GET
//    Call<ResponseBody> downloadFileWithDynamicUrlAsync(@Url String fileUrl);
//
//    @GET("services/create")
//    Observable<CreateService> createService(@HeaderMap Map<String, String> headermap);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("services")
//    Observable<PostedServicesRes> postService(@HeaderMap Map<String, String> headermap, @Body String json);
//
//    @GET("services")
//    Observable<CustomerServices> getAllServicesForCustomer(@HeaderMap Map<String, String> headermap, @Query("page") int page, @Query("status") int status);
//
//    @GET("services/{id}")
//    Observable<CustomerServicesDetails> getServiceDetailsForCustomer(@HeaderMap Map<String, String> headermap, @Path("id") int id);
//
//    @GET("services/{id}")
//    Observable<ServiceManServicesDetails> getServiceDetailsForServiceMan(@HeaderMap Map<String, String> headermap, @Path("id") int id);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @PUT("services/{id}")
//    Observable<String> changeServiceManStatus(@HeaderMap Map<String, String> headermap, @Path("id") int id, @Body String json);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("comment")
//    Observable<String> postComments(@HeaderMap Map<String, String> headermap, @Body String json);
//
//    @GET("tasks/create")
//    Observable<TaskAdd> getTask(@HeaderMap Map<String, String> headermap);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("tasks")
//    Observable<String> postTask(@HeaderMap Map<String, String> headermap, @Body TaskPost json);
//
//    @GET("tasks")
//    Observable<Tasks> getAllTask(@HeaderMap Map<String, String> headermap, @Query("page") int page, @Query("status") int status);
//
//    @GET("tasks/{id}")
//    Observable<TasksDetails> getTaskDetails(@HeaderMap Map<String, String> headermap, @Path("id") int id);
//
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("deviceinfo")
    Observable<String> postDeviceInfo(@HeaderMap Map<String, String> headermap, @Body Deviceinfo json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("logout")
    Observable<String> logout(@Body LogoutRequest logoutRequest);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("profile")
    Observable<ProfileDetails> getProfile(@HeaderMap Map<String, String> headermap);

//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("profile")
//    Observable<String> updateProfile(@HeaderMap Map<String, String> headermap, @Body UpdateProfile json);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("auth/resetpassword")
//    Observable<ResponsePassword> resetPassword(@Body ResetPassword json);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("send-otp")
//    Observable<OTPSendApi> generateOtp(@Body String json);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("verify-otp")
//    Observable<OTPValidateApi> validateOtp(@Body String json);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("taskstatus-update")
//    Observable<TaskStatusChange> taskStatusUpdate(@HeaderMap Map<String, String> headermap, @Body String json);
//
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @GET("brands/{id}")
//    Observable<Brands> fetchAllBrand(@HeaderMap Map<String, String> headermap, @Path("id") int category_id);


}
