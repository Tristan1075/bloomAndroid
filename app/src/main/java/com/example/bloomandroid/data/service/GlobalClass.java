package com.example.bloomandroid.data.service;

import android.app.Application;

public class GlobalClass extends Application {

    private String userId;


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
