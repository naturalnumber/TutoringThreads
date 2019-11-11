import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

import Integration.Summer;
import Integration.TrapezoidIntegrator;
import functions.Polynomial;

public class ThreadRacer {
    private static int nPoints = 10;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        int number;

        System.out.print("Enter example number (1-10), 0 to change the number of points, or quit to quit: ");
        line = in.nextLine();

        while (!line.equalsIgnoreCase("quit")) {
            try {
                number = Integer.parseInt(line);

                switch (number) {
                    case 1:
                        polynomialComparison(new Polynomial(new double[]{0.0, 1.0}), 0.0, 1.0);
                        break;
                    case 2:
                        polynomialComparison(new Polynomial(new double[]{0.25, 10.0}), 55.0, 100.0);
                        break;
                    case 3:
                        polynomialComparison(new Polynomial(new double[]{0.0, 1.0, 2.0, 3.0, 4.0}), 0.0, 100.0);
                        break;
                    case 4:
                        polynomialComparison(new Polynomial(new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0}), -1.0, 1.0);
                        break;
                    case 5:
                        polynomialComparison(new Polynomial(new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0}), -1.0, 1.0);
                        break;
                    case 6:
                        polynomialComparison(new Polynomial(new double[]{1.0, 2.0, 1.0}), 0.0, 0.00001);
                        break;
                    case 7:
                        polynomialComparison(new Polynomial(new double[]{1.0, -2.0, 1.0}), 1.0, 5.0);
                        break;
                    case 8:
                        polynomialComparison(new Polynomial(new double[]{1.0, 3.0, 3.0, 1.0}), 0.0, 1.0);
                        break;
                    case 9:
                        polynomialComparison(new Polynomial(new double[]{1.0, 4.0, 6.0, 4.0, 1.0}), 0.0, 1.0);
                        break;
                    case 10:
                        polynomialComparison(new Polynomial(new double[]{0.0, 0.0, 1.0}), -1.0, 1.0);
                        break;
                    case 0:
                        try {
                            System.out.print("Enter a number of points: ");
                            line = in.nextLine();
                            nPoints = Integer.parseInt(line);
                        }
                        catch (NumberFormatException nfe) {
                            System.out.println("Can't parse "+line);
                        }
                        break;
                    default:
                        System.out.println("No example: "+number);
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("Can't parse "+line);
            }

            System.out.print("Enter example number (1-10), 0 to change the number of points, or quit to quit: ");
            line = in.nextLine();
        }

        System.out.println("Goodbye!");
    }

    public static void polynomialComparison(Polynomial f, double a, double b) {
        Polynomial F = f.integrate();

        System.out.println("Integrating: "+f);

        System.out.println("Correct answer: "+(F.apply(b)-F.apply(a)));


        System.out.println("With 1 thread this happens:");
        multiThreadIntegration(f, a, b, nPoints, 1);
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("With 10 threads this happens:");
        multiThreadIntegration(f, a, b, nPoints, 10);
        System.out.println();
        System.out.println();
        System.out.println();

    }

    public static void multiThreadIntegration(Function<Double, Double> f, double a, double b, int n, int threadCount) {
        ArrayList<Thread> threads = new ArrayList<>(threadCount);
        int per = n / threadCount;
        double width = (b - a)/threadCount;
        ThreadGroup group = new ThreadGroup("Integration thread group.");

        Summer s = new Summer();

        Thread thread;

        for (int i = 1; i < threadCount; i++) {
            thread = new Thread(group, new TrapezoidIntegrator(f, a+(i-1)*width, a+i*width, per, s), "Integration Thread "+i);
            threads.add(thread);
            thread.start();
        }

        thread = new Thread(group, new TrapezoidIntegrator(f, a+(threadCount-1)*width, b, n - per*(threadCount-1), s), "Integration Thread "+threadCount);
        threads.add(thread);
        thread.start();

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("The integration result is "+s.getSum());
    }

    public static void fTest(Function<Double, Double> f, double a, double b, int n) {
        double dx = (b - a)/n, x;

        System.out.println("f("+a+") = "+f.apply(a));

        for (int i = 1; i < n; i++) {
            x = a + dx*i;
            System.out.println("f("+x+") = "+f.apply(x));
        }

        System.out.println("f("+b+") = "+f.apply(b));
    }

}
