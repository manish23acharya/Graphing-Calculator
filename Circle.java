import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;
public class Circle {

    public static void calculateCirclePoints(double a, double h, double b, double g, double f, double c) {
        double radius = Math.sqrt(((g* g)+(f*f)) - a*b*c);
        double centerX = -g / a;
        double centerY = -f / b;

        double x1 = centerX + radius;
        double y1 = centerY;
        double x2 = centerX - radius;
        double y2 = centerY;
        double[][] data = new double[2][360];
        System.out.println("The center of the circle is (" + centerX + ", " + centerY + ")");
        System.out.println("The radius of the circle is " + radius);
        System.out.println("The points on the circle are (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")");
        for (int i = 0; i < 360; i++) {
            double theta = Math.toRadians(i);
            double x = centerX + radius * Math.cos(theta);
            double y = centerY + radius * Math.sin(theta);
            data[0][i] = x;
            data[1][i] = y;

        }

        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("Circle", data);

        JFreeChart chart = ChartFactory.createXYLineChart("Circle", "X", "Y", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = (XYPlot) chart.getPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesShapesVisible(0, false);
        renderer.setSeriesLinesVisible(0, true);
        renderer.setSeriesPaint(0, Color.BLUE);
        plot.setRenderer(renderer);

        JFrame frame = new JFrame("Circle Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 500));
        panel.setLayout(new java.awt.BorderLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel, java.awt.BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);

    }



}