import java.util.Arrays;
import java.util.Random;

public class Runner {
    public static void main(String[] args){
        int[] shape = {2, 3, 3, 2};
        TransferFuncType[] functions = {TransferFuncType.IDENTITY, TransferFuncType.TANH, TransferFuncType.TANH, TransferFuncType.TANH};
        Network testMLP = new Network(shape, functions);

//        double[] x = new double[2];
//        Random r = new Random();
//        for(int i = 0; i < x.length; i++) x[i]=r.nextDouble();
        double[] x = {1.0, 1.0};
        double[] teacherY = {0.5, 0.5};
        System.out.println("X Input: "+Arrays.toString(x));
        System.out.println("Initial Output: "+Arrays.toString(testMLP.calculateOutputs(x)));
        System.out.println("backprop... Teacher="+Arrays.toString(teacherY));
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        testMLP.backprop(x, teacherY);
        System.out.println("Trained Out: "+Arrays.toString(testMLP.calculateOutputs(x)));
    }
}
