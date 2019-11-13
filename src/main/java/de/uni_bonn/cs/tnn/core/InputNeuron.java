package de.uni_bonn.cs.tnn.core;

public class InputNeuron extends Neuron {

    public InputNeuron() {
        super(TransferFuncType.IDENTITY);
    }

    @Override
    public void backprop(double teacherY) {
        //ignore
    }

    double calcDelta(double teacherY) {
        //ignore
        return 0;
    }
}
