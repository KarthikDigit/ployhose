package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.aclocationtrack.utility.Utils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerServices implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<CustomerServices> CREATOR = new Creator<CustomerServices>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CustomerServices createFromParcel(Parcel in) {
            return new CustomerServices(in);
        }

        public CustomerServices[] newArray(int size) {
            return (new CustomerServices[size]);
        }

    };

    protected CustomerServices(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CustomerServices() {
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

        @SerializedName("current_page")
        @Expose
        private Integer currentPage;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;
        @SerializedName("first_page_url")
        @Expose
        private String firstPageUrl;
        @SerializedName("from")
        @Expose
        private Integer from;
        @SerializedName("last_page")
        @Expose
        private Integer lastPage;
        @SerializedName("last_page_url")
        @Expose
        private String lastPageUrl;
        @SerializedName("next_page_url")
        @Expose
        private Object nextPageUrl;
        @SerializedName("path")
        @Expose
        private String path;
        @SerializedName("per_page")
        @Expose
        private Integer perPage;
        @SerializedName("prev_page_url")
        @Expose
        private Object prevPageUrl;
        @SerializedName("to")
        @Expose
        private Integer to;
        @SerializedName("total")
        @Expose
        private Integer total;
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
            this.currentPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(this.data, (Datum.class.getClassLoader()));
            this.firstPageUrl = ((String) in.readValue((String.class.getClassLoader())));
            this.from = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.lastPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.lastPageUrl = ((String) in.readValue((String.class.getClassLoader())));
            this.nextPageUrl = ((Object) in.readValue((Object.class.getClassLoader())));
            this.path = ((String) in.readValue((String.class.getClassLoader())));
            this.perPage = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.prevPageUrl = ((Object) in.readValue((Object.class.getClassLoader())));
            this.to = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.total = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Data() {
        }

        public Integer getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

        public String getFirstPageUrl() {
            return firstPageUrl;
        }

        public void setFirstPageUrl(String firstPageUrl) {
            this.firstPageUrl = firstPageUrl;
        }

        public Integer getFrom() {
            return from;
        }

        public void setFrom(Integer from) {
            this.from = from;
        }

        public Integer getLastPage() {
            return lastPage;
        }

        public void setLastPage(Integer lastPage) {
            this.lastPage = lastPage;
        }

        public String getLastPageUrl() {
            return lastPageUrl;
        }

        public void setLastPageUrl(String lastPageUrl) {
            this.lastPageUrl = lastPageUrl;
        }

        public Object getNextPageUrl() {
            return nextPageUrl;
        }

        public void setNextPageUrl(Object nextPageUrl) {
            this.nextPageUrl = nextPageUrl;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Object getPrevPageUrl() {
            return prevPageUrl;
        }

        public void setPrevPageUrl(Object prevPageUrl) {
            this.prevPageUrl = prevPageUrl;
        }

        public Integer getTo() {
            return to;
        }

        public void setTo(Integer to) {
            this.to = to;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(currentPage);
            dest.writeList(data);
            dest.writeValue(firstPageUrl);
            dest.writeValue(from);
            dest.writeValue(lastPage);
            dest.writeValue(lastPageUrl);
            dest.writeValue(nextPageUrl);
            dest.writeValue(path);
            dest.writeValue(perPage);
            dest.writeValue(prevPageUrl);
            dest.writeValue(to);
            dest.writeValue(total);
        }

        public int describeContents() {
            return 0;
        }


        public static class Datum implements Parcelable {

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
            @SerializedName("address_1")
            @Expose
            private String address1;
            @SerializedName("address_2")
            @Expose
            private String address2;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Datum)) return false;

                Datum datum = (Datum) o;

                return id != null ? id.equals(datum.id) : datum.id == null;
            }

            @Override
            public int hashCode() {
                return id != null ? id.hashCode() : 0;
            }

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
            public final static Parcelable.Creator<Datum> CREATOR = new Creator<Datum>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public Datum createFromParcel(Parcel in) {
                    return new Datum(in);
                }

                public Datum[] newArray(int size) {
                    return (new Datum[size]);
                }

            };

            protected Datum(Parcel in) {
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
                this.complaint = ((String) in.readValue((String.class.getClassLoader())));
                this.preferTimeId = ((String) in.readValue((String.class.getClassLoader())));
                this.preferDate = ((String) in.readValue((String.class.getClassLoader())));
                this.comments = ((Object) in.readValue((Object.class.getClassLoader())));
                this.status = ((String) in.readValue((String.class.getClassLoader())));
                this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
                this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
                this.brandName = ((String) in.readValue((String.class.getClassLoader())));
                this.stateName = ((String) in.readValue((String.class.getClassLoader())));
                this.preferTime = ((String) in.readValue((String.class.getClassLoader())));
                this.problemType = ((String) in.readValue((String.class.getClassLoader())));
            }

            public Datum() {
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
                return Utils.getString(complaint);
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

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(userId);
                dest.writeValue(assignedBy);
                dest.writeValue(assignedTo);
                dest.writeValue(customerEmail);
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
                dest.writeValue(createdAt);
                dest.writeValue(updatedAt);
                dest.writeValue(brandName);
                dest.writeValue(stateName);
                dest.writeValue(preferTime);
                dest.writeValue(problemType);
            }

            public int describeContents() {
                return 0;
            }

        }

    }


}