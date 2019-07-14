package com.example.bloomandroid.data.service.models;

import com.example.bloomandroid.data.service.dto.DataDTO;

import java.util.List;

public class FriendsParticipating {

    private String idUser;
    private List<DataDTO> dataDTOList;
    private String idEvent;

    public FriendsParticipating(String idUser, List<DataDTO> dataDTOList, String idEvent) {
        this.idUser = idUser;
        this.dataDTOList = dataDTOList;
        this.idEvent = idEvent;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public List<DataDTO> getDataDTOList() {
        return dataDTOList;
    }

    public void setDataDTOList(List<DataDTO> dataDTOList) {
        this.dataDTOList = dataDTOList;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

}
