package com.example.myapp;

import java.io.Serializable;


public class StockClass implements Serializable {
    int StockId;
    String ProductCode;
    String ProductName;
    String Description;
    String PartNo;
    String Qty;
    int CompanyId;


    public  StockClass(){

    }

    public int getId() {
        return StockId;
    }

    public void setId(int StockId) {
        this.StockId = StockId;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPartNo() {
        return PartNo;
    }

    public void setPartNo(String partNo) {
        PartNo = partNo;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public boolean contains(String query){
        query = query.toLowerCase();
        if(ProductName != null && ProductCode != null)
            return ((String.valueOf(PartNo).contains(query)) || (ProductName.toLowerCase().contains(query) )|| (PartNo.toLowerCase().contains(query)));
        else
            return false;
    }
}
