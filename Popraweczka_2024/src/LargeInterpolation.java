import java.util.List;

public class LargeInterpolation {
    public static double interpolate(double[] x, double[] y, double x1) {
        int n = x.length;
        double result = 0.0;

        for (int i = 0; i < n; i++) {
            double term = y[i];
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    term *= (x1 - x[j]) / (x[i] - x[j]);
                }
            }
            result += term;
        }
        return result;
    }
}
