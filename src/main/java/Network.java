import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Network {

    private static final Logger LOGGER = Logger.getLogger(Network.class.getName());

    private enum LayerType{
        INPUT, HIDDEN, OUTPUT;
    }

    private int layerCount;
    private X[] inputVector;
    private Neuron[][] layers;

    /**
     * Creates an MLP with the specified amount of nodes per layer and with the specified transfer functions on each layer
     * nodeCounts must be of length 2-4
     * functions must be of length 2-4. The first value gets ignored, since the input layer always uses IDENTITY
     */
    public Network(int[] nodeCounts, TransferFuncType[] functions) {
        LOGGER.info("Initializing " + nodeCounts.length + " layer MLP network");
        layerCount = Math.min(nodeCounts.length, 4);
        layers = new Neuron[layerCount][];
        inputVector = new X[nodeCounts[0]];
        for(int i = 0; i < inputVector.length; i++)inputVector[i] = new X();
        layers[0] = createInputLayer(nodeCounts[0]); //input layer
        for (int i = 1; i < layerCount - 1; i++){
            layers[i] = createFollowingLayer(LayerType.HIDDEN, nodeCounts[i], functions[i], layers[i-1]);
        }
        layers[layerCount-1] = createFollowingLayer(LayerType.OUTPUT, nodeCounts[layerCount-1], functions[layerCount-1], layers[layerCount-2]);
    }

    public double[] calculateOutputs(double[] inputValues){
        return calculateLayerOutputs(inputValues, layerCount-1);
    }
    public double[] calculateLayerOutputs(double[] inputValues, int layer){
        for(int i = 0; i < inputVector.length; i++){
            inputVector[i].setInput(inputValues[i]);
        }

        double[] out = new double[layers[layer].length];
        for(int i = 0; i < out.length; i++){
            out[i] = layers[layer][i].calculateOutput();
        }
        return out;
    }

    private double getError(double[] trainX, double[] teachY){
        double squaredError = 0.0;
        double[] predictY = calculateOutputs(trainX);
        for(int i = 0; i < teachY.length; i++){
            squaredError+=Math.pow(teachY[i]-predictY[i], 2);
        }
        return squaredError;
    }

    private void backprop(double[] trainX, double[] teachY){
        //for the output layer
        double[] predictY = calculateOutputs(trainX);
        for(int n = 0; n < layers[layerCount-1].length; n++){
            layers[layerCount-1][n].backprop(teachY[n]);
        }
        //for the hidden layers
        for(int l = layerCount-2; l > 0; l--){
            for(int n = 0; n < layers[l].length; n++){

            }
        }
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
