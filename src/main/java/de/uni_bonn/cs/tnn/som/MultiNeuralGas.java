package de.uni_bonn.cs.tnn.som;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MultiNeuralGas {

    List<NeuralGas> gases = new ArrayList<>();

    public MultiNeuralGas(int[] nodeCounts){
        for (int i = 0; i < nodeCounts.length; i++) {
            gases.add(new NeuralGas(nodeCounts[i]));
        }
    }
    public void applyStimulus(double[] stimulus) {
        getWinningGas(stimulus).applyStimulus(stimulus);
    }

    private NeuralGas getWinningGas(double[] stimulus) {
        NeuralGas winner = gases.stream().sorted(Comparator.comparingDouble(gas -> gas.getWinnerDist(stimulus)))
                .collect(Collectors.toList()).get(0);
        return winner;
    }


}
