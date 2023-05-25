import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PairOfStraightLines {
    public static void plot(double A, double h, double b, double g, double f, double c) {
        // Calculate slopes and y-intercepts of each line
        double slope1 = (-h + Math.sqrt(h * h - A * b)) / b;
        double yInt1 = c / slope1;
        double slope2 = (-h - Math.sqrt(h * h - A * b)) / b;
        double yInt2 = c / slope2;

        // Create dataset and add data for each line
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Line 1");
        XYSeries series2 = new XYSeries("Line 2");

        // Add data for line 1
        double x1 = -10;
        double y1 = slope1 * x1 + yInt1;
        series1.add(x1, y1);
        x1 = 10;
        y1 = slope1 * x1 + yInt1;
        series1.add(x1, y1);

        // Add data for line 2
        double x2 = -10;
        double y2 = slope2 * x2 + yInt2;
        series2.add(x2, y2);
        x2 = 10;
        y2 = slope2 * x2 + yInt2;
        series2.add(x2, y2);

        // Add both lines to the dataset
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        // Create chart and plot the dataset
        ChartFrame frame = new ChartFrame("Pair of Straight Lines", ChartFactory.createXYLineChart("Pair of Straight Lines",
                "x", "y", dataset, PlotOrientation.VERTICAL, true, true, false));
        frame.pack();
        frame.setVisible(true);
    }
}
