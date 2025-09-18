public class IntegralCalculator {
    public static double integrate(LagrangeInterpolatedFunction func, double a, double b, int n) {
        double minX = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;

        for (double xi : func.x) {
            if (xi < minX) minX = xi;
            if (xi > maxX) maxX = xi;
        }

        if (a < minX || b > maxX) {
            throw new IllegalArgumentException("Przedział całkowania wykracza poza punkty interpolacji!");
        }

        // Prosta metoda trapezów
        double h = (b - a) / n;
        double sum = 0.5 * (func.f(a) + func.f(b));

        for (int i = 1; i < n; i++) {
            double xi = a + i * h;
            sum += func.f(xi);
        }

        return sum * h;
    }
}
