import java.util.Arrays;

public class Polynomial implements CalculatedFunction {
    private final double[] cofficients;

    public Polynomial(double... cofficients){
        this.cofficients = Arrays.copyOf(cofficients, cofficients.length);
    }
    public double f(double x) {
        double result = 0.0;
        for(int i = cofficients.length-1; i >=0; i--){
            result = result * x + cofficients[i];

        }
        return result;
    }
    public String toString() {
        return Arrays.toString(cofficients);
    }

}

