import java.util.Random;

public abstract class MonteCarloMethod extends Integral{

    private int n;
    public MonteCarloMethod(int n, CalculatedFunction function, double dolna, double gorna){
        super(function, dolna, gorna);
        this.n = n;
    }

    public double MonteCarlo() {
        Random random = new Random();
        double sum = 0.0;
        double result = 0.0;
        double x = 0.0;
        for ( int i = 0; i < n; i++){
            x = dolna + (gorna-dolna) * random.nextDouble();
            sum += function.f(x);
        }
        result = sum / n;
        return (gorna-dolna) * result;
    }
}
