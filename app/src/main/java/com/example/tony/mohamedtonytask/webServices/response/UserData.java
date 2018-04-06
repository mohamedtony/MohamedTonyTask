package com.example.tony.mohamedtonytask.webServices.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("fk_gender_id")
    @Expose
    private String fkGenderId;
    @SerializedName("image_profile")
    @Expose
    private String imageProfile;
    @SerializedName("player_id")
    @Expose
    private Object playerId;
    @SerializedName("lng")
    @Expose
    private Object lng;
    @SerializedName("lat")
    @Expose
    private Object lat;
    @SerializedName("distance")
    @Expose
    private Object distance;
    @SerializedName("last_time_location")
    @Expose
    private Object lastTimeLocation;
    @SerializedName("create_user_id")
    @Expose
    private Object createUserId;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("update_user_id")
    @Expose
    private Object updateUserId;
    @SerializedName("update_date")
    @Expose
    private Object updateDate;
    @SerializedName("validity")
    @Expose
    private String validity;
    @SerializedName("reset")
    @Expose
    private String reset;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFkGenderId() {
        return fkGenderId;
    }

    public void setFkGenderId(String fkGenderId) {
        this.fkGenderId = fkGenderId;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public Object getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Object playerId) {
        this.playerId = playerId;
    }

    public Object getLng() {
        return lng;
    }

    public void setLng(Object lng) {
        this.lng = lng;
    }

    public Object getLat() {
        return lat;
    }

    public void setLat(Object lat) {
        this.lat = lat;
    }

    public Object getDistance() {
        return distance;
    }

    public void setDistance(Object distance) {
        this.distance = distance;
    }

    public Object getLastTimeLocation() {
        return lastTimeLocation;
    }

    public void setLastTimeLocation(Object lastTimeLocation) {
        this.lastTimeLocation = lastTimeLocation;
    }

    public Object getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Object createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Object getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Object updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Object getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Object updateDate) {
        this.updateDate = updateDate;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getReset() {
        return reset;
    }

    public void setReset(String reset) {
        this.reset = reset;
    }
}
