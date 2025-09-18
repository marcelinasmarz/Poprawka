public class Main {
    public static void main(String[] args) {
        CalculatedFunction function = new Polynomial(1.0, 2.0, 3.0, 4.0);

        System.out.println("f(0) = " + function.f(0));
        System.out.println("f(1) = " + function.f(1));
        System.out.println("f(3)=" + function.f(3));

    }
}