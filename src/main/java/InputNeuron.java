import java.util.List;

public class InputNeuron extends Neuron {

    public InputNeuron(List<Input> inputs) {
        super(TransferFuncType.IDENTITY, inputs);
    }

    @Override
    void backprop(double learn) {
        //ignore
    }

    @Override
    double getDelta(double learn) {
        //ignore
        return 0;
    }
}
