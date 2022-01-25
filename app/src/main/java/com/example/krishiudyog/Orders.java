package com.example.krishiudyog;

public class Orders {

    String Producername,Consumername,oqty,amount,consumerno,oqlty,opname,date;
    String qty,rate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpname() {
        return opname;
    }

    public void setOpname(String opname) {
        this.opname = opname;
    }

    public Orders(){}

    public void setProducername(String producername) {
        Producername = producername;
    }

    public void setConsumername(String consumername) {
        Consumername = consumername;
    }

    public void setOqty(String oqty) {
        this.oqty = oqty;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setConsumerno(String consumerno) {
        this.consumerno = consumerno;
    }

    public void setOqlty(String oqlty) {
        this.oqlty = oqlty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getProducername() {
        return Producername;
    }

    public String getConsumername() {
        return Consumername;
    }

    public String getOqty() {
        return oqty;
    }

    public String getAmount() {
        return amount;
    }

    public String getConsumerno() {
        return consumerno;
    }

    public String getOqlty() {
        return oqlty;
    }

    public String getQty() {
        return qty;
    }

    public String getRate() {
        return rate;
    }
}
