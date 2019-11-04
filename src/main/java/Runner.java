import de.uni_bonn.cs.tnn.importer.Pattern;
import de.uni_bonn.cs.tnn.importer.PatternLoader;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Runner {
    public static void main(String[] args){
        int[] shape = {2, 2, 2, 1};
        TransferFuncType[] functions = {TransferFuncType.IDENTITY, TransferFuncType.TANH, TransferFuncType.TANH, TransferFuncType.TANH};
        Network testMLP = new Network(shape, functions);
        PatternLoader loader = new PatternLoader();
        List<Pattern> patterns = loader.loadPatterns(new File("src/main/resources/training2.dat"));
        testMLP.train(patterns);
    }
}
