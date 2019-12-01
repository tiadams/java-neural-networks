package de.uni_bonn.cs.tnn.som;

public class SOMNeuron {

    double[] centralVector;

    public SOMNeuron(double[] center){
        centralVector = center;
    }

    public double dist(double[] stimulus) {
        assert (centralVector.length == stimulus.length);
        double sum = 0;
        for (int i = 0; i < centralVector.length; i++) {
            sum += Math.pow((centralVector[i] - stimulus[i]), 2);
        }
        return Math.sqrt(sum);
    }

    public void updateCenter(double[] stimulus, int rank) {
        assert (centralVector.length == stimulus.length);
        double[] update = scaleUpdate(getTranslation(stimulus), rank);
        for (int i = 0; i < centralVector.length; i++) {
            centralVector[i] += update[i];
        }
    }

    private double[] scaleUpdate(double[] dist, int rank) {
        double[] scaled = new double[dist.length];
        for (int i = 0; i < dist.length; i++) {
            scaled[i] = dist[i] * neighbourhoodFunction(rank);
        }
        return scaled;
    }

    private double[] getTranslation(double[] stimulus){
        assert (centralVector.length == stimulus.length);
        double[] translation = new double[stimulus.length];
        for (int i = 0; i < centralVector.length; i++) {
            translation[i] = stimulus[i] - centralVector[i];
        }
        return translation;
    }

    private double neighbourhoodFunction(int index) {
        return 1 / (double)(index+1);
    }

}
