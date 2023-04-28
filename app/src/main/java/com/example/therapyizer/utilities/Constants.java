package com.example.therapyizer.utilities;

import java.util.HashMap;

public class Constants {

    public static final String KEY_COLLECTION_USERS = "users";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_USERNAME = "user_name";
    public static final String KEY_USER_TYPE = "user_type";

    public static final String KEY_PROFESSION = "profession";
    public static final String KEY_HOSPITAL = "hospital";
    public static final String KEY_REG_CODE = "registration_code";

    public static final String PATIENT_USER_TYPE = "Patient";
    public static final String PROFESSIONAL_USER_TYPE = "Professional";

    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_FCM_TOKEN = "fcm_token";

    public static final String KEY_PREFERENCE_NAME = "therapizerPreference";

    public static final String KEY_IS_SIGNED_IN = "isSignedIn";

    public static final String REMOTE_MSG_AUTHORIZATION = "Authorization";
    public static final String REMOTE_MSG_CONTENT_TYPE = "content_type";

    public static final String REMOTE_MSG_TYPE = "type";
    public static final String REMOTE_MSG_INVITATION = "invitation";
    public static final String REMOTE_MSG_MEETING_TYPE = "meetingType";
    public static final String REMOTE_MSG_INVITER_TOKEN = "inviterToken";
    public static final String REMOTE_MSG_DATA = "data";
    public static final String REMOTE_MSG_REGISTRATION_IDS = "registration_ids";

    public static HashMap<String, String> getRemoteMessageHeaders(){
        HashMap<String, String> headers = new HashMap<>();
        headers.put(
                Constants.REMOTE_MSG_AUTHORIZATION,
                "key=AAAAeXpOxhg:APA91bHUVWqDhPiX-CN0FoOeyPk6X4lxADFEmraxO5LQtwOvgXTzqGUCWC1FyxKheTEtt-p0KcQ8R4VQtMkMkEiZoLPPbkNqATfkceRAqYB5QXVRGr6J_OWxRdhRFuAhr5Z-_Kncd2o5"
        );
        headers.put(Constants.REMOTE_MSG_CONTENT_TYPE, "application/json");
        return headers;
    }
}
