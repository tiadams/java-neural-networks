import java.util.List;

public class OutputNeuron extends Neuron{

    public OutputNeuron(TransferFuncType function) {
        super(function);
    }

    @Override
    void backprop(double teacherY) {
        double delta_m =  calcDelta(teacherY);
        for(Input synapse : inputs) {
            synapse.updateWeight(learningRate * delta_m * synapse.getUnweightedOutput());
        }
    }
    private double calcDelta(double teacherY){
        double delta = (teacherY - calculateOutput()) * transferFunction.differentiate(getSum());
        lastDelta = delta;
        return delta;
    }
}
