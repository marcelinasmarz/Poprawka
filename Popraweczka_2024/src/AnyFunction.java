import java.util.Objects;

public  class AnyFunction implements CalculatedFunction{
    private final CalculatedFunction function;
    private AnyFunction(CalculatedFunction function){
        this.function = Objects.requireNonNull(function);
    }
    public static AnyFunction of(CalculatedFunction function){
        return new AnyFunction(function);
    }
    @Override
    public double f(double x){
        return function.f(x);

    }
    public AnyFunction add(CalculatedFunction other){
        return new AnyFunction(x -> this.f(x) + other.f(x));
    }
    public AnyFunction sub(CalculatedFunction other) {
        return new AnyFunction(x -> this.f(x)- other.f(x));
    }
    public AnyFunction mul(CalculatedFunction other) {
        return new AnyFunction(x-> this.f(x)* other.f(x));
    }
    public AnyFunction scale(double factor){
        return new AnyFunction(x-> factor* this.f(x));

    }
    public AnyFunction negate(){
        return scale(-1.0);
    }

    public AnyFunction compose (CalculatedFunction inner){
        return new AnyFunction(x-> this.f(inner.f(x)));
    }

    public AnyFunction sin(CalculatedFunction sin){
        sin = AnyFunction.of(Math::sin);
        CalculatedFunction finalSin = sin;
        return new AnyFunction(x-> finalSin.f(x));
    }
    public AnyFunction power() {
        return new AnyFunction(x-> this.f(x) * this.f(x));
    }

}
