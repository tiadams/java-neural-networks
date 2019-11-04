import java.util.Random;

public class Synapse implements Input{

    Neuron input;
    Neuron output;
    double weight;

    public Synapse(Neuron input) {
        this.input = input;
        input.addOutSynapse(this);
        Random r = new Random();
        this.weight = 4.0*r.nextDouble() - 2.0;
    }

    @Override
    public double getWeightedValue() {
        return input.calculateOutput() * weight;
    }

    public double getUnweightedOutput() {
        return input.calculateOutput();
    }
    public void updateWeight(double weightDelta){
        weight += weightDelta;
    }
    public double getWeight(){
        return weight;
    }
    public void setOutputNeuron(Neuron n){
        output = n;
    }
    public double getOutputNeuronDelta(){
        return output.getLastDelta();
    }
}
