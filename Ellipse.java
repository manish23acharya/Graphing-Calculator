import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Ellipse {
    public static void calculateEllipsePoints(double a, double h, double b, double g, double f, double c) {



        // calculate center
        double x0=(h*f - b*g) / (a*b - h*h);
        double y0 = (h*g - a*f) / (a*b - h*h);

        // calculate major axis length and minor axis length
                /*double t1 = (2*(a*f*f + b*g*g + c*h*h - 2*g*f*h - a*b*c)) / ((b-a)*Math.sqrt((a-b)*(a-b) + 4*h*h));
                double t2 = (2*(a*f*f + b*g*g + c*h*h - 2*g*f*h - a*b*c)) / ((a-b)*Math.sqrt((a-b)*(a-b) + 4*h*h));
                double majorAxis = Math.sqrt(Math.abs(t1));
                double minorAxis = Math.sqrt(Math.abs(t2));*/

        // double t1 = Math.sqrt(2 * (a * f * f + b * g * g + c * h * h - 2 * f * g * h - a * b * c) * ((a + b) + Math.sqrt((a - b) * (a - b) + 4 * h * h))) / Math.sqrt((a + b) - Math.sqrt((a - b) * (a - b) + 4 * h * h));
        //double t2 = Math.sqrt(2 * (a * f * f + b * g * g + c * h * h - 2 * f * g * h - a * b * c) * ((a + b) - Math.sqrt((a - b) * (a - b) + 4 * h * h))) / Math.sqrt((a + b) + Math.sqrt((a - b) * (a - b) + 4 * h * h));

        double t1=(2*(a*f*f+b*g*g+c*h*h-2*h*g*f-a*b*c)) / ((h*h-a*b)*(Math.sqrt((a*b)*(a*b)+4*h*h)-(a+b)));
        double t2=(2*(a*f*f+b*g*g+c*h*h-2*h*g*f-a*b*c)) / ((h*h-a*b)*(-Math.sqrt((a*b)*(a*b)+4*h*h)-(a+b)));
        double majorAxis=Math.sqrt(Math.abs(t1));
        double minorAxis=Math.sqrt(Math.abs(t2));

        System.out.println("Center: (" + x0 + ", " + y0 + ")");
        System.out.println("Major axis length: " + majorAxis);
        System.out.println("Minor axis length: " + minorAxis);

        double h1 = x0; // center x coordinate
        double k1 = y0; // center y coordinate
        double a1 = majorAxis; // length of major axis
        double b1 = minorAxis; // length of minor axis

        XYSeries series = new XYSeries("Ellipse");

        // plot points of ellipse
        for (double theta = 0; theta <= 2 * Math.PI; theta += 0.01) {
            double x = h1 + a1 * Math.cos(theta);
            double y = k1 + b1 * Math.sin(theta);
            series.add(x, y);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        // create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Ellipse Plot", // chart title
                "X", // x axis label
                "Y", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                true, // tooltips
                false // urls
        );

        // create chart frame and display chart
        ChartFrame frame = new ChartFrame("Ellipse Plot", chart);
        frame.pack();
        frame.setVisible(true);

    }
}