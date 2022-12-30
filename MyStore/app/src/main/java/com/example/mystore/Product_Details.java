package com.example.mystore;

public class Product_Details {
    
    private String prod_Name;
    private int quantity;
    private double price;
    private int cat_id;
    private byte[] image;

    public Product_Details()
    {}
    public String getProd_Name() {
        return prod_Name;
    }

    public void setProd_Name(String prod_Name) {
        this.prod_Name = prod_Name;
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

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public byte[] getImage() {return image;}

    public void setImage(byte[] image) {this.image = image;}
}
