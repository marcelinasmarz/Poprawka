import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class NonFoodProduct extends Product{
    String name;
    Double[] prices;

    private NonFoodProduct(String name, Double[] prices) {
        super(name);
        this.prices = prices;
    }

    public static NonFoodProduct fromCsv(Path path) {
        String name;
        Double[] prices;

        try {
            Scanner scanner = new Scanner(path);
            name = scanner.nextLine(); // odczytuję pierwszą linię i zapisuję ją jako nazwa
            scanner.nextLine();  // pomijam drugą linię z nagłówkiem tabeli
            prices = Arrays.stream(scanner.nextLine().split(";")) // odczytuję kolejną linię i dzielę ją na tablicę
                    .map(value -> value.replace(",",".")) // zamieniam polski znak ułamka dziesiętnego - przecinek na kropkę
                    .map(Double::valueOf) // konwertuję string na double
                    .toArray(Double[]::new); // dodaję je do nowo utworzonej tablicy

            scanner.close();

            return new NonFoodProduct(name, prices);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double getPrice(int year, int month){
        double price;
        if(month < 1 || month>12) throw new  IndexOutOfBoundsException("Nie ma takiego miesiąca");
        if((year < 2010 || year > 2022) || (year ==2022 && month > 3)) throw new IndexOutOfBoundsException("Zły zakres danych");

        String fileName = getName() + ".csv";
        String miesiąc = "" + month;
        String szukanyOkres = year + " " + miesiąc.toUpperCase();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String header = reader.readLine(); // pierwszy wiersz z datami
            if (header == null) return -1;

            String[] periods = header.split(";");
            int periodIndex = -1;
            String dane = reader.readLine();
            String [] prices = dane.split(";");
            // Znajdź indeks podanego okresu
            for (int i = 1; i < periods.length; i++) {
                if (periods[i].trim().equalsIgnoreCase(szukanyOkres)) {
                    periodIndex = i;
                    break;
                }

            }
            price = Double.parseDouble(prices[periodIndex]);

            if (periodIndex == -1) {
                System.out.println("Nie znaleziono okresu: " + szukanyOkres);
                return -1;
            }



    } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return price;
}

}
