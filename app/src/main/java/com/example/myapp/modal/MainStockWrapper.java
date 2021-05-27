package com.example.myapp.modal;

import com.example.myapp.StockClass;

import java.io.Serializable;
import java.util.ArrayList;

public class MainStockWrapper implements Serializable {
    ArrayList<StockClass> Items;

    public ArrayList<StockClass> getItems() {
        return Items;
    }

    public void setItems(ArrayList<StockClass> items) {
        Items = items;
    }
}
