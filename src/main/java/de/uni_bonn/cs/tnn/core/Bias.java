package de.uni_bonn.cs.tnn.core;

import java.util.Random;

public class Bias implements Input{

    double weight;

    Bias(){
        Random rnd = new Random();
        this.weight = rnd.nextDouble();

    }

    @Override
    public double getWeightedValue() {
        return weight;
    }

    @Override
    public double getUnweightedOutput() {
        return 1.0;
    }

    @Override
    public void updateWeight(double weightChange) {
        this.weight += weightChange;
    }
}
