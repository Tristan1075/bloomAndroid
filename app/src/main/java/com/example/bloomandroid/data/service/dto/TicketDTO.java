package com.example.bloomandroid.data.service.dto;

import com.google.gson.annotations.SerializedName;

public class TicketDTO {

    @SerializedName("name")
    String name;
    @SerializedName("price")
    String price;
    @SerializedName("quantity")
    String quantity;

    public TicketDTO(String name, String price, String quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

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
