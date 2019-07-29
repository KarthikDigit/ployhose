package com.polyhose.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.polyhose.utility.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerServicesDetails implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<CustomerServicesDetails> CREATOR = new Creator<CustomerServicesDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CustomerServicesDetails createFromParcel(Parcel in) {
            return new CustomerServicesDetails(in);
        }

        public CustomerServicesDetails[] newArray(int size) {
            return (new CustomerServicesDetails[size]);
        }

    };

    protected CustomerServicesDetails(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CustomerServicesDetails() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(data);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }


    public static class Data implements Parcelable {

        @SerializedName("service_comments")
        @Expose
        private List<ServiceComment> serviceComments = null;
        @SerializedName("service")
        @Expose
        private Service service;
        public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            public Data[] newArray(int size) {
                return (new Data[size]);
            }

        };

        protected Data(Parcel in) {
            in.readList(this.serviceComments, (ServiceComment.class.getClassLoader()));
            this.service = ((Service) in.readValue((Service.class.getClassLoader())));
        }

        public Data() {
        }

        public List<ServiceComment> getServiceComments() {
            return serviceComments;
        }

        public void setServiceComments(List<ServiceComment> serviceComments) {
            this.serviceComments = serviceComments;
        }

        public Service getService() {
            return service;
        }

        public void setService(Service service) {
            this.service = service;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(serviceComments);
            dest.writeValue(service);
        }

        public int describeContents() {
            return 0;
        }


        public static class Service implements Parcelable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("assigned_by")
            @Expose
            private Object assignedBy;
            @SerializedName("assigned_to")
            @Expose
            private Object assignedTo;
            @SerializedName("customer_email")
            @Expose
            private String customerEmail;
            @SerializedName("customer_name")
            @Expose
            private String customerName;
            @SerializedName("address_1")
            @Expose
            private String address1;
            @SerializedName("address_2")
            @Expose
            private String address2;
            @SerializedName("city")
            @Expose
            private String city;
            @SerializedName("state_id")
            @Expose
            private String stateId;
            @SerializedName("zipcode")
            @Expose
            private String zipcode;
            @SerializedName("mobile_no")
            @Expose
            private String mobileNo;
            @SerializedName("brand_id")
            @Expose
            private String brandId;
            @SerializedName("model_name")
            @Expose
            private String modelName;
            @SerializedName("ton_capacity")
            @Expose
            private String tonCapacity;
            @SerializedName("problem_type_id")
            @Expose
            private String problemTypeId;
            @SerializedName("place_at_home")
            @Expose
            private String placeAtHome;
            @SerializedName("complaint")
            @Expose
            private String complaint;
            @SerializedName("prefer_time_id")
            @Expose
            private String preferTimeId;
            @SerializedName("prefer_date")
            @Expose
            private String preferDate;
            @SerializedName("comments")
            @Expose
            private Object comments;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("lng")
            @Expose
            private String lng;
            @SerializedName("lat")
            @Expose
            private String lat;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("brand_name")
            @Expose
            private String brandName;
            @SerializedName("state_name")
            @Expose
            private String stateName;
            @SerializedName("prefer_time")
            @Expose
            private String preferTime;
            @SerializedName("problem_type")
            @Expose
            private String problemType;
            @SerializedName("attended_at")
            @Expose
            private String attended_at;
            public final static Parcelable.Creator<Service> CREATOR = new Creator<Service>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public Service createFromParcel(Parcel in) {
                    return new Service(in);
                }

                public Service[] newArray(int size) {
                    return (new Service[size]);
                }

            };

            protected Service(Parcel in) {
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.userId = ((String) in.readValue((String.class.getClassLoader())));
                this.assignedBy = ((Object) in.readValue((Object.class.getClassLoader())));
                this.assignedTo = ((Object) in.readValue((Object.class.getClassLoader())));
                this.customerEmail = ((String) in.readValue((String.class.getClassLoader())));
                this.address1 = ((String) in.readValue((String.class.getClassLoader())));
                this.address2 = ((String) in.readValue((String.class.getClassLoader())));
                this.city = ((String) in.readValue((String.class.getClassLoader())));
                this.stateId = ((String) in.readValue((String.class.getClassLoader())));
                this.zipcode = ((String) in.readValue((String.class.getClassLoader())));
                this.mobileNo = ((String) in.readValue((String.class.getClassLoader())));
                this.brandId = ((String) in.readValue((String.class.getClassLoader())));
                this.modelName = ((String) in.readValue((String.class.getClassLoader())));
                this.tonCapacity = ((String) in.readValue((String.class.getClassLoader())));
                this.problemTypeId = ((String) in.readValue((String.class.getClassLoader())));
                this.placeAtHome = ((String) in.readValue((String.class.getClassLoader())));
                this.complaint = ((String) in.readValue((Object.class.getClassLoader())));
                this.preferTimeId = ((String) in.readValue((String.class.getClassLoader())));
                this.preferDate = ((String) in.readValue((String.class.getClassLoader())));
                this.comments = ((Object) in.readValue((Object.class.getClassLoader())));
                this.status = ((String) in.readValue((String.class.getClassLoader())));
                this.lng = ((String) in.readValue((String.class.getClassLoader())));
                this.lat = ((String) in.readValue((String.class.getClassLoader())));
                this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
                this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
                this.brandName = ((String) in.readValue((String.class.getClassLoader())));
                this.stateName = ((String) in.readValue((String.class.getClassLoader())));
                this.preferTime = ((String) in.readValue((String.class.getClassLoader())));
                this.problemType = ((String) in.readValue((String.class.getClassLoader())));
                this.customerName = ((String) in.readValue((String.class.getClassLoader())));
                this.attended_at = ((String) in.readValue((String.class.getClassLoader())));
            }

            public Service() {
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getUserId() {
                return Utils.getString(userId);
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public Object getAssignedBy() {
                return assignedBy;
            }

            public void setAssignedBy(Object assignedBy) {
                this.assignedBy = assignedBy;
            }

            public Object getAssignedTo() {
                return assignedTo;
            }

            public void setAssignedTo(Object assignedTo) {
                this.assignedTo = assignedTo;
            }

            public String getCustomerEmail() {
                return Utils.getString(customerEmail);
            }

            public void setCustomerEmail(String customerEmail) {
                this.customerEmail = customerEmail;
            }

            public String getAddress1() {
                return Utils.getString(address1);
            }

            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            public String getAddress2() {
                return Utils.getString(address2);
            }

            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            public String getCity() {
                return Utils.getString(city);
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getStateId() {
                return Utils.getString(stateId);
            }

            public void setStateId(String stateId) {
                this.stateId = stateId;
            }

            public String getZipcode() {
                return Utils.getString(zipcode);
            }

            public void setZipcode(String zipcode) {
                this.zipcode = zipcode;
            }

            public String getMobileNo() {
                return Utils.getString(mobileNo);
            }

            public void setMobileNo(String mobileNo) {
                this.mobileNo = mobileNo;
            }

            public String getBrandId() {
                return Utils.getString(brandId);
            }

            public void setBrandId(String brandId) {
                this.brandId = brandId;
            }

            public String getModelName() {
                return Utils.getString(modelName);
            }

            public void setModelName(String modelName) {
                this.modelName = modelName;
            }

            public String getTonCapacity() {
                return Utils.getString(tonCapacity);
            }

            public void setTonCapacity(String tonCapacity) {
                this.tonCapacity = tonCapacity;
            }

            public String getProblemTypeId() {
                return Utils.getString(problemTypeId);
            }

            public void setProblemTypeId(String problemTypeId) {
                this.problemTypeId = problemTypeId;
            }

            public String getPlaceAtHome() {
                return Utils.getString(placeAtHome);
            }

            public void setPlaceAtHome(String placeAtHome) {
                this.placeAtHome = placeAtHome;
            }

            public String getComplaint() {
                return complaint;
            }

            public void setComplaint(String complaint) {
                this.complaint = complaint;
            }

            public String getPreferTimeId() {
                return Utils.getString(preferTimeId);
            }

            public void setPreferTimeId(String preferTimeId) {
                this.preferTimeId = preferTimeId;
            }

            public String getPreferDate() {
                return Utils.getString(preferDate);
            }

            public void setPreferDate(String preferDate) {
                this.preferDate = preferDate;
            }

            public Object getComments() {
                return comments;
            }

            public void setComments(Object comments) {
                this.comments = comments;
            }

            public String getStatus() {
                return Utils.getString(status);
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getCreatedAt() {
                return Utils.getString(createdAt);
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return Utils.getString(updatedAt);
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getBrandName() {
                return Utils.getString(brandName);
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getStateName() {
                return Utils.getString(stateName);
            }

            public void setStateName(String stateName) {
                this.stateName = stateName;
            }

            public String getPreferTime() {
                return Utils.getString(preferTime);
            }

            public void setPreferTime(String preferTime) {
                this.preferTime = preferTime;
            }

            public String getProblemType() {
                return Utils.getString(problemType);
            }

            public void setProblemType(String problemType) {
                this.problemType = problemType;
            }

            public String getCustomerName() {
                return Utils.getString(customerName);
            }

            public void setCustomerName(String customerName) {
                this.customerName = customerName;
            }

            public String getAttended_at() {
                return Utils.getString(attended_at);
            }

            public void setAttended_at(String attended_at) {
                this.attended_at = attended_at;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(userId);
                dest.writeValue(assignedBy);
                dest.writeValue(assignedTo);
                dest.writeValue(customerEmail);
                dest.writeValue(customerName);
                dest.writeValue(address1);
                dest.writeValue(address2);
                dest.writeValue(city);
                dest.writeValue(stateId);
                dest.writeValue(zipcode);
                dest.writeValue(mobileNo);
                dest.writeValue(brandId);
                dest.writeValue(modelName);
                dest.writeValue(tonCapacity);
                dest.writeValue(problemTypeId);
                dest.writeValue(placeAtHome);
                dest.writeValue(complaint);
                dest.writeValue(preferTimeId);
                dest.writeValue(preferDate);
                dest.writeValue(comments);
                dest.writeValue(status);
                dest.writeValue(lng);
                dest.writeValue(lat);
                dest.writeValue(createdAt);
                dest.writeValue(updatedAt);
                dest.writeValue(brandName);
                dest.writeValue(stateName);
                dest.writeValue(preferTime);
                dest.writeValue(problemType);
                dest.writeValue(attended_at);
            }

            public int describeContents() {
                return 0;
            }

        }


        public static class ServiceComment implements Parcelable {

            @SerializedName("comment")
            @Expose
            private String comment;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("user_id")
            @Expose
            private String userId;

            @SerializedName("comment_datetime")
            @Expose
            private String comment_datetime;

            public final static Parcelable.Creator<ServiceComment> CREATOR = new Creator<ServiceComment>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public ServiceComment createFromParcel(Parcel in) {
                    return new ServiceComment(in);
                }

                public ServiceComment[] newArray(int size) {
                    return (new ServiceComment[size]);
                }

            };

            protected ServiceComment(Parcel in) {
                this.comment = ((String) in.readValue((String.class.getClassLoader())));
                this.name = ((String) in.readValue((String.class.getClassLoader())));
                this.userId = ((String) in.readValue((String.class.getClassLoader())));
                this.comment_datetime = ((String) in.readValue((String.class.getClassLoader())));
            }

            public ServiceComment() {
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getName() {
                return name;
            }

            public String getComment_datetime() {
                return comment_datetime;
            }

            public void setComment_datetime(String comment_datetime) {
                this.comment_datetime = comment_datetime;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(comment);
                dest.writeValue(name);
                dest.writeValue(userId);
                dest.writeValue(comment_datetime);
            }

            public int describeContents() {
                return 0;
            }

        }


    }


}