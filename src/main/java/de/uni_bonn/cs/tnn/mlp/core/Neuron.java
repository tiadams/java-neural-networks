package de.uni_bonn.cs.tnn.mlp.core;

import java.util.ArrayList;
import java.util.List;

abstract class Neuron {

    List<Input> inputs;
    List<Synapse> outSynapses;
    TransferFunction transferFunction;
    double learningRate;
    double lastDelta;

    public Neuron(TransferFuncType function) {
        outSynapses = new ArrayList<>();
        inputs = new ArrayList<>();
        learningRate = 0.1;
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
        }
    }
    double getSum(){ //returns net_m
        return inputs.stream().mapToDouble(input -> (double) input.getWeightedValue()).sum();
    }
    double calculateOutput(){
        return transferFunction.calculate(getSum());
    }
    abstract void backprop(double teacherY);
    public double getLastDelta(){
        return lastDelta;
    }

    public void addInput(Input i) {
        this.inputs.add(i);
    }
    public void addOutSynapse(Synapse s) {
        this.outSynapses.add(s);
    }
}
