package com.example.therapyizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.patient.PatientMainPageActivity;
import com.example.therapyizer.activities.professional.ProfessionalMainPageActivity;
import com.example.therapyizer.databinding.ActivitySignInBinding;
import com.example.therapyizer.utilities.Constants;
import com.example.therapyizer.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        preferenceManager = new PreferenceManager(getApplicationContext());

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());

        binding.loginButton.setOnClickListener(view -> {
            signInButtonPressed();
        });
    }


    private void signInButtonPressed() {
        if (binding.emailInput.getText().toString().trim().isEmpty())
            Toast.makeText(this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
        else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailInput.getText().toString()).matches())
            Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
        else if (binding.passwordInput.getText().toString().trim().isEmpty())
            Toast.makeText(this, "Enter Your password", Toast.LENGTH_SHORT).show();
        else
            signIn();
    }

    private void signIn() {
        binding.loginButton.setVisibility(View.INVISIBLE);
        binding.signInProgressBar.setVisibility(View.VISIBLE);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(Constants.KEY_COLLECTION_USERS)
                .whereEqualTo(Constants.KEY_EMAIL, binding.emailInput.getText().toString())
                .whereEqualTo(Constants.KEY_PASSWORD, binding.passwordInput.getText().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null && task.getResult().getDocuments().size() > 0) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                            preferenceManager.putString(Constants.KEY_USER_ID, documentSnapshot.getId());
                            preferenceManager.putString(Constants.KEY_FIRST_NAME, documentSnapshot.getString(Constants.KEY_FIRST_NAME));
                            preferenceManager.putString(Constants.KEY_LAST_NAME, documentSnapshot.getString(Constants.KEY_LAST_NAME));
                            preferenceManager.putString(Constants.KEY_EMAIL, documentSnapshot.getString(Constants.KEY_EMAIL));
                            preferenceManager.putString(Constants.KEY_USER_TYPE, documentSnapshot.getString(Constants.KEY_USER_TYPE));

                            Intent intent;
                            if(documentSnapshot.getString(Constants.KEY_USER_TYPE).equals(Constants.PROFESSIONAL_USER_TYPE)) {
                                intent = new Intent(getApplicationContext(), ProfessionalMainPageActivity.class);
                            } else {
                                intent = new Intent(getApplicationContext(), PatientMainPageActivity.class);
                            }
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            binding.signInProgressBar.setVisibility(View.INVISIBLE);
                            binding.loginButton.setVisibility(View.VISIBLE);
                            Toast.makeText(SignInActivity.this, "Unable to Sign In", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    private void firebaseTesterFunction(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();
        user.put("first_name", "John");
        user.put("last_name", "Doe");
        user.put("email", "JD@gmail.com");

        database.collection("users")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(SignInActivity.this, "User Added", Toast.LENGTH_LONG).show();
                    Log.d("firebaseChecker", "Success: ");
                }).addOnFailureListener(e -> {
                    Log.d("firebaseChecker", "Failure: " + e.getMessage());
                    Toast.makeText(SignInActivity.this, "Failed To add user", Toast.LENGTH_LONG).show();
                });
    }
}

