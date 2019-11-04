import java.util.List;

public class InputNeuron extends Neuron {

    public InputNeuron() {
        super(TransferFuncType.IDENTITY);
    }

    @Override
    void backprop(double teacherY) {
        //ignore
    }

    double calcDelta(double teacherY) {
        //ignore
        return 0;
    }
}
