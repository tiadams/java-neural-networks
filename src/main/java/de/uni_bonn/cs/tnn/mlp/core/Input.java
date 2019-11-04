package de.uni_bonn.cs.tnn.mlp.core;

public interface Input {

    double getWeightedValue();
    double getUnweightedOutput();
    void updateWeight(double newWeight);

}
