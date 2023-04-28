package com.example.therapyizer.models;

import java.io.Serializable;

public class OnlineCallModel implements Serializable {

    String doctorId;
    String doctorName;
    String doctorProfession;
    String fcm_Token;

    public OnlineCallModel(String doctorId, String doctorName, String doctorProfession, String FCM_Token) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.doctorProfession = doctorProfession;
        this.fcm_Token = FCM_Token;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorProfession() {
        return doctorProfession;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getFcm_Token() {
        return fcm_Token;
    }
}
