import static org.junit.jupiter.api.Assertions.*;

class TrapezoidMethodTest {

    @org.junit.jupiter.api.Test
    void calculateply() {
        CalculatedFunction poly= new Polynomial(1.0,2.0,-8.0,-1.0);
        Integral integral = new TrapezoidMethod(poly, 4, 0, 16);
        double result = integral.calculate();
       System.out.println(result);
       assertEquals(39.0, result,0.1);
}}