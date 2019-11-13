package de.uni_bonn.cs.tnn.io;

import org.junit.Test;

import java.io.File;
import java.util.List;

public class PatternLoaderTest {

    @Test
    public void loadPattern1(){
        PatternLoader loader = new PatternLoader();
        File file = new File("src/test/resources/training.dat");
        List<Pattern> patterns = loader.loadPatterns(file);
        assert(patterns.size() == 32);
    }

    @Test
    public void loadPattern2(){
        PatternLoader loader = new PatternLoader();
        File file = new File("src/test/resources/training2.dat");
        List<Pattern> patterns = loader.loadPatterns(file);
        assert(patterns.size() == 350);
    }
}