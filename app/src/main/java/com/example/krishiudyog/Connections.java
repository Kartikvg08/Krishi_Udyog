package com.example.krishiudyog;

public class Connections {
    String productid,pname,location,phone;

    public Connections(String productid, String pname,String place,String phone) {
        this.productid = productid;
        this.pname = pname;
        this.location=place;
        this.phone=phone;
    }

    public String getProductid() {
        return productid;
    }

    public String getPname() {
        return pname;
    }

    public String getLocation() {
        return location;
    }
}
