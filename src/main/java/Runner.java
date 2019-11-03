import java.util.Arrays;
import java.util.Random;

public class Runner {
    public static void main(String[] args){
        int[] shape = {20, 30, 30, 20};
        TransferFuncType[] functions = {TransferFuncType.IDENTITY, TransferFuncType.TANH, TransferFuncType.TANH, TransferFuncType.TANH};
        Network testMLP = new Network(shape, functions);

        double[] x = new double[20];
        Random r = new Random();
        for(int i = 0; i < x.length; i++) x[i]=r.nextDouble();
        System.out.println("In: "+Arrays.toString(x));
        System.out.println("Out: "+Arrays.toString(testMLP.calculateOutputs(x)));
    }
}
