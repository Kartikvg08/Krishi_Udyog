package com.example.krishiudyog;

import android.graphics.drawable.Drawable;

import java.util.HashMap;

public class Itemslist {

    String quality,quantity,rate,pname,location,contact,productname,pid;
    HashMap<String,Integer> imagehash;
    private int image;

    public Itemslist(){}

    public Itemslist(String quality, String quantity, String rate, String pname, String location,String contact,String Productname) {
        this.pname = pname;
        this.location = location;
        this.contact=contact;
        this.quality = quality;
        this.quantity = quantity;
        this.rate = rate;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }


    public void setimagehash(HashMap<String,Integer> h){
        this.imagehash=h;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public String getQuality() {
        return quality;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getRate() {
        return rate;
    }

    public String getPname() {
        return pname;
    }

    public String getLocation() {
        return location;
    }
}
