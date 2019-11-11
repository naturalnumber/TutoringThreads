package Integration;

import java.util.function.BiConsumer;

public class Summer implements BiConsumer<String, Double> {
    protected double sum = 0.0d;

    @Override
    public void accept(String s, Double d) {
        double current_sum = sum;
        if (s != null) System.out.println(s+" sees current sum of "+current_sum);
        current_sum += d;
        if (s != null) System.out.println(s+" adds "+d+" and gets "+current_sum);
        double new_sum = (sum = current_sum);
        if (s != null) System.out.println(s+" changes sum to "+new_sum);
    }

    public double getSum() {
        return sum;
    }
}
