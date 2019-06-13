package com.example.bloomandroid.data.service.dto;

import com.example.bloomandroid.data.service.models.StringParams;
import com.google.gson.annotations.SerializedName;

public class TicketDTO {

    @SerializedName("_id")
    String _id;
    @SerializedName("idEvent")
    String idEvent;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    String price;
    @SerializedName("quantity")
    String quantity;

    public TicketDTO(String _id, String idEvent, String name, String price, String quantity) {
        this._id = _id;
        this.idEvent = idEvent;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String get_id() { return _id; }

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

}
