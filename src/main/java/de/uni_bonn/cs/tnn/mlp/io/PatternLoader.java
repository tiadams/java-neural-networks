package de.uni_bonn.cs.tnn.mlp.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PatternLoader {

    // TODO: adapt to multiple output values, read from header
    public ArrayList<Pattern> loadPatterns(File file){
        System.out.println("Loading Patterns from file " + file.getAbsolutePath() + "...");
        ArrayList<Pattern> patterns = new ArrayList<Pattern>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                if(!line.startsWith("#")){
                    Pattern pattern = new Pattern();
                    String[] input_output;
                    if (line.contains("\t")){
                        input_output = line.split("\t");
                    } else{
                        input_output = line.split("  ");
                    }
                    String[] input = input_output[0].trim().split(" ");
                    String[] output = input_output[1].trim().split(" ");
                    pattern.setInput(Arrays.stream(input)
                            .mapToDouble(Double::parseDouble)
                            .toArray());
                    pattern.setOutput(Arrays.stream(output)
                            .mapToDouble(Double::parseDouble)
                            .toArray());
                    patterns.add(pattern);
                }
        }
            System.out.println("Successfully parsed " + patterns.size() + " patterns.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patterns;
    }

}
