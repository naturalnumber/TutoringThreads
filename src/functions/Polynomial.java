package functions;


import java.util.function.Function;

public class Polynomial implements Function<Double, Double> {
    protected final double[] coeficients;

    public Polynomial(double[] coeficients) {this.coeficients = coeficients;}

    @Override
    public Double apply(Double x) {
        double y = 0;

        for (int i = this.coeficients.length-1; i >= 0; i--) {
            y *= x;
            y += this.coeficients[i];
        }

        return y;
    }

    @Override
    public String toString() {
        String name = "Polynomial: ";
        boolean first = true;

        for (int i = this.coeficients.length-1; i >= 0; i--) {
            if (this.coeficients[i] != 0.0d) {
                if (!first) {
                    name += " +";
                }
                if (this.coeficients[i] != 1.0) {
                    name += " ";
                    name += Double.toString(this.coeficients[i]);
                }
                if (i > 0) {
                    name += " x^";
                    name += i;
                }
                first = false;
            }
        }

        return name;
    }

    public Polynomial integrate() {
        double[] cs = new double[this.coeficients.length+1];

        for (int i = 0; i < this.coeficients.length; i++) {
            cs[i+1] = this.coeficients[i]/(i+1);
        }

        return new Polynomial(cs);
    }

    public Polynomial differentiate() {
        double[] cs = new double[this.coeficients.length-1];

        for (int i = 1; i < this.coeficients.length; i++) {
            cs[i-1] = this.coeficients[i]*(i);
        }

        return new Polynomial(cs);
    }
}
