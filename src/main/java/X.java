public class X implements Input{

    double value;

    public void setInput(double input){
        value = input;
    }
    @Override
    public double getWeightedValue() {
        return value;
    }

    @Override
    public double getUnweightedOutput() {
        return value;
    }

    @Override
    public void updateWeight(double newWeight) {
        //ignore
    }
}
