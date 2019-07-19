
package com.aclocationtrack.data.model.response.createorder;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mobile implements Parcelable
{

    @SerializedName("product_categories")
    @Expose
    private List<ProductCategory> productCategories = new ArrayList<>();
    @SerializedName("brands")
    @Expose
    private List<Brand> brands = new ArrayList<>();
    public final static Parcelable.Creator<Mobile> CREATOR = new Creator<Mobile>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Mobile createFromParcel(Parcel in) {
            return new Mobile(in);
        }

        public Mobile[] newArray(int size) {
            return (new Mobile[size]);
        }

    }
    ;

    protected Mobile(Parcel in) {
        in.readList(this.productCategories, (ProductCategory.class.getClassLoader()));
        in.readList(this.brands, (Brand.class.getClassLoader()));
    }

    public Mobile() {
    }

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(productCategories);
        dest.writeList(brands);
    }

    public int describeContents() {
        return  0;
    }

}
