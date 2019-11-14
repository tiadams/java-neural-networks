package de.uni_bonn.cs.tnn.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PatternLoader {

    public ArrayList<Pattern> loadPatterns(File file) {
        System.out.println("Loading Patterns from file " + file.getAbsolutePath() + "...");
        ArrayList<Pattern> patterns = new ArrayList<Pattern>();
        BufferedReader br = null;
        String headerRegex = "#.*P=\\d.*N=\\d.*M=\\d";
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            // header params
            int p = 0;
            int n = 0;
            int m = 0;
            boolean initialized = false;
            while ((line = br.readLine()) != null) {
                if (line.matches(headerRegex)) {
                    String temp = line.replaceFirst("#", "");
                    p = Integer.parseInt(temp.replaceAll("\\s*\\b(?!P=\\d)\\w+=\\d+", "").trim().split("=")[1]);
                    n = Integer.parseInt(temp.replaceAll("\\s*\\b(?!N=\\d)\\w+=\\d+", "").trim().split("=")[1]);
                    m = Integer.parseInt(temp.replaceAll("\\s*\\b(?!M=\\d)\\w+=\\d+", "").trim().split("=")[1]);
                    initialized = true;
                }
                if (!line.startsWith("#")) {
                    if (!initialized) {
                        System.out.println("No header found - returning null");
                        return null;
                    }
                    Pattern pattern = new Pattern();
                    String[] split = Arrays.asList(line.split(" ")).stream().filter(x -> !x.isEmpty()).toArray(String[]::new);
                    if(split.length != (n + m)){
                        System.out.println(line);
                    }
                    assert (split.length == (n + m));
                    List<Double> input = new ArrayList<>();
                    List<Double> output = new ArrayList<>();
                    for (int i = 0; i < split.length; i++) {
                        if (i < n) {
                            input.add(Double.parseDouble(split[i]));
                        } else {
                            output.add(Double.parseDouble(split[i]));
                        }
                    }
                    pattern.setInput(input.stream().mapToDouble(Double::doubleValue).toArray());
                    pattern.setOutput(output.stream().mapToDouble(Double::doubleValue).toArray());
                    patterns.add(pattern);
                }
            }
            System.out.println("Successfully parsed " + patterns.size() + " patterns.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patterns;
    }

    // TODO: This does belong in its own class, possibly extending List<Pattern>
    public List<Pattern> getShuffledPatternList(List<Pattern> patterns) {
        ArrayList<Pattern> shuffled = new ArrayList<Pattern>();
        Random rnd = new Random();
        while (patterns.size() != 0) {
            int pick = rnd.nextInt(patterns.size());
            shuffled.add(patterns.get(pick));
            patterns.remove(pick);
        }
        return shuffled;
    }

}
