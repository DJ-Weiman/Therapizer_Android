package com.example.therapyizer.models;

public class ClientModel {

    String clientName;
    String clientAge;

    public ClientModel(String clientName, String clientAge) {
        this.clientName = clientName;
        this.clientAge = clientAge;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientAge() {
        return clientAge;
    }
}
