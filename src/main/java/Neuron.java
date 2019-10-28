import java.util.List;

abstract class Neuron {

    List<Input> inputs;
    TransferFunction transferFunction;

    double calculateOutput(){
        double sum = inputs.stream().mapToDouble(input -> (double) input.getWeightedValue()).sum();
        return transferFunction.calculate(sum);
    }
}
