package com.polyhose.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductSearch implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<ProductSearch> CREATOR = new Creator<ProductSearch>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductSearch createFromParcel(Parcel in) {
            return new ProductSearch(in);
        }

        public ProductSearch[] newArray(int size) {
            return (new ProductSearch[size]);
        }

    }
            ;

    protected ProductSearch(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (Datum.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductSearch() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
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
        dest.writeList(data);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }
    public static class Datum implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("value")
        @Expose
        private String value;
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

        }
                ;

        protected Datum(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.value = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Datum() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(value);
        }

        public int describeContents() {
            return 0;
        }


    }
}