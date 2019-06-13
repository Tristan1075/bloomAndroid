package com.example.bloomandroid.data.service.dto;

import com.google.gson.annotations.SerializedName;

public class PromotionalCodeDTO {

    @SerializedName("idEvent")
    String idEvent;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    String price;
    @SerializedName("quantity")
    String quantity;

    public PromotionalCodeDTO(String idEvent, String name, String price, String quantity) {
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

}
