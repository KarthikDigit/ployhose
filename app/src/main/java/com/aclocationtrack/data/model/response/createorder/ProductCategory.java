
package com.aclocationtrack.data.model.response.createorder;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductCategory implements Parcelable,Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    public final static Parcelable.Creator<ProductCategory> CREATOR = new Creator<ProductCategory>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ProductCategory createFromParcel(Parcel in) {
            return new ProductCategory(in);
        }

        public ProductCategory[] newArray(int size) {
            return (new ProductCategory[size]);
        }

    }
    ;

    protected ProductCategory(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.categoryName = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProductCategory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(categoryName);
    }

    public int describeContents() {
        return  0;
    }

}
