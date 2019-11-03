import java.util.List;

public class HiddenNeuron extends Neuron{

    public HiddenNeuron(TransferFuncType function, List<Input> inputs) {
        super(function, inputs);
    }

    @Override
    void backprop(double learn) {
        double sum = 0.0;
        double w_m = learningRate * (sum) * transferFunction.differentiate(getSum());// w_hm / out_h
        for(Input synapse : inputs) {
            synapse.updateWeight(w_m * synapse.getUnweightedOutput());
        }
    }

    @Override
    double getDelta(double learn) {
        return 0;//TODO
    }
}
