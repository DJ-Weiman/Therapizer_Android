package com.example.therapyizer.activities.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.patient.PatientMainPageActivity;
import com.example.therapyizer.activities.professional.ProfessionalMainPageActivity;
import com.example.therapyizer.databinding.ActivityProfessionalRegistrationBinding;
import com.example.therapyizer.utilities.Constants;
import com.example.therapyizer.utilities.CurrentScreen;
import com.example.therapyizer.utilities.PreferenceManager;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ProfessionalRegistrationActivity extends AppCompatActivity {

    ActivityProfessionalRegistrationBinding binding;
    private static CurrentScreen currentScreen;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceManager = new PreferenceManager(getApplicationContext());

        binding = DataBindingUtil.setContentView(this, R.layout.activity_professional_registration);
        currentScreen = CurrentScreen.GENERAL_INFORMATION;

        binding.generalInformationMarker.setBackgroundColor(getColor(R.color.appThemePurple));
        binding.credentialsMarker.setBackgroundColor(getColor(R.color.unselected_gray));

        setupButtons();
    }


    private void setupButtons(){

        binding.topActionBar.backButton.setOnClickListener(view -> {
            if(currentScreen.equals(CurrentScreen.GENERAL_INFORMATION))
                onBackPressed();
            else if (currentScreen.equals(CurrentScreen.CREDENTIALS)) {
                currentScreen = CurrentScreen.GENERAL_INFORMATION;
                binding.credentialsMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                binding.generalInformationMarker.setBackgroundColor(getColor(R.color.appThemePurple));
                if(binding.generalInformationScrollView.getVisibility() == View.GONE){
                    binding.credentialsScrollView.setVisibility(View.GONE);
                    binding.generalInformationScrollView.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(currentScreen.equals(CurrentScreen.GENERAL_INFORMATION)){

                    currentScreen = CurrentScreen.CREDENTIALS;

                    binding.generalInformationMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                    binding.credentialsMarker.setBackgroundColor(getColor(R.color.appThemePurple));

                    if(binding.credentialsScrollView.getVisibility() == View.GONE){
                        binding.generalInformationScrollView.setVisibility(View.GONE);
                        binding.credentialsScrollView.setVisibility(View.VISIBLE);
                    }
                }else if (currentScreen.equals(CurrentScreen.CREDENTIALS)){
                    verifyInputs();
                }

            }
        });
    }

    private void verifyInputs(){
        if (binding.firstNameInput.getText().toString().trim().isEmpty())
            Toast.makeText(ProfessionalRegistrationActivity.this, "Please Enter your First Name", Toast.LENGTH_SHORT).show();
        else if (binding.lastNameInput.getText().toString().trim().isEmpty())
            Toast.makeText(ProfessionalRegistrationActivity.this, "Please Enter your Last Name", Toast.LENGTH_SHORT).show();
        else if (binding.emailInput.getText().toString().trim().isEmpty())
            Toast.makeText(ProfessionalRegistrationActivity.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
        else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailInput.getText().toString()).matches())
            Toast.makeText(ProfessionalRegistrationActivity.this, "Please Enter a valid Email Address", Toast.LENGTH_SHORT).show();
        else if(binding.professionInput.getText().toString().trim().isEmpty())
            Toast.makeText(ProfessionalRegistrationActivity.this, "Please Enter your Profession", Toast.LENGTH_SHORT).show();
        else if(binding.hospitalInput.getText().toString().trim().isEmpty())
            Toast.makeText(ProfessionalRegistrationActivity.this, "Please Enter your Hospital", Toast.LENGTH_SHORT).show();
        else if (binding.usernameInput.getText().toString().trim().isEmpty())
            Toast.makeText(ProfessionalRegistrationActivity.this, "Please Enter your Username", Toast.LENGTH_SHORT).show();
        else if (binding.passwordInput.getText().toString().trim().isEmpty())
            Toast.makeText(ProfessionalRegistrationActivity.this, "Please Enter a Password", Toast.LENGTH_SHORT).show();
        else if (binding.confirmPasswordInput.getText().toString().trim().isEmpty())
            Toast.makeText(ProfessionalRegistrationActivity.this, "Please Enter confirm your password", Toast.LENGTH_SHORT).show();
        else if (!binding.passwordInput.getText().toString().equals(binding.confirmPasswordInput.getText().toString()))
            Toast.makeText(ProfessionalRegistrationActivity.this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
        else if(binding.registrationCodeInput.getText().toString().trim().isEmpty())
            Toast.makeText(ProfessionalRegistrationActivity.this, "Please Enter your registration code", Toast.LENGTH_SHORT).show();
        else {
            registerUser();
        }
    }

    private void registerUser() {

        binding.nextButton.setVisibility(View.INVISIBLE);
        binding.signUpProgressBar.setVisibility(View.VISIBLE);

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();

        user.put(Constants.KEY_FIRST_NAME, binding.firstNameInput.getText().toString());
        user.put(Constants.KEY_LAST_NAME, binding.lastNameInput.getText().toString());
        user.put(Constants.KEY_EMAIL, binding.emailInput.getText().toString());
        user.put(Constants.KEY_PROFESSION, binding.professionInput.getText().toString());
        user.put(Constants.KEY_HOSPITAL, binding.hospitalInput.getText().toString());
        user.put(Constants.KEY_USERNAME, binding.usernameInput.getText().toString());
        user.put(Constants.KEY_PASSWORD, binding.passwordInput.getText().toString());
        user.put(Constants.KEY_REG_CODE, binding.registrationCodeInput.getText().toString());
        user.put(Constants.KEY_USER_TYPE, Constants.PROFESSIONAL_USER_TYPE);

        database.collection(Constants.KEY_COLLECTION_USERS)
                .add(user)
                .addOnSuccessListener(documentReference -> {

                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                    preferenceManager.putString(Constants.KEY_FIRST_NAME, binding.firstNameInput.getText().toString());
                    preferenceManager.putString(Constants.KEY_LAST_NAME, binding.lastNameInput.getText().toString());
                    preferenceManager.putString(Constants.KEY_EMAIL, binding.emailInput.getText().toString());
                    preferenceManager.putString(Constants.KEY_USERNAME, binding.usernameInput.getText().toString());
                    preferenceManager.putString(Constants.KEY_USER_TYPE, Constants.PROFESSIONAL_USER_TYPE);

                    Intent intent = new Intent(getApplicationContext(), ProfessionalMainPageActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }).addOnFailureListener(e -> {
                    binding.nextButton.setVisibility(View.VISIBLE);
                    binding.signUpProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(ProfessionalRegistrationActivity.this, "Failed to Register User", Toast.LENGTH_SHORT).show();
                });

    }



}