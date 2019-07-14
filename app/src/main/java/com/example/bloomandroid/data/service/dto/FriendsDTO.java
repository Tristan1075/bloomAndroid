package com.example.bloomandroid.data.service.dto;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendsDTO {

    @SerializedName("data")
    @Expose
    private List<DataDTO> dataDTO = null;

    public List<DataDTO> getData() {
        return dataDTO;
    }

    public void setData(List<DataDTO> dataDTO) {
        this.dataDTO = dataDTO;
    }
}