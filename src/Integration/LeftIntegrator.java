package Integration;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class LeftIntegrator extends Integrator {
    public LeftIntegrator(Function<Double, Double> f, double a, double b, int n,
                          BiConsumer<String, Double> summer) {
        super(f, a, b, n, summer);
    }

    @Override
    public void run() {
        double dx = (b - a)/n;

        s.accept(name, f.apply(a)*dx);

        for (int i = 1; i < n; i++) {
            s.accept(name, f.apply(a + dx*i)*dx);
        }
    }
}
