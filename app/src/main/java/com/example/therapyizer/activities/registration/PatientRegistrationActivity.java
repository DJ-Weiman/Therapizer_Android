package com.example.therapyizer.activities.registration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.therapyizer.R;
import com.example.therapyizer.activities.MainActivity;
import com.example.therapyizer.activities.patient.PatientMainPageActivity;
import com.example.therapyizer.databinding.ActivityPatientRegistrationBinding;
import com.example.therapyizer.utilities.Constants;
import com.example.therapyizer.utilities.CurrentScreen;
import com.example.therapyizer.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class PatientRegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityPatientRegistrationBinding binding;
    private static CurrentScreen currentScreen;
    private PreferenceManager preferenceManager;
    private String[] ages;
    private String[] drugs;
    private String[] dosages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferenceManager = new PreferenceManager(getApplicationContext());

        binding = DataBindingUtil.setContentView(this, R.layout.activity_patient_registration);
        currentScreen = CurrentScreen.GENERAL_INFORMATION;

        binding.generalInformationMarker.setBackgroundColor(getColor(R.color.appThemePurple));
        binding.credentialsMarker.setBackgroundColor(getColor(R.color.unselected_gray));
        binding.personalInformationMarker.setBackgroundColor(getColor(R.color.unselected_gray));

        setupButtons();
    }

    private void setupButtons() {

        binding.topActionBar.backButton.setOnClickListener(view -> {
            if (currentScreen.equals(CurrentScreen.GENERAL_INFORMATION))
                onBackPressed();
            else if (currentScreen.equals(CurrentScreen.CREDENTIALS)) {
                currentScreen = CurrentScreen.GENERAL_INFORMATION;
                binding.credentialsMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                binding.personalInformationMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                binding.generalInformationMarker.setBackgroundColor(getColor(R.color.appThemePurple));

                binding.credentialsLinearLayout.setVisibility(View.GONE);
                binding.personalInformationLinearLayout.setVisibility(View.GONE);
                binding.generalInformationLinearLayout.setVisibility(View.VISIBLE);

            } else if (currentScreen.equals(CurrentScreen.PERSONAL_INFORMATION)) {
                currentScreen = CurrentScreen.CREDENTIALS;
                binding.credentialsMarker.setBackgroundColor(getColor(R.color.appThemePurple));
                binding.generalInformationMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                binding.personalInformationMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                if (binding.generalInformationLinearLayout.getVisibility() == View.GONE) {
                    binding.credentialsLinearLayout.setVisibility(View.VISIBLE);
                    binding.generalInformationLinearLayout.setVisibility(View.GONE);
                    binding.personalInformationLinearLayout.setVisibility(View.GONE);
                }
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentScreen.equals(CurrentScreen.GENERAL_INFORMATION)) {
                    currentScreen = CurrentScreen.CREDENTIALS;

                    binding.generalInformationMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                    binding.personalInformationMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                    binding.credentialsMarker.setBackgroundColor(getColor(R.color.appThemePurple));

                    binding.generalInformationLinearLayout.setVisibility(View.GONE);
                    binding.credentialsLinearLayout.setVisibility(View.VISIBLE);
                    binding.personalInformationLinearLayout.setVisibility(View.GONE);
                } else if (currentScreen.equals(CurrentScreen.CREDENTIALS)) {
                    currentScreen = CurrentScreen.PERSONAL_INFORMATION;

                    binding.generalInformationMarker.setBackgroundColor(getColor(R.color.unselected_gray));
                    binding.personalInformationMarker.setBackgroundColor(getColor(R.color.appThemePurple));
                    binding.credentialsMarker.setBackgroundColor(getColor(R.color.unselected_gray));

                    binding.generalInformationLinearLayout.setVisibility(View.GONE);
                    binding.credentialsLinearLayout.setVisibility(View.GONE);
                    binding.personalInformationLinearLayout.setVisibility(View.VISIBLE);

                } else if (currentScreen.equals(CurrentScreen.PERSONAL_INFORMATION)) {
                    verifyInputs();
                }

            }
        });
    }

    private void verifyInputs() {

        int age = 0;
        if(!binding.ageInput.getText().toString().isEmpty()) {
            age = Integer.parseInt(binding.ageInput.getText().toString());
        }

        if (binding.firstNameInput.getText().toString().trim().isEmpty())
            Toast.makeText(PatientRegistrationActivity.this, "Please Enter your First Name", Toast.LENGTH_SHORT).show();
        else if (binding.lastNameInput.getText().toString().trim().isEmpty())
            Toast.makeText(PatientRegistrationActivity.this, "Please Enter your Last Name", Toast.LENGTH_SHORT).show();
        else if (binding.emailInput.getText().toString().trim().isEmpty())
            Toast.makeText(PatientRegistrationActivity.this, "Please Enter your Email", Toast.LENGTH_SHORT).show();
        else if (!Patterns.EMAIL_ADDRESS.matcher(binding.emailInput.getText().toString()).matches())
            Toast.makeText(PatientRegistrationActivity.this, "Please Enter a valid Email Address", Toast.LENGTH_SHORT).show();
        else if (binding.usernameInput.getText().toString().trim().isEmpty())
            Toast.makeText(PatientRegistrationActivity.this, "Please Enter your Username", Toast.LENGTH_SHORT).show();
        else if (binding.passwordInput.getText().toString().trim().isEmpty())
            Toast.makeText(PatientRegistrationActivity.this, "Please Enter a Password", Toast.LENGTH_SHORT).show();
        else if (binding.confirmPasswordInput.getText().toString().trim().isEmpty())
            Toast.makeText(PatientRegistrationActivity.this, "Please Enter confirm your password", Toast.LENGTH_SHORT).show();
        else if (!binding.passwordInput.getText().toString().equals(binding.confirmPasswordInput.getText().toString()))
            Toast.makeText(PatientRegistrationActivity.this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show();
        else if(binding.ageInput.getText().toString().isEmpty())
            Toast.makeText(PatientRegistrationActivity.this, "Please Enter an age value", Toast.LENGTH_SHORT).show();
        else if(binding.drugInput.getText().toString().isEmpty())
            Toast.makeText(PatientRegistrationActivity.this, "Please Enter an Drug", Toast.LENGTH_SHORT).show();
        else if(binding.dosageInput.getText().toString().isEmpty())
            Toast.makeText(PatientRegistrationActivity.this, "Please Enter an Dosage", Toast.LENGTH_SHORT).show();
        else if(age < 18 || age > 30)
            Toast.makeText(PatientRegistrationActivity.this, "Enter a correct age value", Toast.LENGTH_SHORT).show();
        else if(checkDrugInput())
            Toast.makeText(PatientRegistrationActivity.this, "Enter a Valid drug type", Toast.LENGTH_SHORT).show();
        else if(checkDosageInput())
            Toast.makeText(PatientRegistrationActivity.this, "Enter a valid Dosage", Toast.LENGTH_SHORT).show();
        else {
            registerUser();
        }
    }

    private boolean checkDrugInput(){
        String drug = binding.drugInput.getText().toString();
        if(drug.equals("Cannabis"))
            return false;
        else if (drug.equals("Meth"))
            return false;
        else if (drug.equals("Heroin"))
            return false;
        else
            return true;
    }

    private boolean checkDosageInput(){
        String drug = binding.dosageInput.getText().toString();
        if(drug.equals("Occasionally"))
            return false;
        else if (drug.equals("Every Day"))
            return false;
        else if (drug.equals("Rarely"))
            return false;
        else
            return true;
    }

    private void registerUser() {

        binding.nextButton.setVisibility(View.INVISIBLE);
        binding.signUpProgressBar.setVisibility(View.VISIBLE);

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, Object> user = new HashMap<>();

        user.put(Constants.KEY_FIRST_NAME, binding.firstNameInput.getText().toString());
        user.put(Constants.KEY_LAST_NAME, binding.lastNameInput.getText().toString());
        user.put(Constants.KEY_EMAIL, binding.emailInput.getText().toString());
        user.put(Constants.KEY_USERNAME, binding.usernameInput.getText().toString());
        user.put(Constants.KEY_PASSWORD, binding.passwordInput.getText().toString());
        user.put(Constants.KEY_USER_TYPE, Constants.PATIENT_USER_TYPE);

        database.collection(Constants.KEY_COLLECTION_USERS)
                .add(user)
                .addOnSuccessListener(documentReference -> {

                    preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                    preferenceManager.putString(Constants.KEY_USER_ID, documentReference.getId());
                    preferenceManager.putString(Constants.KEY_FIRST_NAME, binding.firstNameInput.getText().toString());
                    preferenceManager.putString(Constants.KEY_LAST_NAME, binding.lastNameInput.getText().toString());
                    preferenceManager.putString(Constants.KEY_EMAIL, binding.emailInput.getText().toString());
                    preferenceManager.putString(Constants.KEY_USERNAME, binding.usernameInput.getText().toString());
                    preferenceManager.putString(Constants.KEY_USER_TYPE, Constants.PATIENT_USER_TYPE);

                    savePersonalInformation();

                }).addOnFailureListener(e -> {
                    binding.nextButton.setVisibility(View.VISIBLE);
                    binding.signUpProgressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(PatientRegistrationActivity.this, "Failed to Register User", Toast.LENGTH_SHORT).show();
                });

    }

    private void savePersonalInformation(){

        preferenceManager.putString(Constants.AGE_INFORMATION, binding.ageInput.getText().toString());
        preferenceManager.putString(Constants.DRUG, binding.drugInput.getText().toString());
        preferenceManager.putString(Constants.DOSAGE, binding.dosageInput.getText().toString());

        Intent intent = new Intent(getApplicationContext(), PatientScreeeningActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

