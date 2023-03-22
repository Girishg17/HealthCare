package com.girish.healthcare.models;

public class BMIModel {
    private double weight;
    private double height;
    private double bmi;

    public BMIModel(double weight, double height) {
        this.weight = weight;
        this.height = height;
        this.bmi = calculateBMI(weight, height);
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getBmi() {
        return bmi;
    }

    private double calculateBMI(double weight, double height) {
        return weight / (height / 100 * height / 100);
    }
}

