package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateService implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<CreateService> CREATOR = new Creator<CreateService>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CreateService createFromParcel(Parcel in) {
            return new CreateService(in);
        }

        public CreateService[] newArray(int size) {
            return (new CreateService[size]);
        }

    };

    protected CreateService(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CreateService() {
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

        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("brands")
        @Expose
        private List<Brand> brands = null;
        @SerializedName("states")
        @Expose
        private List<State> states = null;
        @SerializedName("prefer_times")
        @Expose
        private List<PreferTime> preferTimes = null;
        @SerializedName("problem_types")
        @Expose
        private List<ProblemType> problemTypes = null;
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
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.brands, (Brand.class.getClassLoader()));
            in.readList(this.states, (State.class.getClassLoader()));
            in.readList(this.preferTimes, (PreferTime.class.getClassLoader()));
            in.readList(this.problemTypes, (ProblemType.class.getClassLoader()));
        }

        public Data() {
        }

        public List<Brand> getBrands() {
            return brands;
        }

        public void setBrands(List<Brand> brands) {
            this.brands = brands;
        }

        public List<State> getStates() {
            return states;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setStates(List<State> states) {
            this.states = states;
        }

        public List<PreferTime> getPreferTimes() {
            return preferTimes;
        }

        public void setPreferTimes(List<PreferTime> preferTimes) {
            this.preferTimes = preferTimes;
        }

        public List<ProblemType> getProblemTypes() {
            return problemTypes;
        }

        public void setProblemTypes(List<ProblemType> problemTypes) {
            this.problemTypes = problemTypes;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(email);
            dest.writeList(brands);
            dest.writeList(states);
            dest.writeList(preferTimes);
            dest.writeList(problemTypes);
        }

        public int describeContents() {
            return 0;
        }


        public static class State implements Parcelable {

            @SerializedName("state_name")
            @Expose
            private String stateName;
            @SerializedName("id")
            @Expose
            private Integer id;
            public final static Parcelable.Creator<State> CREATOR = new Creator<State>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public State createFromParcel(Parcel in) {
                    return new State(in);
                }

                public State[] newArray(int size) {
                    return (new State[size]);
                }

            };

            protected State(Parcel in) {
                this.stateName = ((String) in.readValue((String.class.getClassLoader())));
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            }

            public State() {
            }

            public String getStateName() {
                return stateName;
            }

            public void setStateName(String stateName) {
                this.stateName = stateName;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(stateName);
                dest.writeValue(id);
            }

            public int describeContents() {
                return 0;
            }

        }


        public static class ProblemType implements Parcelable {

            @SerializedName("problem_type")
            @Expose
            private String problemType;
            @SerializedName("id")
            @Expose
            private Integer id;
            public final static Parcelable.Creator<ProblemType> CREATOR = new Creator<ProblemType>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public ProblemType createFromParcel(Parcel in) {
                    return new ProblemType(in);
                }

                public ProblemType[] newArray(int size) {
                    return (new ProblemType[size]);
                }

            };

            protected ProblemType(Parcel in) {
                this.problemType = ((String) in.readValue((String.class.getClassLoader())));
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            }

            public ProblemType() {
            }

            public String getProblemType() {
                return problemType;
            }

            public void setProblemType(String problemType) {
                this.problemType = problemType;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(problemType);
                dest.writeValue(id);
            }

            public int describeContents() {
                return 0;
            }

        }

        public static class Brand implements Parcelable {

            @SerializedName("brand_name")
            @Expose
            private String brandName;
            @SerializedName("id")
            @Expose
            private Integer id;
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

            };

            protected Brand(Parcel in) {
                this.brandName = ((String) in.readValue((String.class.getClassLoader())));
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            }

            public Brand() {
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(brandName);
                dest.writeValue(id);
            }

            public int describeContents() {
                return 0;
            }

        }

        public static class PreferTime implements Parcelable {

            @SerializedName("prefer_time")
            @Expose
            private String preferTime;
            @SerializedName("id")
            @Expose
            private Integer id;
            public final static Parcelable.Creator<PreferTime> CREATOR = new Creator<PreferTime>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public PreferTime createFromParcel(Parcel in) {
                    return new PreferTime(in);
                }

                public PreferTime[] newArray(int size) {
                    return (new PreferTime[size]);
                }

            };

            protected PreferTime(Parcel in) {
                this.preferTime = ((String) in.readValue((String.class.getClassLoader())));
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            }

            public PreferTime() {
            }

            public String getPreferTime() {
                return preferTime;
            }

            public void setPreferTime(String preferTime) {
                this.preferTime = preferTime;
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(preferTime);
                dest.writeValue(id);
            }

            public int describeContents() {
                return 0;
            }

        }


    }


}