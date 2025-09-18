import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Product {

    private String name;
    public Product(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public abstract double getPrice(int year, int month);

}
