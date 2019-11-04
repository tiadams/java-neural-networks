package de.uni_bonn.cs.tnn.mlp.core;

public class LogisticTransferFunction implements TransferFunction{

    @Override
    public double calculate(double input) {
        return 1 / (1 + Math.pow(Math.E, -input));
    }

    @Override
    public double differentiate(double input) {
        return calculate(input) * (1 - calculate(input));
    }
}
