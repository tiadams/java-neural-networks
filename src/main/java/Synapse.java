public class Synapse implements Input{

    Neuron input;
    double weight;

    @Override
    public double getWeightedValue() {
        final double v = input.calculateOutput() * weight;
        return v;
    }
}
