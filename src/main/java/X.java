public class X implements Input{

    double value;

    public void setInput(double input){
        value = input;
    }
    @Override
    public double getWeightedValue() {
        return value;
    }
}
