package com.example.therapyizer.listeners;

import com.example.therapyizer.models.User;

public interface UsersListener {

    void initiateVideoMeeting(User user);

    void initiateAudioMeeting(User user);

}
