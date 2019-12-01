package de.uni_bonn.cs.tnn.som;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NeuralGas {

    List<SOMNeuron> neurons = new ArrayList<>();

    public NeuralGas(int neuronCount){
        Random r = new Random();
        for (int i = 0; i < neuronCount; i++) {
            neurons.add(new SOMNeuron(new double[]{r.nextDouble(), r.nextDouble()})); //random center init in 0-1 square
        }
    }

    public void applyStimulus(double[] stimulus) {
        sortForWinner(stimulus);
        for (int i = 0; i < neurons.size(); i++) {
            SOMNeuron current = neurons.get(i);
            current.updateCenter(stimulus, i);
        }
    }

    private void sortForWinner(double[] stimulus) {
        List<SOMNeuron> sorted = neurons.stream()
                .sorted(Comparator.comparingDouble(neuron -> neuron.dist(stimulus)))
                .collect(Collectors.toList());
        this.neurons = sorted;
    }

    public double getWinnerDist(double[] stimulus) {
        sortForWinner(stimulus);
        return neurons.get(0).dist(stimulus);
    }

}
