package de.uni_bonn.cs.tnn.mlp.core;

public class IdentityTransferFunction implements TransferFunction {

    @Override
    public double calculate(double input) {
        return input;
    }

    @Override
    public double differentiate(double input) {
        return 1;
    }
}
