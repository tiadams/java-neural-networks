package de.uni_bonn.cs.tnn.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Neuron {

    protected List<Input> inputs;
    protected Map<Input, Double> synapseWeightUpdates; //All w_gh changes calculated during backprop, this Neuron being h
    List<Synapse> outSynapses;
    protected TransferFunction transferFunction;
    double learningRate;
    double lastDelta;

    public Neuron(TransferFuncType function) {
        outSynapses = new ArrayList<>();
        inputs = new ArrayList<>();
        synapseWeightUpdates = new HashMap<>();
        learningRate = 0.01;
        switch(function){
            case TANH:
                this.transferFunction = new TanhTransferFunction();
                break;
            case LOGISTIC:
                this.transferFunction = new LogisticTransferFunction();
                break;
            case IDENTITY:
                this.transferFunction = new IdentityTransferFunction();
                break;
            case GAUSSIAN:
                this.transferFunction = new GaussianTransferFunction();
                break;
        }
    }
    public double getSum(){ //returns net_m
        return inputs.stream().mapToDouble(input -> (double) input.getWeightedValue()).sum();
    }
    public double calculateOutput(){
        return transferFunction.calculate(getSum());
    }
    public abstract void backprop(double teacherY);
    public double getLastDelta(){
        return lastDelta;
    }
    public void applySynapseWeightUpdates(){
        for(Input synapse : synapseWeightUpdates.keySet()){
            synapse.updateWeight(synapseWeightUpdates.get(synapse));
        }
        synapseWeightUpdates = new HashMap<>(); //all changes have been applied, continue empty
    }
    public void addInput(Input i) {
        this.inputs.add(i);
    }
    public void addOutSynapse(Synapse s) {
        this.outSynapses.add(s);
    }
}
