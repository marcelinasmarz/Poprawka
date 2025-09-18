# Poprawka


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
