package de.uni_bonn.cs.tnn.core;

public class HiddenNeuron extends Neuron{

    public HiddenNeuron(TransferFuncType function) {
        super(function);
    }

    @Override
    public void backprop(double teacherY) {
        double delta_h =  calcDelta();
        for(Input synapse : inputs) {
            synapse.updateWeight(learningRate * delta_h * synapse.getUnweightedOutput());
        }
    }

    private double calcDelta() {
        double sum = 0.0;
        for(Synapse s : outSynapses) {
            sum += s.getWeight()*s.getOutputNeuronDelta();
        }
        double delta = sum * transferFunction.differentiate(getSum());
        lastDelta = delta;
        return delta;
    }
}
