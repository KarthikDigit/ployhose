package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        public LoginResponse[] newArray(int size) {
            return (new LoginResponse[size]);
        }

    }
            ;

    protected LoginResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public LoginResponse() {
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



    public static class Data implements Parcelable
    {

        @SerializedName("access_token")
        @Expose
        private String accessToken;
        @SerializedName("token_type")
        @Expose
        private String tokenType;
        @SerializedName("expires_at")
        @Expose
        private ExpiresAt expiresAt;
        @SerializedName("role")
        @Expose
        private String role;
        @SerializedName("role_id")
        @Expose
        private String roleId;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        public final Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


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
            this.accessToken = ((String) in.readValue((String.class.getClassLoader())));
            this.tokenType = ((String) in.readValue((String.class.getClassLoader())));
            this.expiresAt = ((ExpiresAt) in.readValue((ExpiresAt.class.getClassLoader())));
            this.role = ((String) in.readValue((String.class.getClassLoader())));
            this.roleId = ((String) in.readValue((String.class.getClassLoader())));
            this.userId = ((Integer) in.readValue((Integer.class.getClassLoader())));
        }

        public Data() {
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public ExpiresAt getExpiresAt() {
            return expiresAt;
        }

        public void setExpiresAt(ExpiresAt expiresAt) {
            this.expiresAt = expiresAt;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getRoleId() {
            return roleId;
        }

        public void setRoleId(String roleId) {
            this.roleId = roleId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(accessToken);
            dest.writeValue(tokenType);
            dest.writeValue(expiresAt);
            dest.writeValue(role);
            dest.writeValue(roleId);
            dest.writeValue(userId);
        }

        public int describeContents() {
            return 0;
        }




        public static class ExpiresAt implements Parcelable
        {

            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("timezone_type")
            @Expose
            private Integer timezoneType;
            @SerializedName("timezone")
            @Expose
            private String timezone;
            public final Parcelable.Creator<ExpiresAt> CREATOR = new Creator<ExpiresAt>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public ExpiresAt createFromParcel(Parcel in) {
                    return new ExpiresAt(in);
                }

                public ExpiresAt[] newArray(int size) {
                    return (new ExpiresAt[size]);
                }

            }
                    ;

            protected ExpiresAt(Parcel in) {
                this.date = ((String) in.readValue((String.class.getClassLoader())));
                this.timezoneType = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.timezone = ((String) in.readValue((String.class.getClassLoader())));
            }

            public ExpiresAt() {
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public Integer getTimezoneType() {
                return timezoneType;
            }

            public void setTimezoneType(Integer timezoneType) {
                this.timezoneType = timezoneType;
            }

            public String getTimezone() {
                return timezone;
            }

            public void setTimezone(String timezone) {
                this.timezone = timezone;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(date);
                dest.writeValue(timezoneType);
                dest.writeValue(timezone);
            }

            public int describeContents() {
                return 0;
            }

        }

    }




}