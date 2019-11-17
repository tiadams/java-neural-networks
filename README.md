# Technical Neural Networks
## Build
This project is build with [maven](http://maven.apache.org)

build with `mvn clean compile assembly:single`

execute main class with `java -jar target/multilayer-perceptron-1.0-SNAPSHOT-jar-with-dependencies.jar` <TYPE_OF_DEMO (either MLP or RBF)>

## Multilayer Perceptron

        // define network
        int[] shape = {2, 10, 10, 1};
        TransferFuncType[] functions = {TransferFuncType.IDENTITY, TransferFuncType.TANH, TransferFuncType.TANH, TransferFuncType.TANH};
        MLPNetwork testMLP = new MLPNetwork(shape, functions);

        // load Patterns
        PatternLoader loader = new PatternLoader();
        List<Pattern> patterns = loader.loadPatterns(new File("src/main/resources/training.dat"));

        // shuffle
        List<Pattern> shuffled = loader.getShuffledPatternList(patterns_clean);

        // train
        testMLP.train(shuffled);

        // visualize
        ErrorPlotter plotter = new ErrorPlotter(testMLP.errorValues);
        plotter.showErrorPlot();
       

# Radial Basis Function Network

        int[] shape2 = {2, 2, 1};
        double[][] centers = {{0.0, 1.0}, {1.0, 0.0}};
        RBFNetwork testRBF = new RBFNetwork(shape2, centers);
        double[] testPoint = {0.0, 0.0};
        System.out.println("Testing XOR for "+Arrays.toString(testPoint)+": "+Arrays.toString(testRBF.calculateOutputs(testPoint)));
        double[] testPoint2 = {1.0, 0.0};
        System.out.println("Testing XOR for "+Arrays.toString(testPoint2)+": "+Arrays.toString(testRBF.calculateOutputs(testPoint2)));
