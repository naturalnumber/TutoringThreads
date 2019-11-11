package functions;

import java.util.function.Function;

public class Sin implements Function<Double, Double> {
    protected final double A, W, PHI;

    public Sin() {
        this(1, 1, 0);
    }

    public Sin(double a, double w, double phi) {
        A = a;
        W = w;
        PHI = phi;
    }

    @Override
    public Double apply(Double x) {
        double y = 0;

        y = W * x + PHI;

        y = Math.sin(y);

        y *= A;

        return y;
    }

    @Override
    public String toString() {
        String name = "Trig: ";

        if (A != 1.0) {
            name += Double.toString(A);
            name += " ";
        }

        name += "Sin(";

        if (W != 1.0) {
            name += Double.toString(W);
            name += " ";
        }

        name += "x";

        if (PHI != 0.0) {
            name += " + ";
            name += Double.toString(PHI);
        }

        name += ")";

        return name;
    }
}
