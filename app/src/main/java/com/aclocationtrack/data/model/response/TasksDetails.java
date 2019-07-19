package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TasksDetails implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<TasksDetails> CREATOR = new Creator<TasksDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TasksDetails createFromParcel(Parcel in) {
            return new TasksDetails(in);
        }

        public TasksDetails[] newArray(int size) {
            return (new TasksDetails[size]);
        }

    };

    protected TasksDetails(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TasksDetails() {
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

        @SerializedName("task_comments")
        @Expose
        private List<TaskComment> taskComments = null;
        @SerializedName("task_performs")
        @Expose
        private List<TaskPerform> taskPerforms = null;
        @SerializedName("task")
        @Expose
        private Task task;
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
            in.readList(this.taskComments, (TaskComment.class.getClassLoader()));
            in.readList(this.taskPerforms, (TaskPerform.class.getClassLoader()));
            this.task = ((Task) in.readValue((Task.class.getClassLoader())));
        }

        public Data() {
        }

        public List<TaskComment> getTaskComments() {
            return taskComments;
        }

        public void setTaskComments(List<TaskComment> taskComments) {
            this.taskComments = taskComments;
        }

        public List<TaskPerform> getTaskPerforms() {
            return taskPerforms;
        }

        public void setTaskPerforms(List<TaskPerform> taskPerforms) {
            this.taskPerforms = taskPerforms;
        }

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            this.task = task;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(taskComments);
            dest.writeList(taskPerforms);
            dest.writeValue(task);
        }

        public int describeContents() {
            return 0;
        }


        public static class Task implements Parcelable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("dealer")
            @Expose
            private String dealer_name;
            @SerializedName("assigned_by")
            @Expose
            private String assignedBy;
            @SerializedName("assigned_to")
            @Expose
            private String assignedTo;
            @SerializedName("task_date")
            @Expose
            private String taskDate;
            @SerializedName("remarks")
            @Expose
            private String remarks;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("lng")
            @Expose
            private String lng;
            @SerializedName("lat")
            @Expose
            private String lat;

            public String getDealer_name() {
                return dealer_name;
            }

            public void setDealer_name(String dealer_name) {
                this.dealer_name = dealer_name;
            }

            @SerializedName("created_at")

            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private Object updatedAt;
            public final static Parcelable.Creator<Task> CREATOR = new Creator<Task>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public Task createFromParcel(Parcel in) {
                    return new Task(in);
                }

                public Task[] newArray(int size) {
                    return (new Task[size]);
                }

            };

            protected Task(Parcel in) {
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.userId = ((String) in.readValue((String.class.getClassLoader())));
                this.dealer_name = ((String) in.readValue((String.class.getClassLoader())));
                this.assignedBy = ((String) in.readValue((String.class.getClassLoader())));
                this.assignedTo = ((String) in.readValue((String.class.getClassLoader())));
                this.taskDate = ((String) in.readValue((String.class.getClassLoader())));
                this.remarks = ((String) in.readValue((Object.class.getClassLoader())));
                this.status = ((String) in.readValue((String.class.getClassLoader())));
                this.lng = ((String) in.readValue((String.class.getClassLoader())));
                this.lat = ((String) in.readValue((String.class.getClassLoader())));
                this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
                this.updatedAt = ((Object) in.readValue((Object.class.getClassLoader())));
            }

            public Task() {
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getAssignedBy() {
                return assignedBy;
            }

            public void setAssignedBy(String assignedBy) {
                this.assignedBy = assignedBy;
            }

            public String getAssignedTo() {
                return assignedTo;
            }

            public void setAssignedTo(String assignedTo) {
                this.assignedTo = assignedTo;
            }

            public String getTaskDate() {
                return taskDate;
            }

            public void setTaskDate(String taskDate) {
                this.taskDate = taskDate;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getStatus() {
                return status;
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
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public Object getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(Object updatedAt) {
                this.updatedAt = updatedAt;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(userId);
                dest.writeValue(dealer_name);
                dest.writeValue(assignedBy);
                dest.writeValue(assignedTo);
                dest.writeValue(taskDate);
                dest.writeValue(remarks);
                dest.writeValue(status);
                dest.writeValue(lng);
                dest.writeValue(lat);
                dest.writeValue(createdAt);
                dest.writeValue(updatedAt);
            }

            public int describeContents() {
                return 0;
            }

        }


        public static class TaskComment implements Parcelable {

            @SerializedName("comment")
            @Expose
            private String comment;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("user_id")
            @Expose
            private String userId;

            public String getComment_datetime() {
                return comment_datetime;
            }

            public void setComment_datetime(String comment_datetime) {
                this.comment_datetime = comment_datetime;
            }

            @SerializedName("comment_datetime")

            @Expose
            private String comment_datetime;
            public final static Parcelable.Creator<TaskComment> CREATOR = new Creator<TaskComment>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public TaskComment createFromParcel(Parcel in) {
                    return new TaskComment(in);
                }

                public TaskComment[] newArray(int size) {
                    return (new TaskComment[size]);
                }

            };

            protected TaskComment(Parcel in) {
                this.comment = ((String) in.readValue((String.class.getClassLoader())));
                this.name = ((String) in.readValue((String.class.getClassLoader())));
                this.userId = ((String) in.readValue((String.class.getClassLoader())));
                this.comment_datetime = ((String) in.readValue((String.class.getClassLoader())));
            }

            public TaskComment() {
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


        public static class TaskPerform implements Parcelable {

            @SerializedName("perform")
            @Expose
            private String perform;
            @SerializedName("perform_desc")
            @Expose
            private String performDesc;
            public final static Parcelable.Creator<TaskPerform> CREATOR = new Creator<TaskPerform>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public TaskPerform createFromParcel(Parcel in) {
                    return new TaskPerform(in);
                }

                public TaskPerform[] newArray(int size) {
                    return (new TaskPerform[size]);
                }

            };

            protected TaskPerform(Parcel in) {
                this.perform = ((String) in.readValue((String.class.getClassLoader())));
                this.performDesc = ((String) in.readValue((Object.class.getClassLoader())));
            }

            public TaskPerform() {
            }

            public String getPerform() {
                return perform;
            }

            public void setPerform(String perform) {
                this.perform = perform;
            }

            public String getPerformDesc() {
                return performDesc;
            }

            public void setPerformDesc(String performDesc) {
                this.performDesc = performDesc;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(perform);
                dest.writeValue(performDesc);
            }

            public int describeContents() {
                return 0;
            }

        }


    }


}