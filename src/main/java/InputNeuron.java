import java.util.List;

public class InputNeuron extends Neuron {

    public InputNeuron(List<Input> inputs) {
        super(TransferFuncType.IDENTITY, inputs);
    }

}
