import java.util.List;

abstract class Neuron {

    List<Input> inputs;
    TransferFunction transferFunction;

    public Neuron(TransferFuncType function, List<Input> inputs) {
        this.inputs = inputs;
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
    double calculateOutput(){
        double sum = inputs.stream().mapToDouble(input -> (double) input.getWeightedValue()).sum();
        return transferFunction.calculate(sum);
    }
}
