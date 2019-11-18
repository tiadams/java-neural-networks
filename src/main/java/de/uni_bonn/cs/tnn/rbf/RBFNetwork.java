package de.uni_bonn.cs.tnn.rbf;

import de.uni_bonn.cs.tnn.core.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class RBFNetwork {

    private static final Logger LOGGER = Logger.getLogger(RBFNetwork.class.getName());

    private enum LayerType {
        INPUT, RBF, OUTPUT;
    }

    private Neuron[][] layers;
    private X[] inputVector;
    private double[][] centerVectors;

    /**
     * Creates an RBF-network with the specified amount of nodes per layer
     * nodeCounts must be of length 3
     */
    public RBFNetwork(int[] nodeCounts, double[][] centers) {
        LOGGER.info("Initializing " + Arrays.toString(nodeCounts) + " RBF network");
        layers = new Neuron[3][];
        inputVector = new X[nodeCounts[0]];
        centerVectors = centers;
        for (int i = 0; i < inputVector.length; i++) inputVector[i] = new X();
        //initialise layers
        layers[0] = createInputLayer(nodeCounts[0]);
        layers[1] = createFollowingLayer(LayerType.RBF, nodeCounts[1], layers[0]);
        layers[2] = createFollowingLayer(LayerType.OUTPUT, nodeCounts[2], layers[1]);
    }

    public double[] calculateOutputs(double[] inputValues) {
        return calculateLayerOutputs(inputValues, 2);
    }

    private double[] calculateLayerOutputs(double[] inputValues, int layer) {
        for (int i = 0; i < inputVector.length; i++) {
            inputVector[i].setInput(inputValues[i]);
        }

        double[] out = new double[layers[layer].length];
        for (int i = 0; i < out.length; i++) {
            out[i] = layers[layer][i].calculateOutput();
        }
        return out;
    }

    /**
     * Creates the input layer.
     *
     * @return de.uni_bonn.cs.tnn.mlp.core.Neuron array with InpoutNeuron objects
     */
    private Neuron[] createInputLayer(int nodeCount) {
        Neuron[] newrons = new Neuron[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            Neuron newron = new InputNeuron();

            List<Input> inputs = new ArrayList<>();
            inputs.add(inputVector[i]);
            for (Input in : inputs) newron.addInput(in);
            newrons[i] = newron;
        }
        return newrons;
    }

    /**
     * Creates a hidden or output layer and all Synapses coming in from the previous layer.
     *
     * @param type Either RBF or OUTPUT
     * @return de.uni_bonn.cs.tnn.mlp.core.Neuron array with either de.uni_bonn.cs.tnn.mlp.core.RBFNeuron or de.uni_bonn.cs.tnn.mlp.core.OutputNeuron objects
     */
    private Neuron[] createFollowingLayer(LayerType type, int nodeCount, Neuron[] previousLayer) {
        Neuron[] newrons = new Neuron[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            Neuron newron;
            if (type == LayerType.RBF) {
                RBFNeuron rbfNewron = new RBFNeuron();
                List<Synapse> inSynapses = new ArrayList<>();
                for (Neuron precedingNeuron : previousLayer) {
                    inSynapses.add(new Synapse(precedingNeuron, 1.0));
                }
                for (Synapse syn : inSynapses) {
                    syn.setOutputNeuron(rbfNewron);
                    rbfNewron.addInput(syn);
                }
                rbfNewron.setCenterVector(centerVectors[i]);
                newron = rbfNewron;
            } else {
                newron = new OutputNeuron(TransferFuncType.IDENTITY);
                List<Synapse> inSynapses = new ArrayList<>();
                for (Neuron precedingNeuron : previousLayer) {
                    inSynapses.add(new Synapse(precedingNeuron, 1.0));
                }
                for (Synapse syn : inSynapses) {
                    syn.setOutputNeuron(newron);
                    newron.addInput(syn);
                }
            }
            newrons[i] = newron;
        }
        return newrons;
    }
}
