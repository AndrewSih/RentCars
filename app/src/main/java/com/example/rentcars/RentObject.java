package com.example.rentcars;

public class RentObject {
    private String model;
    private String description;
    private String city;
    private String mobileNo;
    private String cost;
    private String Image;

    public RentObject() {
    }

    public RentObject(String model, String description, String city, String mobileNo, String cost, String image) {
        this.model = model;
        this.description = description;
        this.city = city;
        this.mobileNo = mobileNo;
        this.cost = cost;
        Image = image;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
