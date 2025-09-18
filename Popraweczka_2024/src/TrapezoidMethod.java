public class TrapezoidMethod extends Integral{
    int n;
    public TrapezoidMethod (CalculatedFunction function,  double gorna,double dolna, int n){
        super(function, gorna, dolna);
        this.n = n;
    }



    @Override
    public double calculate() {
        double h = (gorna - dolna) /(double)n;
        double sum = 0.0;

        sum += 0.5 * function.f(dolna);
        sum += 0.5 * function.f(gorna);

        for(int i = 1; i < n; i++){
            double x = dolna + i * h;
            sum += function.f(x);

        }
        return (sum * h);
    }
}
