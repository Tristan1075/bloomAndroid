package com.example.bloomandroid.data.service.models;

public class BoughtTicket {

    private String userId;
    private Ticket ticket;
    private Integer newPrice;

    public BoughtTicket(String userId, Ticket ticket, Integer newPrice) {
        this.userId = userId;
        this.ticket = ticket;
        this.newPrice = newPrice;
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

    public Integer getNewPrice(){ return newPrice; }
    public void setNewPrice(Integer newPrice){ this.newPrice = newPrice; }

}
