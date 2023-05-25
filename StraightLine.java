import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class StraightLine {
    public static void plot(double a, double b, double c) {
        XYSeries line = new XYSeries("Line");
        double x, y;
        if (a == 0 && b == 0) {
            // The line is a point
            x = c / 2;
            y = c / 2;
            line.add(x, y);
        } else if (a == 0) {
            // The line is parallel to the y-axis
            x = -c / b;
            for (int i = -100; i <= 100; i++) {
                y = i;
                line.add(x, y);
            }
        } else if (b == 0) {
            // The line is parallel to the x-axis
            y = -c / a;
            for (int i = -100; i <= 100; i++) {
                x = i;
                line.add(x, y);
            }
        } else {
            // The line is not parallel to either axis
            for (int i = -100; i <= 100; i++) {
                x = i;
                y = (-a * x - c) / b;
                line.add(x, y);
            }
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(line);
        JFreeChart chart = ChartFactory.createXYLineChart("Straight Line", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        ChartFrame frame = new ChartFrame("Chart", chart);
        frame.pack();
        frame.setVisible(true);
    }


}
