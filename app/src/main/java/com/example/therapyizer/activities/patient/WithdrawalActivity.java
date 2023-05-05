package com.example.therapyizer.activities.patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.therapyizer.R;
import com.example.therapyizer.databinding.ActivityWithdrawalBinding;
import com.example.therapyizer.ml.SymptomModel;
import com.example.therapyizer.utilities.Constants;
import com.example.therapyizer.utilities.PreferenceManager;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class WithdrawalActivity extends AppCompatActivity {

    ActivityWithdrawalBinding binding;
    PreferenceManager preferenceManager;
    float predictionVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_withdrawal);
        preferenceManager = new PreferenceManager(getApplicationContext());
        binding.topActionBar.backButton.setOnClickListener(view -> onBackPressed());

        ByteBuffer byteBuffer = ByteBuffer.allocate(3*4);
        byteBuffer.putFloat(getAgeValue());
        byteBuffer.putFloat(getDrugValue());
        byteBuffer.putFloat(getDosageValue());

        getPrediction(byteBuffer);
        setSymptomsList();
    }

    private void setSymptomsList(){
        String[] symptomList;
        if(predictionVal == 0f){
            symptomList = getResources().getStringArray(R.array.symptom_category_01);
        } else if (predictionVal == 1f) {
            symptomList = getResources().getStringArray(R.array.symptom_category_02);
        }else {
            symptomList = getResources().getStringArray(R.array.symptom_category_03);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.activity_symptom_list_item,
                R.id.symptomText,
                symptomList);
        binding.withdrawalSymptomsListView.setAdapter(arrayAdapter);
    }

    private float getAgeValue(){
        return Float.parseFloat(preferenceManager.getString(Constants.AGE_INFORMATION));
    }

    private float getDrugValue(){
        String drug = preferenceManager.getString(Constants.DRUG);
        if(drug.equals("Cannabis"))
            return 0f;
        else if (drug.equals("Heroin"))
            return 1f;
        else
            return 2f;
    }

    private float getDosageValue(){
        String dosage = preferenceManager.getString(Constants.DOSAGE);
        if(dosage.equals("Occasionally"))
            return 0f;
        else if (dosage.equals("Every Day"))
            return 1f;
        else
            return 2f;
    }

    private void getPrediction(ByteBuffer byteBuffer){
        try {
            SymptomModel model = SymptomModel.newInstance(this);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 3},
                    DataType.FLOAT32);
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            SymptomModel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            returnPrediction(outputFeature0.getFloatArray());
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }
    }

    private void returnPrediction(float[] predictions){
        float max = predictions[0];
        for (int i = 1; i < predictions.length; i++) {
            if (predictions[i] > max) {
                max = predictions[i];
            }
        }
        predictionVal = max;
    }
}