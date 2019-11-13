package de.uni_bonn.cs.tnn.core;

public class RBFNeuron extends Neuron {

    private double[] centerVector;
    public RBFNeuron() {
        super(TransferFuncType.GAUSSIAN);
    }

    @Override
    public double calculateOutput() {
        return transferFunction.calculate(getDistance());
    }

    @Override
    public void backprop(double teacherY) {
        //TODO
    }

    private double getDistance(){
        double sum = 0.0;
        for(int i=0; i < centerVector.length; i++)
        {
            double diff = centerVector[i]-inputs.get(i).getWeightedValue();
            sum += diff*diff;
        }
        return Math.sqrt(sum);
    }

    public void setCenterVector(double[] centerVector) {
        if(centerVector.length == inputs.size()) {
            this.centerVector = centerVector;
        }
        else System.out.println("Center vector of dimension "+centerVector.length+" could not applied to RBF-neuron with input dimension of "+inputs.size());
    }
}
