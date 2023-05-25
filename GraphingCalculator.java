import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.chart.plot.XYPlot;

public class GraphingCalculator {
    public static void main(String[] args) {
        // Get user input for the function
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the function (in terms of x): ");
        String function = scanner.nextLine();

        // Generate data for the function
        DefaultXYDataset dataset = new DefaultXYDataset();
        double[][] data = new double[2][101];
        double maxY = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        for (int i = 0; i < 101; i++) {
            double x = i - 50;
            double y = evaluate(function, x);
            data[0][i] = x;
            data[1][i] = y;
            maxY = Math.max(maxY, y);
            minY = Math.min(minY, y);
        }
        dataset.addSeries("Function", data);

        // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "", // Title
                "", // X-Axis label
                "", // Y-Axis label
                dataset,
                PlotOrientation.VERTICAL,
                false, // Include legend
                false, // Include tooltips
                false // Include URLs
        );
        chart.setBackgroundPaint(Color.WHITE);

// Customize the plot
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(240, 240, 240)); // Background color of the plot
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY); // Color of the gridlines on the x-axis
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); // Color of the gridlines on the y-axis
        plot.setDomainZeroBaselinePaint(Color.BLACK); // Color of the x-axis line
        plot.setRangeZeroBaselinePaint(Color.BLACK); // Color of the y-axis line
        plot.getDomainAxis().setAxisLineStroke(new BasicStroke(2.0f)); // Thickness of the x-axis line
        plot.getRangeAxis().setAxisLineStroke(new BasicStroke(2.0f)); // Thickness of the y-axis line
        plot.getDomainAxis().setTickMarkPaint(Color.BLACK); // Color of the tick marks on the x-axis
        plot.getRangeAxis().setTickMarkPaint(Color.BLACK); // Color of the tick marks on the y-axis
        plot.getDomainAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 14)); // Font of the x-axis tick labels
        plot.getRangeAxis().setTickLabelFont(new Font("Arial", Font.PLAIN, 14)); // Font of the y-axis tick labels
        plot.getDomainAxis().setLabelFont(new Font("Arial", Font.PLAIN, 14)); // Font of the x-axis label
        plot.getRangeAxis().setLabelFont(new Font("Arial", Font.PLAIN, 14)); // Font of the y-axis label

// Customize the line
        plot.getRenderer().setSeriesPaint(0, new Color(31, 119, 180)); // Color of the line
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(3.0f)); // Thickness of the line



// Set the range for the x-axis and y-axis based on the function data
        double maxX = data[0][data[0].length - 1];
        double minX = data[0][0];
        double maXY = Arrays.stream(data).flatMapToDouble(row -> Arrays.stream(row)).max().getAsDouble();
        double miNY = Arrays.stream(data).flatMapToDouble(row -> Arrays.stream(row)).min().getAsDouble();
        if (maxY <= 20) {
            maxY = 20;
            minY = -20;
        } else if (maxY <= 200) {
            maxY = Math.min(Math.ceil(maxY / 10) * 10,400);
            minY =  Math.max(Math.floor(minY / 10) * 10,-400);
        } else {
            maxY = Math.min(Math.ceil(maxY / 100) * 100,400);
            minY = Math.max(Math.floor(minY / 100) * 100, -400);
        }
        chart.getXYPlot().getDomainAxis().setRange(minX, maxX);
        chart.getXYPlot().getRangeAxis().setRange(minY, maxY);



        // Draw the x-axis and y-axis
        chart.getXYPlot().setDomainZeroBaselineVisible(true);
        chart.getXYPlot().setRangeZeroBaselineVisible(true);
        chart.getXYPlot().setDomainGridlinePaint(Color.BLACK);
        chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);

        // Display the chart in a frame
        ChartFrame frame = new ChartFrame("Graph", chart);
        frame.pack();
        frame.setVisible(true);
    }

    public static double evaluate(String function, double x) {
        double y = 0;
        String[] terms = function.split("(?=[+-])"); // split by + or -
        for (String term : terms) {
            term = term.trim(); // remove extra whitespace
            if (term.isEmpty()) {
                continue; // skip empty terms
            }
            double coefficient = 0;
            double exponent = 0;
            double constant = 0;
            if (term.contains("x^2")) {
                String[] parts = term.split("x\\^2");
                coefficient = parts[0].isEmpty() ? 1 : Double.parseDouble(parts[0]);
                exponent = 2;
                if (parts.length > 1) {
                    constant = Double.parseDouble(parts[1]);
                }
            } else if (term.contains("x")) {
                String[] parts = term.split("x");
                coefficient = parts[0].isEmpty() ? 1 : Double.parseDouble(parts[0]);
                exponent = 1;
                if (parts.length > 1) {
                    constant = Double.parseDouble(parts[1]);
                }
            } else {
                constant =
                        Double.parseDouble(term);
            }
            y += coefficient * Math.pow(x, exponent) + constant;
        }
        return y;
    }
}