package de.uni_bonn.cs.tnn.core;

public class TanhTransferFunction implements TransferFunction{

    @Override
    public double calculate(double input) {
        return Math.tanh(input);
    }

    @Override
    public double differentiate(double input) {
        // TODO: check is tanh^2(z) = tanh(z)^2
        return 1 - Math.pow(calculate(input), 2);
    }
}
