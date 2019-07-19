package com.aclocationtrack.data.model.request;

import com.aclocationtrack.data.model.response.createorder.Brand;
import com.aclocationtrack.data.model.response.createorder.ProductCategory;

import java.io.Serializable;

public class Products implements Serializable{

    private String product_category_name;
    private int product_category_id;

    private int product_id;

    private int brand_id;
    private String brand_name;

    private String sku;
    private String model_name;
    private double dealer_price;
    private double sub_total;
    private int quantity;
    private int maxQuantity;
    private double price;

    private String searchProductName;

    private ProductCategory productCategory;

    private Brand brand;

    private int productCategorySelectedPosition;
    private int brandSelectedPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Products)) return false;

        Products products = (Products) o;

        if (product_category_id != products.product_category_id) return false;
        if (product_id != products.product_id) return false;
        if (brand_id != products.brand_id) return false;
        if (!product_category_name.equals(products.product_category_name)) return false;
        if (!brand_name.equals(products.brand_name)) return false;
        if (!sku.equals(products.sku)) return false;
        return model_name.equals(products.model_name);
    }

    @Override
    public int hashCode() {
        int result = product_category_name.hashCode();
        result = 31 * result + product_category_id;
        result = 31 * result + product_id;
        result = 31 * result + brand_id;
        result = 31 * result + brand_name.hashCode();
        result = 31 * result + sku.hashCode();
        result = 31 * result + model_name.hashCode();
        return result;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public String getSearchProductName() {
        return searchProductName;
    }

    public void setSearchProductName(String searchProductName) {
        this.searchProductName = searchProductName;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getProductCategorySelectedPosition() {
        return productCategorySelectedPosition;
    }

    public void setProductCategorySelectedPosition(int productCategorySelectedPosition) {
        this.productCategorySelectedPosition = productCategorySelectedPosition;
    }

    public int getBrandSelectedPosition() {
        return brandSelectedPosition;
    }

    public void setBrandSelectedPosition(int brandSelectedPosition) {
        this.brandSelectedPosition = brandSelectedPosition;
    }
//    public Brand getBrand() {
//        return brand;
//    }
//
//    public void setBrand(Brand brand) {
//        this.brand = brand;
//    }
//
//    public ProductCategory getProductCategory() {
//        return productCategory;
//    }
//
//    public void setProductCategory(ProductCategory productCategory) {
//        this.productCategory = productCategory;
//    }
//
//    private Brand brand;
//    private ProductCategory productCategory;


    public String getProduct_category_name() {
        return product_category_name;
    }

    public void setProduct_category_name(String product_category_name) {
        this.product_category_name = product_category_name;
    }

    public int getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(int product_category_id) {
        this.product_category_id = product_category_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getModel_name() {
        return model_name;
    }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public double getDealer_price() {
        return dealer_price;
    }

    public void setDealer_price(double dealer_price) {
        this.dealer_price = dealer_price;
    }

    public double getSub_total() {
        return sub_total;
    }

    public void setSub_total(double sub_total) {
        this.sub_total = sub_total;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
