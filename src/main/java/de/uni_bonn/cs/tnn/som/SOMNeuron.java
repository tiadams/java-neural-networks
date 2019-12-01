package de.uni_bonn.cs.tnn.som;

import de.uni_bonn.cs.tnn.core.GaussianTransferFunction;

import java.util.Random;

public class SOMNeuron {

    double[] centralVector;
    double maxLearningRate = 1.0; // η_0
    double minLearningRate = 0.2; // η_end
    double learningRateDecay = 0.975;

    public SOMNeuron(double[] center){
        centralVector = center;
    }

    public SOMNeuron(int dimension){ //when only the dimension is given, initialise uniformly within the unit cube
        centralVector = new double[dimension];
        Random r = new Random();
        for (int i = 0; i < dimension; i++) {
            centralVector[i] = -1.0+r.nextDouble()*2;
        }
    }

    public double dist(double[] stimulus) {
        assert (centralVector.length == stimulus.length);
        double sum = 0;
        for (int i = 0; i < centralVector.length; i++) {
            sum += Math.pow((centralVector[i] - stimulus[i]), 2);
        }
        return Math.sqrt(sum);
    }

    public void updateCenter(double[] stimulus, int rank, int t) {
        assert (centralVector.length == stimulus.length);
        double[] update = scaleUpdate(getTranslation(stimulus), rank, t);
        for (int i = 0; i < centralVector.length; i++) {
            centralVector[i] += update[i];
        }
    }

    private double[] scaleUpdate(double[] translation, int rank, int t) { //applies neighborhood function and learning rate
        double[] scaled = new double[translation.length];
        double currentLearningRate = Math.max(minLearningRate, maxLearningRate*Math.pow(learningRateDecay, t));
        for (int i = 0; i < translation.length; i++) {
            scaled[i] = translation[i] * neighbourhoodFunction(rank);
            scaled[i] *= currentLearningRate;
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

    GaussianTransferFunction gaussian = new GaussianTransferFunction(1.5); //adjustable but fixed size gaussian
    private double neighbourhoodFunction(int index) {
        return gaussian.calculate(index);
//        return 1 / (double)(index+1); //hyperbolic
    }

}
