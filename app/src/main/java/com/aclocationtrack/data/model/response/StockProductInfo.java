package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StockProductInfo implements Parcelable
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
    public final static Parcelable.Creator<StockProductInfo> CREATOR = new Creator<StockProductInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StockProductInfo createFromParcel(Parcel in) {
            return new StockProductInfo(in);
        }

        public StockProductInfo[] newArray(int size) {
            return (new StockProductInfo[size]);
        }

    }
            ;

    protected StockProductInfo(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (Datum.class.getClassLoader()));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public StockProductInfo() {
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
        @SerializedName("product_name")
        @Expose
        private String productName;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("sku")
        @Expose
        private String sku;
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
            this.productName = ((String) in.readValue((String.class.getClassLoader())));
            this.quantity = ((String) in.readValue((String.class.getClassLoader())));
            this.sku = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Datum() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(productName);
            dest.writeValue(quantity);
            dest.writeValue(sku);
        }

        public int describeContents() {
            return 0;
        }

    }
}