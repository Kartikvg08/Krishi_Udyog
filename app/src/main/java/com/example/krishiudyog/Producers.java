package com.example.krishiudyog;

import java.util.Vector;

public class Producers {

    public String fullname,email,phone,password,id,place;

    public Producers(){

    }

    public Producers(String fullname, String email, String phone,String password,String place)
    {

        this.fullname=fullname;
        this.email=email;
        this.phone=phone;
        this.password=password;
        this.place=place;
    }


    public void setId(String id)
    {
        this.id=id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}
