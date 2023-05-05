package com.example.therapyizer.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserGoal {

    @PrimaryKey(autoGenerate = true)
    public int goalId;

    @ColumnInfo(name="goal_title")
    public String goalTitle;

    @ColumnInfo(name="days_to_complete")
    public int daysToComplete;

    @ColumnInfo(name="current_progress")
    public int current_progress;

    public UserGoal(String goalTitle, int daysToComplete, int current_progress) {
        this.goalTitle = goalTitle;
        this.daysToComplete = daysToComplete;
        this.current_progress = current_progress;
    }

}