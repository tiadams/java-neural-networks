package de.uni_bonn.cs.tnn.core;

public class HiddenNeuron extends Neuron{

    public HiddenNeuron(TransferFuncType function) {
        super(function);
    }

    @Override
    public void backprop(double teacherY) {
        double delta_h =  calcDelta();
        for(Input synapse : inputs) {
            double w_gh = learningRate * delta_h * synapse.getUnweightedOutput();
            synapseWeightUpdates.put(synapse, w_gh);
        }
    }

    private double calcDelta() {
        double deltaKWeightedSum = 0.0;
        for(Synapse hk : outSynapses) {
            double delta_k = hk.getOutputNeuronDelta();
            double w_hk = hk.getWeight();
            deltaKWeightedSum += delta_k*w_hk;
        }
        double delta = deltaKWeightedSum * transferFunction.differentiate(getSum());
        lastDelta = delta;
        return delta;
    }
}
