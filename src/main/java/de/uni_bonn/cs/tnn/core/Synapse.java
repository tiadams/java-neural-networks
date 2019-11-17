package de.uni_bonn.cs.tnn.core;

public class Synapse implements Input{

    Neuron input;
    Neuron output;
    double weight;

    public Synapse(Neuron input, double weight) {
        this.input = input;
        input.addOutSynapse(this);
        this.weight = weight;
    }

    @Override
    public double getWeightedValue() {
        return input.calculateOutput() * weight;
    }

    public double getUnweightedOutput() {
        return input.calculateOutput();
    }
    public void updateWeight(double weightDelta){
        weight += weightDelta;
    }
    public double getWeight(){
        return weight;
    }
    public void setOutputNeuron(Neuron n){
        output = n;
    }
    public double getOutputNeuronDelta(){
        return output.getLastDelta();
    }
}
