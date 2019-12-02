package de.uni_bonn.cs.tnn.io;

public class Stimulus {

    double[] positionVector;

    public Stimulus(double[] position){
        this.positionVector = position;
    }

    public double[] getPositionVector() {
        return positionVector;
    }
}
