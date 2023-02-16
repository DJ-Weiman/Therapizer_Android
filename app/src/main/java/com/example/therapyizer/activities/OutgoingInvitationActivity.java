package com.example.therapyizer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivityOutgoingInvitationBinding;
import com.example.therapyizer.models.User;

public class OutgoingInvitationActivity extends AppCompatActivity {

    ActivityOutgoingInvitationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_outgoing_invitation);

        String meetingType = getIntent().getStringExtra("type");

        if(meetingType != null) {
            if (meetingType.equals("video")){
                binding.imageMeetingType.setImageResource(R.drawable.ic_video);
            }
        }

        User user = (User) getIntent().getSerializableExtra("user");
        if(user != null){
            binding.textFirstChar.setText(user.firstName.substring(0,1));
            binding.textUsername.setText(String.format("%s %s", user.firstName, user.lastName));
            binding.textEmail.setText(user.email);
        }

        binding.imageStopInvitation.setOnClickListener(view -> onBackPressed());

    }
}