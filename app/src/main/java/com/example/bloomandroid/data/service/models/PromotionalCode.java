package com.example.bloomandroid.data.service.models;

import java.io.Serializable;

public class PromotionalCode implements Serializable {

    private String idEvent;
    private String name;
    private String price;
    private String quantity;

    public PromotionalCode(String idEvent, String name, String price, String quantity) {
        this.idEvent = idEvent;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getIdEvent() { return idEvent; }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "PromotionalCode{" +
                "idEvent='" + idEvent + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
