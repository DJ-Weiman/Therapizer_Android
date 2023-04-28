package com.example.therapyizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivityOutgoingInvitationBinding;
import com.example.therapyizer.models.OnlineCallModel;
import com.example.therapyizer.models.User;
import com.example.therapyizer.network.ApiClient;
import com.example.therapyizer.network.ApiService;
import com.example.therapyizer.utilities.Constants;
import com.example.therapyizer.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutgoingInvitationActivity extends AppCompatActivity {

    ActivityOutgoingInvitationBinding binding;
    private PreferenceManager preferenceManager;
    private String inviterToken = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_outgoing_invitation);

        preferenceManager = new PreferenceManager(getApplicationContext());
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (task.isSuccessful() && task.getResult() != null) {
                    inviterToken = task.getResult();
                }
            }
        });

        String meetingType = getIntent().getStringExtra("type");

        if(meetingType != null) {
            if (meetingType.equals("video")){
                binding.imageMeetingType.setImageResource(R.drawable.ic_video);
            }
        }

        OnlineCallModel user = (OnlineCallModel)getIntent().getSerializableExtra("user");
        if(user != null){
            binding.textFirstChar.setText(user.getDoctorName().substring(0,1));
            binding.textUsername.setText(String.format("%s", user.getDoctorName()));
        }

        binding.imageStopInvitation.setOnClickListener(view -> onBackPressed());

        if(user != null){
            initiateMeeting(user.getFcm_Token());
        }

    }

    private void initiateMeeting(String recieverToken){
        try{
            JSONArray tokens = new JSONArray();
            tokens.put(recieverToken);

            JSONObject body = new JSONObject();
            JSONObject data = new JSONObject();

            data.put(Constants.REMOTE_MSG_TYPE, Constants.REMOTE_MSG_INVITATION);
            data.put(Constants.KEY_FIRST_NAME, preferenceManager.getString(Constants.KEY_FIRST_NAME));
            data.put(Constants.KEY_LAST_NAME, preferenceManager.getString(Constants.KEY_LAST_NAME));
            data.put(Constants.KEY_EMAIL, preferenceManager.getString(Constants.KEY_EMAIL));
            data.put(Constants.REMOTE_MSG_INVITER_TOKEN, inviterToken);

            body.put(Constants.REMOTE_MSG_DATA, data);
            body.put(Constants.REMOTE_MSG_REGISTRATION_IDS, tokens);

            sendRemoteMessage(body.toString(), Constants.REMOTE_MSG_INVITATION);

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void sendRemoteMessage(String remoteMessageBody, String type){
        ApiClient.getClient().create(ApiService.class).sendRemoteMessage(
                Constants.getRemoteMessageHeaders(), remoteMessageBody
        ).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(OutgoingInvitationActivity.this, "Invitation Sent sucessfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(OutgoingInvitationActivity.this, response.message() , Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(OutgoingInvitationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}