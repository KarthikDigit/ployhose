package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.aclocationtrack.data.model.response.createorder.Brand;
import com.aclocationtrack.data.model.response.createorder.Category;
import com.aclocationtrack.data.model.response.createorder.ProductCategory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderForDealer implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<CreateOrderForDealer> CREATOR = new Creator<CreateOrderForDealer>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CreateOrderForDealer createFromParcel(Parcel in) {
            return new CreateOrderForDealer(in);
        }

        public CreateOrderForDealer[] newArray(int size) {
            return (new CreateOrderForDealer[size]);
        }

    };

    protected CreateOrderForDealer(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CreateOrderForDealer() {
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

        @SerializedName("dealers")
        @Expose
        private List<Customer> customers = new ArrayList<>();
        @SerializedName("categories")
        @Expose
        private List<Category> categories = new ArrayList<>();
        @SerializedName("product_categories")
        @Expose
        private List<ProductCategory> productCategories = new ArrayList<>();
        @SerializedName("brands")
        @Expose
        private List<Brand> brands = new ArrayList<>();
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
            in.readList(this.customers, (Customer.class.getClassLoader()));
            in.readList(this.categories, (Category.class.getClassLoader()));
            in.readList(this.productCategories, (ProductCategory.class.getClassLoader()));
            in.readList(this.brands, (Brand.class.getClassLoader()));
        }

        public Data() {
        }

        public List<Customer> getCustomers() {
            return customers;
        }

        public void setCustomers(List<Customer> customers) {
            this.customers = customers;
        }

        public List<Category> getCategories() {
            return categories;
        }

        public void setCategories(List<Category> categories) {
            this.categories = categories;
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
            dest.writeList(customers);
            dest.writeList(categories);
            dest.writeList(productCategories);
            dest.writeList(brands);
        }

        public int describeContents() {
            return 0;
        }


//        public static class ProductCategory implements Parcelable
//        {
//
//            @SerializedName("id")
//            @Expose
//            private Integer id;
//            @SerializedName("category_name")
//            @Expose
//            private String categoryName;
//            public final static Parcelable.Creator<ProductCategory> CREATOR = new Creator<ProductCategory>() {
//
//
//                @SuppressWarnings({
//                        "unchecked"
//                })
//                public ProductCategory createFromParcel(Parcel in) {
//                    return new ProductCategory(in);
//                }
//
//                public ProductCategory[] newArray(int size) {
//                    return (new ProductCategory[size]);
//                }
//
//            }
//                    ;
//
//            protected ProductCategory(Parcel in) {
//                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
//                this.categoryName = ((String) in.readValue((String.class.getClassLoader())));
//            }
//
//            public ProductCategory() {
//            }
//
//            public Integer getId() {
//                return id;
//            }
//
//            public void setId(Integer id) {
//                this.id = id;
//            }
//
//            public String getCategoryName() {
//                return categoryName;
//            }
//
//            public void setCategoryName(String categoryName) {
//                this.categoryName = categoryName;
//            }
//
//            public void writeToParcel(Parcel dest, int flags) {
//                dest.writeValue(id);
//                dest.writeValue(categoryName);
//            }
//
//            public int describeContents() {
//                return 0;
//            }
//
//        }


        public static class Customer implements Parcelable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            public final static Parcelable.Creator<Customer> CREATOR = new Creator<Customer>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public Customer createFromParcel(Parcel in) {
                    return new Customer(in);
                }

                public Customer[] newArray(int size) {
                    return (new Customer[size]);
                }

            };

            protected Customer(Parcel in) {
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.name = ((String) in.readValue((String.class.getClassLoader())));
            }

            public Customer() {
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


//        public static class Category implements Parcelable
//        {
//
//            @SerializedName("id")
//            @Expose
//            private Integer id;
//            @SerializedName("category_name")
//            @Expose
//            private String categoryName;
//            public final static Parcelable.Creator<Category> CREATOR = new Creator<Category>() {
//
//
//                @SuppressWarnings({
//                        "unchecked"
//                })
//                public Category createFromParcel(Parcel in) {
//                    return new Category(in);
//                }
//
//                public Category[] newArray(int size) {
//                    return (new Category[size]);
//                }
//
//            }
//                    ;
//
//            protected Category(Parcel in) {
//                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
//                this.categoryName = ((String) in.readValue((String.class.getClassLoader())));
//            }
//
//            public Category() {
//            }
//
//            public Integer getId() {
//                return id;
//            }
//
//            public void setId(Integer id) {
//                this.id = id;
//            }
//
//            public String getCategoryName() {
//                return categoryName;
//            }
//
//            public void setCategoryName(String categoryName) {
//                this.categoryName = categoryName;
//            }
//
//            public void writeToParcel(Parcel dest, int flags) {
//                dest.writeValue(id);
//                dest.writeValue(categoryName);
//            }
//
//            public int describeContents() {
//                return 0;
//            }
//
//        }
//
//        public static class Brand implements Parcelable
//        {
//
//            @SerializedName("id")
//            @Expose
//            private Integer id;
//            @SerializedName("brand_name")
//            @Expose
//            private String brandName;
//            public final static Parcelable.Creator<Brand> CREATOR = new Creator<Brand>() {
//
//
//                @SuppressWarnings({
//                        "unchecked"
//                })
//                public Brand createFromParcel(Parcel in) {
//                    return new Brand(in);
//                }
//
//                public Brand[] newArray(int size) {
//                    return (new Brand[size]);
//                }
//
//            }
//                    ;
//
//            protected Brand(Parcel in) {
//                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
//                this.brandName = ((String) in.readValue((String.class.getClassLoader())));
//            }
//
//            public Brand() {
//            }
//
//            public Integer getId() {
//                return id;
//            }
//
//            public void setId(Integer id) {
//                this.id = id;
//            }
//
//            public String getBrandName() {
//                return brandName;
//            }
//
//            public void setBrandName(String brandName) {
//                this.brandName = brandName;
//            }
//
//            public void writeToParcel(Parcel dest, int flags) {
//                dest.writeValue(id);
//                dest.writeValue(brandName);
//            }
//
//            public int describeContents() {
//                return 0;
//            }
//
//        }

    }


}