import java.util.List;

abstract class Neuron {

    List<Input> inputs;
    TransferFunction transferFunction;
    double learningRate;

    public Neuron(TransferFuncType function, List<Input> inputs) {
        this.inputs = inputs;
        learningRate = 0.1;
        switch(function){
            case TANH:
                this.transferFunction = new TanhTransferFunction();
                break;
            case LOGISTIC:
                this.transferFunction = new LogisticTransferFunction();
                break;
            case IDENTITY:
                this.transferFunction = new IdentityTransferFunction();
                break;
        }
    }
    private double getSum(){ //returns net_m
        return inputs.stream().mapToDouble(input -> (double) input.getWeightedValue()).sum();
    }
    double calculateOutput(){
        return transferFunction.calculate(getSum());
    }
    void updateSynapseWeights(double teacherY){
        double w_m = learningRate * (teacherY - calculateOutput()) * transferFunction.differentiate(getSum());// w_hm / out_h
        for(Input synapse : inputs) {
             synapse.updateWeight(w_m * synapse.getUnweightedOutput());
        }
    }
}
