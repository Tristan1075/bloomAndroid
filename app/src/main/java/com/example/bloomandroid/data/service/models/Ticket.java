package com.example.bloomandroid.data.service.models;

import java.io.Serializable;

public class Ticket implements Serializable {

    private String _id;
    private String idEvent;
    private String name;
    private String price;
    private String quantity;

    public Ticket(String _id, String idEvent, String name, String price, String quantity) {
        this._id = _id;
        this.idEvent = idEvent;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
