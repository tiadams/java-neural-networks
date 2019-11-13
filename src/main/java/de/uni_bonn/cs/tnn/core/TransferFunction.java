package de.uni_bonn.cs.tnn.core;

public interface TransferFunction {

    public double calculate(double input);
    public double differentiate(double input);
}
