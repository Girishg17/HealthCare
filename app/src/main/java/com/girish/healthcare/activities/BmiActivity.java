package com.girish.healthcare.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import android.os.Bundle;

import com.girish.healthcare.R;

import com.girish.healthcare.controller.BMIController;
import com.girish.healthcare.controller.BMIView;


public class BmiActivity extends AppCompatActivity {
    private EditText weightInput;
    private EditText heightInput;
    private TextView bmiResult;
    public BMIController controller;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        weightInput = findViewById(R.id.weight_input);
        heightInput = findViewById(R.id.height_input);
        bmiResult = findViewById(R.id.bmi_result);
        Button calculateButton = findViewById(R.id.calculate_button);
        controller = new BMIController(this);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double weight = Double.parseDouble(weightInput.getText().toString());
                double height = Double.parseDouble(heightInput.getText().toString());
                controller.calculateBMI(weight, height);
            }
        });
    }

    public void setBmiResult(double bmi) {
        bmiResult.setText("Your BMI is: " + String.format("%.2f", bmi));
    }
}


//public class BmiActivity extends AppCompatActivity {
//    private EditText weightInput;
//    private EditText heightInput;
//    private TextView bmiResult;
//
//
//
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bmi);
//
//        weightInput = (EditText) findViewById(R.id.weight_input);
//        heightInput = (EditText) findViewById(R.id.height_input);
//        bmiResult = (TextView) findViewById(R.id.bmi_result);
////        double weight = Double.parseDouble(weightInput.getText().toString());
////        double height = Double.parseDouble(heightInput.getText().toString());
//        Button calculateButton = (Button) findViewById(R.id.calculate_button);
//
//              calculateButton.setOnClickListener(new View.OnClickListener() {
//                  @Override
//                  public void onClick(View view) {
//                double weight = Double.parseDouble(weightInput.getText().toString());
//                double height = Double.parseDouble(heightInput.getText().toString());
//
//                      double bmi = calculateBMI(weight, height);
//
//                      bmiResult.setText("Your BMI is: " + String.format("%.2f", bmi));
//
//
//                  }
//              });
//
//    }
//
//    private double calculateBMI(double weight, double height) {
//        return weight / (height / 100 * height / 100);
//    }
//    }
