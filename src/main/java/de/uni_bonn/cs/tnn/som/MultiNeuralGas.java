package de.uni_bonn.cs.tnn.som;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MultiNeuralGas {

    List<NeuralGas> gases = new ArrayList<>();
    int t = 0; //time passed

    public MultiNeuralGas(int[] nodeCounts, int dimension){
        for (int i = 0; i < nodeCounts.length; i++) {
            gases.add(new NeuralGas(nodeCounts[i], dimension));
        }
    }

    public void applyStimulus(double[] stimulus) {
        getWinningGas(stimulus).applyStimulus(stimulus, t);
        t++;
    }

    private NeuralGas getWinningGas(double[] stimulus) {
        NeuralGas winner = gases.stream().sorted(Comparator.comparingDouble(gas -> gas.getWinnerDist(stimulus)))
                .collect(Collectors.toList()).get(0);
        return winner;
    }


}
