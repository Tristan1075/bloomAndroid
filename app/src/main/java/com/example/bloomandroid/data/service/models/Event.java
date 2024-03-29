package com.example.bloomandroid.data.service.models;

import java.io.Serializable;

public class Event implements Serializable {

    private String id;
    private String title;
    private String description;
    private String imageURl;
    private String longitude;
    private String latitude;
    private String associationName;
    private String dateEvent;

    public String getId(){ return id; }
    public void setId(String id){ this.id = id; }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURl() {
        return imageURl;
    }
    public void setImageURl(String imageURl) {
        this.imageURl = imageURl;
    }

    public String getLongitude() {return longitude; }
    public void setLongitude(String longitude) { this.longitude = longitude; }

    public String getLatitude() {return latitude; }
    public void setLatitude(String latitude) { this.latitude = latitude; }

    public String getAssociationName(){ return associationName; }
    public void setAssociationName(String associationName){ this.associationName = associationName; }

    public String getDateEvent(){ return dateEvent; }
    public void setDateEvent(String dateEvent){ this.dateEvent = dateEvent; }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageURl='" + imageURl + '\'' +
                '}';
    }
}
