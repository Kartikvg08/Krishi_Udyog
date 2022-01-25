package com.example.krishiudyog;

public class Orderslist {

    String Producername,Consumername,oqty,amount,consumerno,oqlty,opname;
    String qty,rate;


    public Orderslist(){}



    public void setopname(String productname) {
        this.opname = productname;
    }

    public void setConsumername(String consumername) {
        this.Consumername = consumername;
    }


    public void setoqty(String orderedqty) {
        this.oqty = orderedqty;
    }

    public void setamount(String orderedamount) {
        this.amount = orderedamount;
    }

    public String getopname() {
        return opname;
    }

    public String getConsumername() {
        return Consumername;
    }


    public String getoqty() {
        return oqty;
    }

    public String getamount() {
        return amount;
    }
}
