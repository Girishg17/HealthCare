package com.girish.healthcare.controller;


import com.girish.healthcare.activities.BmiActivity;

import com.girish.healthcare.models.BMIModel;

public class BMIController {



    public BmiActivity view;

    public BMIController(BmiActivity bmiActivity) {
        this.view=bmiActivity;
    }



    public void calculateBMI(double weight, double height) {
        BMIModel model = new BMIModel(weight, height);
        view.setBmiResult(model.getBmi());
    }
}



