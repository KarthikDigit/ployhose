package com.aclocationtrack.data.model.response;

import com.aclocationtrack.data.model.response.createorder.Brand;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Brands implements Serializable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Brand> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = 7059825828438304403L;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Brand> getData() {
        return data;
    }

    public void setData(List<Brand> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


//    public static class Datum implements Serializable
//    {
//
//        @SerializedName("id")
//        @Expose
//        private Integer id;
//        @SerializedName("brand_name")
//        @Expose
//        private String brandName;
//        private final static long serialVersionUID = 9150953077240405930L;
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getBrandName() {
//            return brandName;
//        }
//
//        public void setBrandName(String brandName) {
//            this.brandName = brandName;
//        }
//
//    }

}