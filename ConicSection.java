public class ConicSection {
    private double A;
    private double B;
    private double b;
    private double D;
    private double E;
    private double c;

    public ConicSection(double A, double B, double b, double D, double E, double c) {
        this.A = A;
        this.B = B;
        this.b = b;
        this.D = D;
        this.E = E;
        this.c = c;
    }

    public void calculateConicSection() {
        double h = B / 2;
        double g = D / 2;
        double f = E / 2;

        double del = A * b * c + 2 * f * g + A * f * f + b * g * g - c * h * h;
        if(A==0 && b==0 && h==0){
            System.out.println("The equation represents straight line.");
            StraightLine.plot(D,E,c);
        }

       else if (del == 0 && A != 0 && b != 0 && h != 0) {
            System.out.println("The equation represents pair of straight lines.");
            PairOfStraightLines.plot(A,h,b,g,f,c);
        }
       else if (del != 0 && A == b && h == 0) {
            System.out.println("The equation represents a circle.");
            Circle.calculateCirclePoints(A, h, b, g, f, c);
        }
        else if (del != 0 && A * b - h * h == 0) {
            System.out.println("The equation represents a parabola.");

        }
       else if (del != 0 && A * b - h * h < 0) {
            System.out.println("The equation represents a hyperbola.");
        }
       else if (del != 0 && A * b - h * h > 0) {
            System.out.println("The equation represents an ellipse.");
            Ellipse.calculateEllipsePoints(A, h, b, g, f, c);
        }
       else {
            System.out.println("Invalid input.");
        }
    }
}
