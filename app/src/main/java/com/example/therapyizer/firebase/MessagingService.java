package com.example.therapyizer.firebase;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.therapyizer.activities.IncomingInvitationActivity;
import com.example.therapyizer.utilities.Constants;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Log.d("messageChecker", "onMessageReceived: ");

        if(message != null){
            Intent intent = new Intent(getApplicationContext(), IncomingInvitationActivity.class);
            intent.putExtra(
                    Constants.KEY_FIRST_NAME,
                    message.getData().get(Constants.KEY_FIRST_NAME)
            );
            intent.putExtra(
                    Constants.KEY_LAST_NAME,
                    message.getData().get(Constants.KEY_LAST_NAME)
            );
            intent.putExtra(
                    Constants.KEY_EMAIL,
                    message.getData().get(Constants.KEY_EMAIL)
            );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

}
