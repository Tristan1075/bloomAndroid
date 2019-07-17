package com.example.bloomandroid.data.service;

import android.app.Application;

import com.facebook.AccessToken;

public class BloomAndroidApplication extends Application {

    private String userId;
    private AccessToken tokenId;


    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AccessToken getTokenId() {
        return tokenId;
    }
    public void setTokenId(AccessToken tokenId) {
        this.tokenId = tokenId;
    }

}
