package com.example.therapyizer.activities.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.therapyizer.interfaces.UserGoalDao;
import com.example.therapyizer.models.UserGoal;

@Database(entities = {UserGoal.class}, version=1)
public abstract class UserGoalDatabase extends RoomDatabase {

    public abstract UserGoalDao userGoalDao();

}
