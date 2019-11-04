import de.uni_bonn.cs.tnn.mlp.core.Network;
import de.uni_bonn.cs.tnn.mlp.core.TransferFuncType;
import de.uni_bonn.cs.tnn.mlp.io.Pattern;
import de.uni_bonn.cs.tnn.mlp.io.PatternLoader;

import java.io.File;
import java.util.List;

public class Runner {
    public static void main(String[] args){
        int[] shape = {2, 2, 2, 1};
        TransferFuncType[] functions = {TransferFuncType.IDENTITY, TransferFuncType.TANH, TransferFuncType.TANH, TransferFuncType.TANH};
        Network testMLP = new Network(shape, functions);
        PatternLoader loader = new PatternLoader();
        List<Pattern> patterns = loader.loadPatterns(new File("src/main/resources/training2.dat"));
        List<Pattern> patterns_clean = patterns.subList(0,150);
        testMLP.train(patterns_clean);
    }
}
