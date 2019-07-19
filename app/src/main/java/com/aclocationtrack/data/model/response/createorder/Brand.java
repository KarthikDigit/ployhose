
package com.aclocationtrack.data.model.response.createorder;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Brand implements Parcelable,Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    public final static Parcelable.Creator<Brand> CREATOR = new Creator<Brand>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Brand createFromParcel(Parcel in) {
            return new Brand(in);
        }

        public Brand[] newArray(int size) {
            return (new Brand[size]);
        }

    }
    ;

    protected Brand(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.brandName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Brand() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(brandName);
    }

    public int describeContents() {
        return  0;
    }

}
