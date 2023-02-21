package com.example.therapyizer.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivitySignUpBinding;
import com.example.therapyizer.utilities.Constants;
import com.example.therapyizer.utilities.PreferenceManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private PreferenceManager preferenceManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        binding.imageBack.setOnClickListener(view -> onBackPressed());
        binding.signInText.setOnClickListener(view -> onBackPressed());

        preferenceManager = new PreferenceManager(getApplicationContext());

//        signUpClicked();
    }

//    private void signUpClicked(){
//        binding.signUpButton.setOnClickListener(view -> {
//            if(binding.firstNameInput.getText().toString().trim().isEmpty())
//                Toast.makeText(SignUpActivity.this, "Please Enter your First Name", Toast.LENGTH_SHORT).show();
//            else if(binding.lastNameInput.getText().toString().trim().isEmpty())
//                Toast.makeText(SignUpActivity.this, "Please Enter your Last Name", Toast.LENGTH_SHORT).show();
//            else if(binding.emailInput.getText().toString().trim().isEmpty())
//                Toast.makeText(SignUpActivity.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
//            else if(!Patterns.EMAIL_ADDRESS.matcher(binding.emailInput.getText().toString()).matches())
//                Toast.makeText(SignUpActivity.this, "Please Enter a valid Email Address", Toast.LENGTH_SHORT).show();
//            else if(binding.passwordInput.getText().toString().trim().isEmpty())
//                Toast.makeText(SignUpActivity.this, "Please Enter a Password", Toast.LENGTH_SHORT).show();
//            else if(binding.confirmPasswordInput.getText().toString().trim().isEmpty())
//                Toast.makeText(SignUpActivity.this, "Please Enter confirm your password", Toast.LENGTH_SHORT).show();
//            else if(!binding.passwordInput.getText().toString().equals(binding.confirmPasswordInput.getText().toString()))
//                Toast.makeText(SignUpActivity.this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
//            else{
//                signUp();
//            }
//        });
//    }
//
//    private void signUp(){
//
//        binding.signUpButton.setVisibility(View.INVISIBLE);
//        binding.signUpProgressBar.setVisibility(View.VISIBLE);
//
//        FirebaseFirestore database = FirebaseFirestore.getInstance();
//
//        HashMap<String, Object> user = new HashMap<>();
//        user.put(Constants.KEY_FIRST_NAME, binding.firstNameInput.getText().toString());
//        user.put(Constants.KEY_LAST_NAME, binding.lastNameInput.getText().toString());
//        user.put(Constants.KEY_EMAIL, binding.emailInput.getText().toString());
//        user.put(Constants.KEY_PASSWORD, binding.passwordInput.getText().toString());
//
//        database.collection(Constants.KEY_COLLECTION_USERS)
//                .add(user)
//                .addOnSuccessListener(documentReference -> {
//
//                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
//                    preferenceManager.putString(Constants.KEY_USER_ID, documentReference.getId());
//                    preferenceManager.putString(Constants.KEY_FIRST_NAME, binding.firstNameInput.getText().toString());
//                    preferenceManager.putString(Constants.KEY_LAST_NAME, binding.lastNameInput.getText().toString());
//                    preferenceManager.putString(Constants.KEY_EMAIL, binding.passwordInput.getText().toString());
//
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//
//                }).addOnFailureListener(e -> {
//
//                    binding.signUpProgressBar.setVisibility(View.INVISIBLE);
//                    binding.signUpButton.setVisibility(View.VISIBLE);
//                    Toast.makeText(SignUpActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                });
//
//    }
}