import java.util.List;

public class OutputNeuron extends Neuron{

    public OutputNeuron(TransferFuncType function, List<Input> inputs) {
        super(function, inputs);
    }

    @Override
    void backprop(double learn) {
        double teacherY = learn;
        double delta_m =  getDelta(teacherY);
        for(Input synapse : inputs) {
            synapse.updateWeight(learningRate * delta_m * synapse.getUnweightedOutput());
        }
    }

    @Override
    double getDelta(double learn) {
        double teacherY = learn;
        return (teacherY - calculateOutput()) * transferFunction.differentiate(getSum());
    }
}
