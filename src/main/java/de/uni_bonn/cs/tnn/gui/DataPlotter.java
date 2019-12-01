package de.uni_bonn.cs.tnn.gui;

import de.uni_bonn.cs.tnn.io.Stimulus;
import de.uni_bonn.cs.tnn.som.MultiNeuralGas;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;
import java.util.List;

public class DataPlotter {

    XYChart chart = new XYChartBuilder().width(600).height(500).title("Data").xAxisTitle("X").yAxisTitle("Y").build();

    public void addData(List<Stimulus> data, String label) {
        assert (!data.isEmpty());
        assert (data.get(0).getPositionVector().length == 2);
        double[] xData = new double[data.size()];
        double[] yData = new double[data.size()];
        for (int i = 0; i < data.size(); i++) {
            xData[i] = data.get(i).getPositionVector()[0];
            yData[i] = data.get(i).getPositionVector()[1];
        }
        chart.addSeries(label, xData, yData);
    }

    public void addData(MultiNeuralGas gas, String label) {
        // TODO: assert that data is 2-dimensional
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();
        gas.getGases().forEach(neuralGas -> neuralGas.getNeurons().forEach(neuron -> {
            xData.add(neuron.getCentralVector()[0]);
            yData.add(neuron.getCentralVector()[1]);
        }));
        chart.addSeries(label, xData, yData);
    }

    public void plot() {
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart.getStyler().setMarkerSize(16);
        new SwingWrapper(chart).displayChart();
    }

}
