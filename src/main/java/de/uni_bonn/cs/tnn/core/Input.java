package de.uni_bonn.cs.tnn.core;

public interface Input {

    double getWeightedValue();
    double getUnweightedOutput();
    void updateWeight(double newWeight);

}
