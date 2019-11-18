import de.uni_bonn.cs.tnn.mlp.MLPNetwork;
import de.uni_bonn.cs.tnn.core.TransferFuncType;
import de.uni_bonn.cs.tnn.gui.ErrorPlotter;
import de.uni_bonn.cs.tnn.io.Pattern;
import de.uni_bonn.cs.tnn.io.PatternLoader;
import de.uni_bonn.cs.tnn.rbf.RBFNetwork;
import de.uni_bonn.cs.tnn.util.PatternGenerator;
import de.uni_bonn.cs.tnn.util.QuadraticFunction;
import de.uni_bonn.cs.tnn.util.SinusFunction;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Runner {

    public static void main(String[] args){

        final String ERROR_MSG = "First argument should be either MLP or RBF!";

        if (args.length != 1){
            System.out.println(ERROR_MSG);
        }
        else{
            switch(args[0]) {
                case "MLP":
                    System.out.println("Running MLP demo");
                    runMLP();
                case "RBF":
                    System.out.println("Running RBF demo");
                    runRBF();
            }
        }
    }

    public static void runMLP(){
        // define shape
        int[] shape = {1, 10, 10, 1};
        TransferFuncType[] functions = {TransferFuncType.IDENTITY, TransferFuncType.TANH, TransferFuncType.TANH, TransferFuncType.IDENTITY};
        MLPNetwork testMLP = new MLPNetwork(shape, functions);

        // load Patterns
        PatternLoader loader = new PatternLoader();

        // shuffle
        List<Pattern> patterns = PatternGenerator.sample(new QuadraticFunction(),10000, -100, 100);
        List<Pattern> shuffled = loader.getShuffledPatternList(patterns);

        // train
        testMLP.train(shuffled);

        // visualize
        ErrorPlotter plotter = new ErrorPlotter(testMLP.errorValues);
        plotter.showErrorPlot();
    }

    public static void runRBF(){
        int[] shape2 = {2, 2, 1};
        double[][] centers = {{0.0, 1.0}, {1.0, 0.0}};
        RBFNetwork testRBF = new RBFNetwork(shape2, centers);
        double[] testPoint = {0.0, 0.0};
        System.out.println("Testing XOR for "+Arrays.toString(testPoint)+": "+Arrays.toString(testRBF.calculateOutputs(testPoint)));
        double[] testPoint2 = {1.0, 0.0};
        System.out.println("Testing XOR for "+Arrays.toString(testPoint2)+": "+Arrays.toString(testRBF.calculateOutputs(testPoint2)));
    }
}
