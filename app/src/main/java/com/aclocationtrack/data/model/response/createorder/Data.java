
package com.aclocationtrack.data.model.response.createorder;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable
{

    @SerializedName("categories")
    @Expose
    private List<Category> categories = null;
    @SerializedName("mobile")
    @Expose
    private Mobile mobile;
    @SerializedName("ac")
    @Expose
    private Ac ac;
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

    }
    ;

    protected Data(Parcel in) {
        in.readList(this.categories, (com.aclocationtrack.data.model.response.createorder.Category.class.getClassLoader()));
        this.mobile = ((Mobile) in.readValue((Mobile.class.getClassLoader())));
        this.ac = ((Ac) in.readValue((Ac.class.getClassLoader())));
    }

    public Data() {
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public Ac getAc() {
        return ac;
    }

    public void setAc(Ac ac) {
        this.ac = ac;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(categories);
        dest.writeValue(mobile);
        dest.writeValue(ac);
    }

    public int describeContents() {
        return  0;
    }

}
