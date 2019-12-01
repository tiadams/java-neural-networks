package de.uni_bonn.cs.tnn.core;

public class GaussianTransferFunction implements TransferFunction {

    private double size;

    public GaussianTransferFunction(){
        this.size = 0.2; //default size
    }
    public GaussianTransferFunction(double size){
        this.size = size;
    }

    @Override
    public double calculate(double input) {
        return Math.exp(-(input*input)/(2*size*size));
    }

    @Override
    public double differentiate(double input) {
        return 0; //TODO
    }

    public void setSize(double size) {
        this.size = size;
    }
}
