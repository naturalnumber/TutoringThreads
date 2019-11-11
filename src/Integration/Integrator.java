package Integration;

import java.util.function.BiConsumer;
import java.util.function.Function;

abstract class Integrator implements Runnable {
    protected final double a, b;
    protected final int                        n;
    protected final Function<Double, Double>   f;
    protected final BiConsumer<String, Double> s;
    protected final String                     name;

    protected Integrator(Function<Double, Double> f, double a, double b, int n, BiConsumer<String, Double> summer) {
        this.a = a;
        this.b = b;
        this.n = n;
        this.f = f;
        this.s = summer;
        this.name = this.getClass().getName()+" on ["+a+", "+b+"] with "+n+" points";
    }
}
