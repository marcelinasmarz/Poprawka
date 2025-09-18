import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LagrangeInterpolatedFunction implements CalculatedFunction {
    private final double[] x;
    private final double[] y;

    public LagrangeInterpolatedFunction(double[] x, double[] y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double f(double arg) {
        return LargeInterpolation.interpolate(x, y, arg);
    }
    // Metoda fabryczna
    public static LagrangeInterpolatedFunction fromCSV(String filePath) throws IOException {
        List<Double> xs = new ArrayList<>();
        List<Double> ys = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                xs.add(Double.parseDouble(parts[0].trim()));
                ys.add(Double.parseDouble(parts[1].trim()));
            }
        }

        double[] xArr = xs.stream().mapToDouble(Double::doubleValue).toArray();
        double[] yArr = ys.stream().mapToDouble(Double::doubleValue).toArray();

        return new LagrangeInterpolatedFunction(xArr, yArr);
    }
}
}
