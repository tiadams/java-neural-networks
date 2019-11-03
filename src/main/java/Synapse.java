import java.util.Random;

public class Synapse implements Input{

    Neuron input;
    double weight;

    public Synapse(Neuron input) {
        this.input = input;
        Random r = new Random();
        this.weight = 4.0*r.nextDouble() - 2.0;
    }

    @Override
    public double getWeightedValue() {
        final double v = input.calculateOutput() * weight;
        return v;
    }
}
