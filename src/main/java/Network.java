import java.util.ArrayList;
import java.util.List;

/**
 * Creates an MLP with the specified amount of nodes per layer and with the specified transfer functions on each layer
 * nodeCounts and functions must be of length 2-4
 */
public class Network {
    enum LayerType{
        INPUT, HIDDEN, OUTPUT;
    }

    int layerCount;
    X[] inputVector;
    Neuron[] outputLayer;

    public Network(int[] nodeCounts, TransferFuncType[] functions) {
        layerCount = Math.min(nodeCounts.length, 4);
        inputVector = new X[nodeCounts[0]];
        for(int i = 0; i < inputVector.length; i++)inputVector[i] = new X();
        Neuron[] inputLayer = createInputLayer(nodeCounts[0]);
        Neuron[] currPreviousLayer = inputLayer;
        for (int i = 1; i < layerCount - 1; i++){
            Neuron[] hiddenLayer = createFollowingLayer(LayerType.HIDDEN, nodeCounts[i], functions[i], currPreviousLayer);
            currPreviousLayer = hiddenLayer;
        }
        outputLayer = createFollowingLayer(LayerType.OUTPUT, nodeCounts[layerCount - 1], functions[layerCount - 1], currPreviousLayer);
    }

    public double[] calculateOutputs(double[] inputValues){
        for(int i = 0; i < inputVector.length; i++){
            inputVector[i].setInput(inputValues[i]);
        }

        double[] out = new double[outputLayer.length];
        for(int i = 0; i < out.length; i++){
            out[i] = outputLayer[i].calculateOutput();
        }
        return out;
    }

    /**
     * Creates the input layer.
     * @return Neuron array with InpoutNeuron objects
     */
    private Neuron[] createInputLayer(int nodeCount){
        Neuron[] newrons = new Neuron[nodeCount];
        for(int i = 0; i < nodeCount; i++){
            List<Input> inputs = new ArrayList<>();
            inputs.add(inputVector[i]);
            newrons[i] = new InputNeuron(inputs);
        }
        return newrons;
    }

    /**
     * Creates a hidden or output layer and all Synapses coming in from the previous layer.
     * @param type Either HIDDEN or OUTPUT
     * @return Neuron array with either HiddenNeuron or OutputNeuron objects
     */
    private Neuron[] createFollowingLayer(LayerType type, int nodeCount, TransferFuncType function, Neuron[] previousLayer){
        Neuron[] newrons = new Neuron[nodeCount];
        for(int i = 0; i < nodeCount; i++){
            List<Input> inputs = new ArrayList<>();
            for (Neuron neuron : previousLayer) {
                inputs.add(new Synapse(neuron));
            }
            newrons[i] = type == LayerType.HIDDEN ? new HiddenNeuron(function, inputs) : new OutputNeuron(function, inputs);
        }
        return newrons;
    }
}
