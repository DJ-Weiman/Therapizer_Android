package com.example.therapyizer.models;

public class PatientNavCardModel {

    String navTitle;
    String navText;
    int image;
    int color;

    public PatientNavCardModel(String navTitle, String navText, int image, int color) {
        this.navTitle = navTitle;
        this.navText = navText;
        this.image = image;
        this.color = color;
    }

    public int getColor() {
        return color;
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
