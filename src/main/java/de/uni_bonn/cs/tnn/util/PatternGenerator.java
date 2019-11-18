package de.uni_bonn.cs.tnn.util;

import de.uni_bonn.cs.tnn.io.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PatternGenerator {

    public static List<Pattern> sample(Function function, int numSamples, double sampleStart, double sampleEnd) {
        List<Pattern> samples = new ArrayList<>();
        for (int i = 0; i < numSamples; i++) {
            Pattern pattern = new Pattern();
            double x = (Math.random() * ((sampleEnd - sampleStart) + 1)) + sampleStart;
            double y = function.f(x);
            pattern.setInput(new double[]{x});
            pattern.setOutput(new double[]{y});
            samples.add(pattern);
        }
        return samples;
    }
}
