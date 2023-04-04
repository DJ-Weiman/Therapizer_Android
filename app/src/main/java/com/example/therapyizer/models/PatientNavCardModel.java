package com.example.therapyizer.models;

public class PatientNavCardModel {

    String navTitle;
    String navText;
    int image;

    public PatientNavCardModel(String navTitle, String navText, int image) {
        this.navTitle = navTitle;
        this.navText = navText;
        this.image = image;
    }

    public String getNavTitle() {
        return navTitle;
    }

    public String getNavText() {
        return navText;
    }

    public int getImage() {
        return image;
    }
}
