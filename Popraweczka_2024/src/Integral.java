public abstract class Integral {
    CalculatedFunction function;
    protected double gorna;
    protected double dolna;

    public Integral(CalculatedFunction function, double gorna, double dolna){
        this.function = function;
        this.gorna = gorna;
        this.dolna = dolna;
    }
    public abstract double calculate();
}
