package com.example.tony.mohamedtonytask.webServices.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("userdata")
    @Expose
    private List<UserData> userdata = null;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("state")
    @Expose
    private String state;

    public List<UserData> getUserdata() {
        return userdata;
    }

    public void setUserdata(List<UserData> userdata) {
        this.userdata = userdata;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
