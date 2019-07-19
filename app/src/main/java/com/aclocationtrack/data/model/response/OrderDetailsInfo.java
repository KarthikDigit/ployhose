package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetailsInfo implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<OrderDetailsInfo> CREATOR = new Creator<OrderDetailsInfo>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrderDetailsInfo createFromParcel(Parcel in) {
            return new OrderDetailsInfo(in);
        }

        public OrderDetailsInfo[] newArray(int size) {
            return (new OrderDetailsInfo[size]);
        }

    };

    protected OrderDetailsInfo(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrderDetailsInfo() {
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

        @SerializedName("order_info")
        @Expose
        private OrderInfo orderInfo;
        @SerializedName("order_details")
        @Expose
        private List<OrderDetail> orderDetails = null;
        @SerializedName("order_comments")
        @Expose
        private List<OrderComment> orderComments = null;
        @SerializedName("category")
        @Expose
        private Category category;
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
            this.orderInfo = ((OrderInfo) in.readValue((OrderInfo.class.getClassLoader())));
            in.readList(this.orderDetails, (OrderDetail.class.getClassLoader()));
            in.readList(this.orderComments, (OrderComment.class.getClassLoader()));
            this.category = ((Category) in.readValue((Category.class.getClassLoader())));
        }

        public Data() {
        }

        public OrderInfo getOrderInfo() {
            return orderInfo;
        }

        public void setOrderInfo(OrderInfo orderInfo) {
            this.orderInfo = orderInfo;
        }

        public List<OrderDetail> getOrderDetails() {
            return orderDetails;
        }

        public void setOrderDetails(List<OrderDetail> orderDetails) {
            this.orderDetails = orderDetails;
        }

        public List<OrderComment> getOrderComments() {
            return orderComments;
        }

        public void setOrderComments(List<OrderComment> orderComments) {
            this.orderComments = orderComments;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(orderInfo);
            dest.writeList(orderDetails);
            dest.writeList(orderComments);
            dest.writeValue(category);
        }

        public int describeContents() {
            return 0;
        }


        public static class Category implements Parcelable {

            @SerializedName("category_name")
            @Expose
            private String categoryName;
            public final static Parcelable.Creator<Category> CREATOR = new Creator<Category>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public Category createFromParcel(Parcel in) {
                    return new Category(in);
                }

                public Category[] newArray(int size) {
                    return (new Category[size]);
                }

            };

            protected Category(Parcel in) {
                this.categoryName = ((String) in.readValue((String.class.getClassLoader())));
            }

            public Category() {
            }

            public String getCategoryName() {
                return categoryName;
            }

            public void setCategoryName(String categoryName) {
                this.categoryName = categoryName;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(categoryName);
            }

            public int describeContents() {
                return 0;
            }

        }


        public static class OrderComment implements Parcelable {

            @SerializedName("comment")
            @Expose
            private String comment;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("user_id")
            @Expose
            private String userId;

            @SerializedName("comment_datetime")
            @Expose
            private String comment_datetime;

            public final static Parcelable.Creator<OrderComment> CREATOR = new Creator<OrderComment>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public OrderComment createFromParcel(Parcel in) {
                    return new OrderComment(in);
                }

                public OrderComment[] newArray(int size) {
                    return (new OrderComment[size]);
                }

            };

            protected OrderComment(Parcel in) {
                this.comment = ((String) in.readValue((String.class.getClassLoader())));
                this.name = ((String) in.readValue((String.class.getClassLoader())));
                this.userId = ((String) in.readValue((String.class.getClassLoader())));
                this.comment_datetime = ((String) in.readValue((String.class.getClassLoader())));
            }

            public OrderComment() {
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUserId() {
                return userId;
            }

            public String getComment_datetime() {
                return comment_datetime;
            }

            public void setComment_datetime(String comment_datetime) {
                this.comment_datetime = comment_datetime;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(comment);
                dest.writeValue(name);
                dest.writeValue(userId);
                dest.writeValue(comment_datetime);
            }

            public int describeContents() {
                return 0;
            }

        }


        public static class OrderInfo implements Parcelable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("total_amount")
            @Expose
            private String totalAmount;
            @SerializedName("status")
            @Expose
            private String status;
            public final static Parcelable.Creator<OrderInfo> CREATOR = new Creator<OrderInfo>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public OrderInfo createFromParcel(Parcel in) {
                    return new OrderInfo(in);
                }

                public OrderInfo[] newArray(int size) {
                    return (new OrderInfo[size]);
                }

            };

            protected OrderInfo(Parcel in) {
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.name = ((String) in.readValue((String.class.getClassLoader())));
                this.totalAmount = ((String) in.readValue((String.class.getClassLoader())));
                this.status = ((String) in.readValue((String.class.getClassLoader())));
            }

            public OrderInfo() {
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

            public String getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(String totalAmount) {
                this.totalAmount = totalAmount;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(name);
                dest.writeValue(totalAmount);
                dest.writeValue(status);
            }

            public int describeContents() {
                return 0;
            }

        }

        public static class OrderDetail implements Parcelable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("user_id")
            @Expose
            private String userId;
            @SerializedName("product_name")
            @Expose
            private String productName;
            @SerializedName("sku")
            @Expose
            private String sku;
            @SerializedName("model_number")
            @Expose
            private String model_number;

            @SerializedName("tonnage_color")
            @Expose
            private String tonnage_color;

            @SerializedName("order_quantity")
            @Expose
            private String orderQuantity;
            @SerializedName("order_price")
            @Expose
            private String orderPrice;
            @SerializedName("order_sub_total")
            @Expose
            private String orderSubTotal;
            @SerializedName("order_detail_id")
            @Expose
            private String orderDetailId;
            public final static Parcelable.Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {


                @SuppressWarnings({
                        "unchecked"
                })
                public OrderDetail createFromParcel(Parcel in) {
                    return new OrderDetail(in);
                }

                public OrderDetail[] newArray(int size) {
                    return (new OrderDetail[size]);
                }

            };

            protected OrderDetail(Parcel in) {
                this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
                this.userId = ((String) in.readValue((String.class.getClassLoader())));
                this.productName = ((String) in.readValue((String.class.getClassLoader())));
                this.sku = ((String) in.readValue((String.class.getClassLoader())));
                this.model_number = ((String) in.readValue((String.class.getClassLoader())));
                this.tonnage_color = ((String) in.readValue((String.class.getClassLoader())));
                this.orderQuantity = ((String) in.readValue((String.class.getClassLoader())));
                this.orderPrice = ((String) in.readValue((String.class.getClassLoader())));
                this.orderSubTotal = ((String) in.readValue((String.class.getClassLoader())));
                this.orderDetailId = ((String) in.readValue((String.class.getClassLoader())));
            }

            public OrderDetail() {
            }

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public String getSku() {
                return sku;
            }

            public String getModel_number() {
                return model_number;
            }

            public String getTonnage_color() {
                return tonnage_color;
            }

            public void setTonnage_color(String tonnage_color) {
                this.tonnage_color = tonnage_color;
            }

            public void setModel_number(String model_number) {
                this.model_number = model_number;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public String getOrderQuantity() {
                return orderQuantity;
            }

            public void setOrderQuantity(String orderQuantity) {
                this.orderQuantity = orderQuantity;
            }

            public String getOrderPrice() {
                return orderPrice;
            }

            public void setOrderPrice(String orderPrice) {
                this.orderPrice = orderPrice;
            }

            public String getOrderSubTotal() {
                return orderSubTotal;
            }

            public void setOrderSubTotal(String orderSubTotal) {
                this.orderSubTotal = orderSubTotal;
            }

            public String getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(String orderDetailId) {
                this.orderDetailId = orderDetailId;
            }

            public void writeToParcel(Parcel dest, int flags) {
                dest.writeValue(id);
                dest.writeValue(userId);
                dest.writeValue(productName);
                dest.writeValue(sku);
                dest.writeValue(model_number);
                dest.writeValue(tonnage_color);
                dest.writeValue(orderQuantity);
                dest.writeValue(orderPrice);
                dest.writeValue(orderSubTotal);
                dest.writeValue(orderDetailId);
            }

            public int describeContents() {
                return 0;
            }

        }


    }

}