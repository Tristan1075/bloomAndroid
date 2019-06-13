package com.example.bloomandroid.data.service.models;

public class BoughtTicket {

    private String userId;
    private Ticket ticket;

    public BoughtTicket(String userId, Ticket ticket) {
        this.userId = userId;
        this.ticket = ticket;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

}
