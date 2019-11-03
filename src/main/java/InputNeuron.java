import java.util.List;

public class InputNeuron extends Neuron {

    public InputNeuron() {
        super(TransferFuncType.IDENTITY);
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
