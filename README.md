# Poprawka

Pamiętaj!!

Jak masz ArrayList to jest specjalna funckja indexOf !!


przykład Buildera


// Produkt

class Pizza {
    private String ciasto;
    private String sos;
    private String dodatki;

    public void setCiasto(String ciasto) { this.ciasto = ciasto; }
    public void setSos(String sos) { this.sos = sos; }
    public void setDodatki(String dodatki) { this.dodatki = dodatki; }

    @Override
    public String toString() {
        return "Pizza [ciasto=" + ciasto + ", sos=" + sos + ", dodatki=" + dodatki + "]";
    }
}

// Builder

interface PizzaBuilder {
    void buildCiasto();
    void buildSos();
    void buildDodatki();
    Pizza getResult();
}

// Konkretni builderzy

class MargheritaBuilder implements PizzaBuilder {
    private Pizza pizza = new Pizza();
    public void buildCiasto() { pizza.setCiasto("Cienkie"); }
    public void buildSos() { pizza.setSos("Pomidorowy"); }
    public void buildDodatki() { pizza.setDodatki("Ser mozzarella"); }
    public Pizza getResult() { return pizza; }
}

class PepperoniBuilder implements PizzaBuilder {
    private Pizza pizza = new Pizza();
    public void buildCiasto() { pizza.setCiasto("Grube"); }
    public void buildSos() { pizza.setSos("Ostry"); }
    public void buildDodatki() { pizza.setDodatki("Ser mozzarella, salami"); }
    public Pizza getResult() { return pizza; }
}

// Dyrektor


class Pizzeria {
    private PizzaBuilder builder;
    public Pizzeria(PizzaBuilder builder) { this.builder = builder; }
    public Pizza zrobPizze() {
        builder.buildCiasto();
        builder.buildSos();
        builder.buildDodatki();
        return builder.getResult();
    }
}

// Test

class MainPizza {
    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria(new MargheritaBuilder());
        System.out.println(pizzeria.zrobPizze());

        pizzeria = new Pizzeria(new PepperoniBuilder());
        System.out.println(pizzeria.zrobPizze());
    }
}

READERY
1️⃣ FileReader – odczyt całego pliku do String
import java.io.*;

public class FileReaderToString {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader("example.txt")) {
            int ch;
            while ((ch = fr.read()) != -1) {
                sb.append((char) ch);
            }
        }
        String content = sb.toString();
        System.out.println("Zawartość pliku: " + content);
    }
}

2️⃣ BufferedReader – odczyt linii do List<String>
import java.io.*;
import java.util.*;

public class BufferedReaderToList {
    public static void main(String[] args) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("example.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }
        System.out.println("Linie pliku: " + lines);
    }
}

3️⃣ CharArrayReader – odczyt do String
import java.io.*;

public class CharArrayReaderToString {
    public static void main(String[] args) throws IOException {
        char[] data = "Hello World!".toCharArray();
        StringBuilder sb = new StringBuilder();
        try (CharArrayReader car = new CharArrayReader(data)) {
            int ch;
            while ((ch = car.read()) != -1) {
                sb.append((char) ch);
            }
        }
        String content = sb.toString();
        System.out.println("Odczyt z tablicy znaków: " + content);
    }
}

4️⃣ StringReader – odczyt do String
import java.io.*;

public class StringReaderToString {
    public static void main(String[] args) throws IOException {
        String data = "Dane z StringReader";
        StringBuilder sb = new StringBuilder();
        try (StringReader sr = new StringReader(data)) {
            int ch;
            while ((ch = sr.read()) != -1) {
                sb.append((char) ch);
            }
        }
        String content = sb.toString();
        System.out.println(content);
    }
}

5️⃣ InputStreamReader – odczyt z pliku do String
import java.io.*;

public class InputStreamReaderToString {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream("example.txt"), "UTF-8")) {
            int ch;
            while ((ch = isr.read()) != -1) {
                sb.append((char) ch);
            }
        }
        String content = sb.toString();
        System.out.println(content);
    }
}

6️⃣ PipedReader – odczyt między wątkami do String
import java.io.*;

public class PipedReaderToString {
    public static void main(String[] args) throws IOException, InterruptedException {
        PipedReader reader = new PipedReader();
        PipedWriter writer = new PipedWriter(reader);

        StringBuilder sb = new StringBuilder();

        Thread writerThread = new Thread(() -> {
            try {
                writer.write("Dane między wątkami");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread readerThread = new Thread(() -> {
            try {
                int ch;
                while ((ch = reader.read()) != -1) {
                    sb.append((char) ch);
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        writerThread.start();
        readerThread.start();
        writerThread.join();
        readerThread.join();

        String content = sb.toString();
        System.out.println(content);
    }
}

