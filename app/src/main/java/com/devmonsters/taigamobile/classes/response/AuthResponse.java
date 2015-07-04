package com.devmonsters.taigamobile.classes.response;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("full_name")
    private String fullName;
    private String bio;
    @SerializedName("full_name_display")
    private String fullNameDisplay;
    private String theme;
    private String photo;
    private Long id;
    @SerializedName("auth_token")
    private String authToken;
    private String color;
    private String timezone;
    @SerializedName("is_active")
    private boolean active;
    private String email;
    private String lang;
    @SerializedName("big_photo")
    private String bigPhoto;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFullNameDisplay() {
        return fullNameDisplay;
    }

    public void setFullNameDisplay(String fullNameDisplay) {
        this.fullNameDisplay = fullNameDisplay;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getBigPhoto() {
        return bigPhoto;
    }

    public void setBigPhoto(String bigPhoto) {
        this.bigPhoto = bigPhoto;
    }
}
