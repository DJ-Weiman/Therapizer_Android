package com.example.therapyizer.models;

public class NotesModel {

    String notesDate;
    String notesClient;

    public NotesModel(String notesDate, String notesClient) {
        this.notesDate = notesDate;
        this.notesClient = notesClient;
    }

    public String getNotesDate() {
        return notesDate;
    }

    public String getNotesClient() {
        return notesClient;
    }
}
