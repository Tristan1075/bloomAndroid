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

    public EventDTO(String id, String title, String description, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
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


}
