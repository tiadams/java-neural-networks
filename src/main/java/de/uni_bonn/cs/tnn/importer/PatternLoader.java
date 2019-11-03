package de.uni_bonn.cs.tnn.importer;

import java.io.*;
import java.util.ArrayList;

public class PatternLoader {

    // TODO: adapt to multiple output values, read from header
    public ArrayList<Pattern> loadPatterns(File file){
        ArrayList<Pattern> patterns = new ArrayList<Pattern>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            Pattern pattern = new Pattern();
            while ((line = br.readLine()) != null){
                if(!line.startsWith("#")){
                    double[] output = new double[1];
                    String[] samples = line.split(" ");
                    output[0] = Double.parseDouble(samples[samples.length - 1]);
                    double[] input = new double[samples.length - 2];
                    for(int i = 0; i < samples.length - 2; i++){
                        input[i] = Double.parseDouble(samples[i]);
                    }
                    pattern.setInput(input);
                    pattern.setOutput(output);
                    patterns.add(pattern);
                }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patterns;
    }

}
