public class InputNeuron extends Neuron {

    public InputNeuron(Input input) {
        this.transferFunction = new IdentityTransferFunction();
        this.inputs.add(input);
    }

}
