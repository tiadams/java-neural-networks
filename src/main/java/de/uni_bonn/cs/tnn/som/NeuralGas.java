package de.uni_bonn.cs.tnn.som;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class NeuralGas {

    List<SOMNeuron> neurons = new ArrayList<>();

    public List<SOMNeuron> getNeurons() {
        return neurons;
    }

    public NeuralGas(int neuronCount, int dimension){
        for (int i = 0; i < neuronCount; i++) {
            neurons.add(new SOMNeuron(dimension));
        }
    }

    public void applyStimulus(double[] stimulus, int t) {
        sortForWinner(stimulus);
        for (int i = 0; i < neurons.size(); i++) {
            SOMNeuron current = neurons.get(i);
            current.updateCenter(stimulus, i, t);
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
