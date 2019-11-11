package Integration;

import java.util.function.BiConsumer;
import java.util.function.Function;

public class MidpointIntegrator extends Integrator {
    public MidpointIntegrator(Function<Double, Double> f, double a, double b, int n,
                              BiConsumer<String, Double> summer) {
        super(f, a, b, n, summer);
    }

    @Override
    public void run() {
        double dx = (b - a)/n;
        double mid = a+dx/2.0;

        s.accept(name, f.apply(mid)*dx);

        for (int i = 1; i < n; i++) {
            s.accept(name, f.apply(mid + dx*i)*dx);
        }
    }
}
