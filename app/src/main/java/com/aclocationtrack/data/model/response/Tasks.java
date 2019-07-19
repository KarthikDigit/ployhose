package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tasks implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<Tasks> CREATOR = new Creator<Tasks>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Tasks createFromParcel(Parcel in) {
            return new Tasks(in);
        }

        public Tasks[] newArray(int size) {
            return (new Tasks[size]);
        }

    };

    protected Tasks(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Tasks() {
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
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private Object updatedAt;

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

            @SerializedName("name")

            @Expose
            private String name;
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
                this.assignedBy = ((String) in.readValue((String.class.getClassLoader())));
                this.assignedTo = ((String) in.readValue((String.class.getClassLoader())));
                this.taskDate = ((String) in.readValue((String.class.getClassLoader())));
                this.remarks = ((String) in.readValue((Object.class.getClassLoader())));
                this.status = ((String) in.readValue((String.class.getClassLoader())));
                this.lng = ((String) in.readValue((String.class.getClassLoader())));
                this.lat = ((String) in.readValue((String.class.getClassLoader())));
                this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
                this.updatedAt = ((Object) in.readValue((Object.class.getClassLoader())));
                this.name = ((String) in.readValue((String.class.getClassLoader())));
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(userId);
                dest.writeValue(assignedBy);
                dest.writeValue(assignedTo);
                dest.writeValue(taskDate);
                dest.writeValue(remarks);
                dest.writeValue(status);
                dest.writeValue(lng);
                dest.writeValue(lat);
                dest.writeValue(createdAt);
                dest.writeValue(updatedAt);
                dest.writeValue(name);
            }

            public int describeContents() {
                return 0;
            }


        }


    }


}