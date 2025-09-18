import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FoodProduct extends  Product {
    Double[] prices;
    String[] provinces;

    public Double[] getPrices() {
        return prices;
    }

    public String[] getProvinces() {
        return provinces;
    }

    public FoodProduct(Double[] prices, String name, String[] provinces) {
        super(name);
        this.prices = prices;
        this.provinces = provinces;
    }

    public static FoodProduct fromCsv(Path path) {
        Double[] prices;
        String[] provinces;
        String name;
        try {
            // Wczytaj wszystkie linie pliku
            String[] lines = Files.readAllLines(path).toArray(new String[0]);

            if (lines.length < 3) {
                throw new IllegalArgumentException("Plik musi zawierać przynajmniej 3 linie.");
            }

            name = lines[0];
            provinces = lines[1].split(";");
            prices = Arrays.stream(lines[2].split(";"))
                    .map(s -> s.replace(",", "."))
                    .map(Double::parseDouble)
                    .toArray(Double[]::new);

            if (provinces.length != prices.length) {
                throw new IllegalArgumentException("Liczba województw nie zgadza się z liczbą cen.");
            }


        } catch (IOException e) {
            throw new RuntimeException("Błąd podczas czytania pliku: " + path, e);
        }

        return new FoodProduct(prices, name, provinces);


    }

    public static double getPrice(int year, int month, String province) {

        return 0;
    }

    @Override
    public double getPrice(int year, int month) {
        return 0;
    }
}


