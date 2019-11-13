package de.uni_bonn.cs.tnn.core;

import java.util.Random;

public class Synapse implements Input{

    Neuron input;
    Neuron output;
    double weight;

    public Synapse(Neuron input) {
        this.input = input;
        input.addOutSynapse(this);
        Random r = new Random();
        this.weight = 1.0*r.nextDouble() - 0.5; //TODO add way to adjust random range programmatically
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
