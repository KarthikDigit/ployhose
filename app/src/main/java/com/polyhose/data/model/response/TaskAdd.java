package com.polyhose.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TaskAdd implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<TaskAdd> CREATOR = new Creator<TaskAdd>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TaskAdd createFromParcel(Parcel in) {
            return new TaskAdd(in);
        }

        public TaskAdd[] newArray(int size) {
            return (new TaskAdd[size]);
        }

    };

    protected TaskAdd(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TaskAdd() {
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

        @SerializedName("performs")
        @Expose
        private List<Perform> performs = null;
        @SerializedName("dealers")
        @Expose
        private List<Dealer> dealers = null;
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
            in.readList(this.performs, (Perform.class.getClassLoader()));
            in.readList(this.dealers, (Dealer.class.getClassLoader()));
        }

        public Data() {
        }

        public List<Perform> getPerforms() {
            return performs;
        }

        public void setPerforms(List<Perform> performs) {
            this.performs = performs;
        }

        public List<Dealer> getDealers() {
            return dealers;
        }

        public void setDealers(List<Dealer> dealers) {
            this.dealers = dealers;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(performs);
            dest.writeList(dealers);
        }

        public int describeContents() {
            return 0;
        }


        public static class Dealer implements Parcelable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            public final static Parcelable.Creator<Dealer> CREATOR = new Creator<Dealer>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public Dealer createFromParcel(Parcel in) {
                    return new Dealer(in);
                }

                public Dealer[] newArray(int size) {
                    return (new Dealer[size]);
                }

            };

            protected Dealer(Parcel in) {
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.name = ((String) in.readValue((String.class.getClassLoader())));
            }

            public Dealer() {
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            @Override
            public String toString() {
                return getName();
            }

            public void setName(String name) {
                this.name = name;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(name);
            }

            public int describeContents() {
                return 0;
            }

        }


        public static class Perform implements Parcelable {

            @SerializedName("perform")
            @Expose
            private String perform;
            @SerializedName("id")
            @Expose
            private Integer id;


            private boolean isChecked = false;

            public boolean isSelected() {
                return isChecked;
            }

            public void setSelected(boolean selected) {
                isChecked = selected;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            private String description = "";

            public final static Parcelable.Creator<Perform> CREATOR = new Creator<Perform>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public Perform createFromParcel(Parcel in) {
                    return new Perform(in);
                }

                public Perform[] newArray(int size) {
                    return (new Perform[size]);
                }

            };

            protected Perform(Parcel in) {
                this.perform = ((String) in.readValue((String.class.getClassLoader())));
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.isChecked = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
                this.description = ((String) in.readValue((String.class.getClassLoader())));
            }

            public Perform() {
            }

            public String getPerform() {
                return perform;
            }

            public void setPerform(String perform) {
                this.perform = perform;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(perform);
                dest.writeValue(id);
                dest.writeValue(isChecked);
                dest.writeValue(description);
            }

            public int describeContents() {
                return 0;
            }

        }


    }

}