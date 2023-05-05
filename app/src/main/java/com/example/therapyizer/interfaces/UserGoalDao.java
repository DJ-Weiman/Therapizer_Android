package com.example.therapyizer.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.therapyizer.models.UserGoal;

import java.util.List;

@Dao
public interface UserGoalDao {

    @Insert
    void insertAll(UserGoal... userGoals);

    @Insert
    void insert(UserGoal userGoal);

    @Query("UPDATE usergoal SET current_progress=:progress WHERE goalId = :id")
    void updateProgress(int id, int progress);

    @Query("SELECT * FROM usergoal")
    List<UserGoal> getAllUSerGoals();
}
