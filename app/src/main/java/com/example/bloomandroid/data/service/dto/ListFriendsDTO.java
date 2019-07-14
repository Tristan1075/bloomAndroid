package com.example.bloomandroid.data.service.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListFriendsDTO {

    @SerializedName("friends")
    @Expose
    private FriendsDTO friendsDTO;
    @SerializedName("id")
    @Expose
    private String id;

    public FriendsDTO getFriendsDTO() {
        return friendsDTO;
    }

    public void setFriendsDTO(FriendsDTO friendsDTO) {
        this.friendsDTO = friendsDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}