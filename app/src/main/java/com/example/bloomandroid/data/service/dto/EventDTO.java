package com.example.bloomandroid.data.service.dto;

import com.google.gson.annotations.SerializedName;

public class EventDTO {

    @SerializedName("_id")
    String id;
    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("image")
    String image;
    @SerializedName("longitude")
    String longitude;
    @SerializedName("latitude")
    String latitude;
    @SerializedName("promotionalCode")
    String associationName;

    public EventDTO(String id, String title, String description, String image, String longitude, String latitude, String associationName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.associationName = associationName;
    }

    public String getId(){ return id; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getLongitude() { return longitude; }

    public String getLatitude() { return latitude; }

    public String getAssociationName() { return associationName; }

}
