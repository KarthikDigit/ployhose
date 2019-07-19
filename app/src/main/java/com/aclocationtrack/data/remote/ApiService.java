package com.aclocationtrack.data.remote;


import com.aclocationtrack.data.model.ProfileDetails;
import com.aclocationtrack.data.model.ResetPassword;
import com.aclocationtrack.data.model.ResponsePassword;
import com.aclocationtrack.data.model.request.Deviceinfo;
import com.aclocationtrack.data.model.request.OrderRequest;
import com.aclocationtrack.data.model.request.StockProductInfoReq;
import com.aclocationtrack.data.model.request.StockProductSearchReq;
import com.aclocationtrack.data.model.request.TaskPost;
import com.aclocationtrack.data.model.request.UpdateProfile;
import com.aclocationtrack.data.model.response.Anouncement;
import com.aclocationtrack.data.model.response.AnouncementDetails;
import com.aclocationtrack.data.model.response.Brands;
import com.aclocationtrack.data.model.response.CreateOrderForDealer;
import com.aclocationtrack.data.model.response.CreateService;
import com.aclocationtrack.data.model.response.CustomerServices;
import com.aclocationtrack.data.model.response.CustomerServicesDetails;
import com.aclocationtrack.data.model.response.Downloads;
import com.aclocationtrack.data.model.response.LoginResponse;
import com.aclocationtrack.data.model.response.Logout;
import com.aclocationtrack.data.model.response.MobileValidate;
import com.aclocationtrack.data.model.response.MyOrderResponse;
import com.aclocationtrack.data.model.response.OTPSendApi;
import com.aclocationtrack.data.model.response.OTPValidateApi;
import com.aclocationtrack.data.model.response.OrderConfirm;
import com.aclocationtrack.data.model.response.OrderDetailsInfo;
import com.aclocationtrack.data.model.response.PostedServicesRes;
import com.aclocationtrack.data.model.response.ProductInfo;
import com.aclocationtrack.data.model.response.ProductSearch;
import com.aclocationtrack.data.model.response.RegisterResponse;
import com.aclocationtrack.data.model.response.ServiceManServicesDetails;
import com.aclocationtrack.data.model.response.StockProductInfo;
import com.aclocationtrack.data.model.response.StockProductSearch;
import com.aclocationtrack.data.model.response.TaskAdd;
import com.aclocationtrack.data.model.response.TaskStatusChange;
import com.aclocationtrack.data.model.response.Tasks;
import com.aclocationtrack.data.model.response.TasksDetails;
import com.aclocationtrack.data.model.response.UserDetailsResponse;
import com.aclocationtrack.data.model.response.createorder.CreateOrderResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by yasar on 6/3/18.
 */

public interface ApiService {

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("auth/login")
    Observable<LoginResponse> login(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("auth/check")
    Observable<MobileValidate> mobileValidate(@Body String json);

    @GET("user")
    Observable<UserDetailsResponse> getUserProfile(@HeaderMap Map<String, String> headermap);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("auth/signup")
    Observable<RegisterResponse> register(@Body String json);

    @GET("orders/create")
    Observable<CreateOrderResponse> createOrder(@HeaderMap Map<String, String> headermap);

    @GET("orders/create")
    Observable<CreateOrderForDealer> createOrderForDealer(@HeaderMap Map<String, String> headermap);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("productsearch")
    Observable<String> searchProduct(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("productsearch")
    Call<ProductSearch> searchProductDirect(@HeaderMap Map<String, String> headermap, @Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("productsearchinfo")
    Observable<ProductInfo> getProductInfo(@HeaderMap Map<String, String> headermap, @Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("orders")
    Observable<OrderConfirm> saveCustomerOrder(@HeaderMap Map<String, String> headermap, @Body OrderRequest json);

    @GET("orders")
    Observable<MyOrderResponse> loadMyOrder(@HeaderMap Map<String, String> headermap, @Query("page") String page, @Query("status") String status);

    @GET("orders/{id}")
    Observable<OrderDetailsInfo> getOrderDetails(@HeaderMap Map<String, String> headermap, @Path("id") int id);


    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("productstocksearch")
    Call<StockProductSearch> stockProductSearch(@HeaderMap Map<String, String> headermap, @Body StockProductSearchReq json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("productstock")
    Observable<StockProductInfo> stockProductInfo(@HeaderMap Map<String, String> headermap, @Body StockProductInfoReq json);

    @GET("announcements")
    Observable<Anouncement> loadAnnouncement(@HeaderMap Map<String, String> headermap);

    @GET("announcements/{id}")
    Observable<AnouncementDetails> loadAnnouncementDetails(@HeaderMap Map<String, String> headermap, @Path("id") int id);

    @GET("downloads")
    Observable<Downloads> loadDownloads(@HeaderMap Map<String, String> headermap);

    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlAsync(@Url String fileUrl);

    @GET("services/create")
    Observable<CreateService> createService(@HeaderMap Map<String, String> headermap);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("services")
    Observable<PostedServicesRes> postService(@HeaderMap Map<String, String> headermap, @Body String json);

    @GET("services")
    Observable<CustomerServices> getAllServicesForCustomer(@HeaderMap Map<String, String> headermap, @Query("page") int page, @Query("status") int status);

    @GET("services/{id}")
    Observable<CustomerServicesDetails> getServiceDetailsForCustomer(@HeaderMap Map<String, String> headermap, @Path("id") int id);

    @GET("services/{id}")
    Observable<ServiceManServicesDetails> getServiceDetailsForServiceMan(@HeaderMap Map<String, String> headermap, @Path("id") int id);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @PUT("services/{id}")
    Observable<String> changeServiceManStatus(@HeaderMap Map<String, String> headermap, @Path("id") int id, @Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("comment")
    Observable<String> postComments(@HeaderMap Map<String, String> headermap, @Body String json);

    @GET("tasks/create")
    Observable<TaskAdd> getTask(@HeaderMap Map<String, String> headermap);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("tasks")
    Observable<String> postTask(@HeaderMap Map<String, String> headermap, @Body TaskPost json);

    @GET("tasks")
    Observable<Tasks> getAllTask(@HeaderMap Map<String, String> headermap, @Query("page") int page, @Query("status") int status);

    @GET("tasks/{id}")
    Observable<TasksDetails> getTaskDetails(@HeaderMap Map<String, String> headermap, @Path("id") int id);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("deviceinfo")
    Observable<String> postDeviceInfo(@HeaderMap Map<String, String> headermap, @Body Deviceinfo json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("logout")
    Observable<Logout> logout(@HeaderMap Map<String, String> headermap);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("profile")
    Observable<ProfileDetails> getProfile(@HeaderMap Map<String, String> headermap);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("profile")
    Observable<String> updateProfile(@HeaderMap Map<String, String> headermap, @Body UpdateProfile json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("auth/resetpassword")
    Observable<ResponsePassword> resetPassword(@Body ResetPassword json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("send-otp")
    Observable<OTPSendApi> generateOtp(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("verify-otp")
    Observable<OTPValidateApi> validateOtp(@Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("taskstatus-update")
    Observable<TaskStatusChange> taskStatusUpdate(@HeaderMap Map<String, String> headermap, @Body String json);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("brands/{id}")
    Observable<Brands> fetchAllBrand(@HeaderMap Map<String, String> headermap, @Path("id") int category_id);


}
