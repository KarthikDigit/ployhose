package com.aclocationtrack.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TaskStatusChange implements Serializable {

    @SerializedName("success")
    @Expose
    public Boolean success;
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("message")
    @Expose
    public String message;


    public static class Data implements Serializable {

        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("user_id")
        @Expose
        public Integer userId;
        @SerializedName("assigned_by")
        @Expose
        public Integer assignedBy;
        @SerializedName("assigned_to")
        @Expose
        public Integer assignedTo;
        @SerializedName("task_date")
        @Expose
        public String taskDate;
        @SerializedName("remarks")
        @Expose
        public String remarks;
        @SerializedName("status")
        @Expose
        public Integer status;
        @SerializedName("lng")
        @Expose
        public String lng;
        @SerializedName("lat")
        @Expose
        public String lat;
        @SerializedName("location")
        @Expose
        public Object location;
        @SerializedName("created_at")
        @Expose
        public String createdAt;
        @SerializedName("updated_at")
        @Expose
        public String updatedAt;


        public int position;

    }
}
