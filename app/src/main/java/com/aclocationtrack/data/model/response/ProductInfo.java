package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductInfo implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<ProductInfo> CREATOR = new Creator<ProductInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductInfo createFromParcel(Parcel in) {
            return new ProductInfo(in);
        }

        public ProductInfo[] newArray(int size) {
            return (new ProductInfo[size]);
        }

    };

    protected ProductInfo(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductInfo() {
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

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("sku")
        @Expose
        private String sku;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("model_number")
        @Expose
        private String modelNumber;
        @SerializedName("tonnage_color")
        @Expose
        private String tonnage_color;
        @SerializedName("star_rating_price")
        @Expose
        private String starRatingPrice;
        @SerializedName("quantity")
        @Expose
        private String quantity;
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
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.sku = ((String) in.readValue((String.class.getClassLoader())));
            this.price = ((String) in.readValue((String.class.getClassLoader())));
            this.modelNumber = ((String) in.readValue((String.class.getClassLoader())));
            this.tonnage_color = ((String) in.readValue((String.class.getClassLoader())));
            this.starRatingPrice = ((String) in.readValue((String.class.getClassLoader())));
            this.quantity = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Data() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSku() {
            return sku;
        }

        public void setSku(String sku) {
            this.sku = sku;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getModelNumber() {
            return modelNumber;
        }

        public String getTonnage_color() {
            return tonnage_color;
        }

        public void setTonnage_color(String tonnage_color) {
            this.tonnage_color = tonnage_color;
        }

        public void setModelNumber(String modelNumber) {
            this.modelNumber = modelNumber;
        }

        public String getStarRatingPrice() {
            return starRatingPrice;
        }

        public void setStarRatingPrice(String starRatingPrice) {
            this.starRatingPrice = starRatingPrice;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(sku);
            dest.writeValue(price);
            dest.writeValue(modelNumber);
            dest.writeValue(tonnage_color);
            dest.writeValue(starRatingPrice);
            dest.writeValue(quantity);
        }

        public int describeContents() {
            return 0;
        }

    }

}