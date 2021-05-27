package com.example.myapp;

import android.util.EventLogTags;

public class User {

private int id;
    private String ProductCode,ProductName,Description,PartNo,Qty;

    public User(int id , String ProductCode, String ProductName,String Description,String ParttNo,String Qty) {
        this.id =id;
        this.ProductCode = ProductCode;
        this.ProductName =ProductName;
        this.Description=Description;
        this.PartNo=ParttNo;
        this.Qty=Qty;
    }


    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getProductCode() {return ProductCode;}
    public void setProductCode(String ProductCode) {this.ProductCode = ProductCode;}

    public String getProductName() {return ProductName;}
    public void setProductName(String ProductName) {this.ProductName = ProductName;}

    public String getDescription() {return Description;}
    public void setDescription(String Description) {this.Description = Description;}

    public String getPartNo() {return PartNo;}
    public void setPartNo(String PartNo) {this.PartNo = PartNo;}

    public String getQty() {return Qty;}
    public void setQty(String Qty) {this.Qty = Qty;}



}

