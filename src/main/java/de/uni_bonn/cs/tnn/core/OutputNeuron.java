package de.uni_bonn.cs.tnn.core;

public class OutputNeuron extends Neuron {

    public OutputNeuron(TransferFuncType function) {
        super(function);
    }

    @Override
    public void backprop(double teacherY) {
        double delta_m = calcDelta(teacherY);
        for (Input input : inputs) {
            input.updateWeight(learningRate * delta_m * input.getUnweightedOutput());
        }
    }

    private double calcDelta(double teacherY) {
        double delta = (teacherY - calculateOutput()) * transferFunction.differentiate(getSum());
        lastDelta = delta;
        return delta;
    }
}
