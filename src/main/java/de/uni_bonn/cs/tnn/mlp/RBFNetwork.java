package de.uni_bonn.cs.tnn.mlp;

import de.uni_bonn.cs.tnn.core.Neuron;
import de.uni_bonn.cs.tnn.core.X;

import java.util.Arrays;
import java.util.logging.Logger;

public class RBFNetwork {

    private static final Logger LOGGER = Logger.getLogger(MLPNetwork.class.getName());
    private Neuron[][] layers;
    private X[] inputVector;

    /**
     * Creates an RBF-network with the specified amount of nodes per layer
     * nodeCounts must be of length 3
     */
    public RBFNetwork(int[] nodeCounts) {
        LOGGER.info("Initializing " + Arrays.toString(nodeCounts) + " RBF network");
        layers = new Neuron[3][];
        inputVector = new X[nodeCounts[0]];
        //TODO initialise layers
    }
}
