import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountryWithProvinces extends Country{

    private Country[] provincies;
    public CountryWithProvinces(String name, CountryWithoutProvinces[] provincies) {
        super(name);
        this.provincies = new Country[provincies.length];
    for(int i = 0; i < provincies.length; i++){
        this.provincies[i] = provincies[i];
    }
    }
    public CountryWithoutProvinces[] getProvincies() {
        return (CountryWithoutProvinces[]) provincies;
    }

    public void setProvincies(Country[] provincies) {
        this.provincies = provincies;
    }
    public String toString() {
        return "CountryWithProvinces{name='" + getName() + "', provinces="
                + Arrays.toString(provincies) + "}";
    }
    public List<LocalDate> getAllDates() {
        List<LocalDate> dates = new ArrayList<>();
        if (provincies.length == 0) return dates;

        // przyjmujemy, że wszystkie prowincje mają te same daty
        dates.addAll(provincies[0].getAllDates());
        return dates;
    }

}
