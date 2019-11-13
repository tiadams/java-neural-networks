import de.uni_bonn.cs.tnn.mlp.MLPNetwork;
import de.uni_bonn.cs.tnn.core.TransferFuncType;
import de.uni_bonn.cs.tnn.gui.ErrorPlotter;
import de.uni_bonn.cs.tnn.io.Pattern;
import de.uni_bonn.cs.tnn.io.PatternLoader;

import java.io.File;
import java.util.List;

public class Runner {

    public static void main(String[] args){

        // define shape
        int[] shape = {2, 10, 10, 1};
        TransferFuncType[] functions = {TransferFuncType.IDENTITY, TransferFuncType.TANH, TransferFuncType.TANH, TransferFuncType.TANH};
        MLPNetwork testMLP = new MLPNetwork(shape, functions);

        // load Patterns
        PatternLoader loader = new PatternLoader();
        List<Pattern> patterns = loader.loadPatterns(new File("src/main/resources/training2.dat"));

        // shuffle
        List<Pattern> patterns_clean = patterns.subList(0,150);
        patterns_clean.addAll(patterns_clean);
        patterns_clean.addAll(patterns_clean);
        patterns_clean.addAll(patterns_clean);
        patterns_clean.addAll(patterns_clean);
        patterns_clean.addAll(patterns_clean);
        List<Pattern> shuffled = loader.getShuffledPatternList(patterns_clean);

        // train
        testMLP.train(shuffled);

        // visualize
        ErrorPlotter plotter = new ErrorPlotter(testMLP.errorValues);
        plotter.showErrorPlot();

    }
}
