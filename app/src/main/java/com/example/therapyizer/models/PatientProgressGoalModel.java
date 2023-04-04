package com.example.therapyizer.models;

public class PatientProgressGoalModel {

    String goalTitle;
    String goalText;
    int progressDays;

    public PatientProgressGoalModel(String goalTitle, String goalText, int progressDays) {
        this.goalTitle = goalTitle;
        this.goalText = goalText;
        this.progressDays = progressDays;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public String getGoalText() {
        return goalText;
    }

    public int getProgressDays() {
        return progressDays;
    }
}
