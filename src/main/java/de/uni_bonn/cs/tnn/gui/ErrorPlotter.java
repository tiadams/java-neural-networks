package de.uni_bonn.cs.tnn.gui;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.List;

public class ErrorPlotter {

    private double[] errorValues;

    public ErrorPlotter(double[] errorValues) {
        this.errorValues = errorValues;
    }

    public void showErrorPlot(){
        double[] xData = new double[errorValues.length];
        double[] yData = this.errorValues;
        for (int i = 0; i<errorValues.length; i++){
            xData[i] = i;
        }
        XYChart chart = QuickChart.getChart("Error plot", "Patterns", "E(x)", "y(x)", xData, yData);
        new SwingWrapper(chart).displayChart();
    }
}
