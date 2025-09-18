import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

 public class CountryWithoutProvinces extends Country{
    public CountryWithoutProvinces(String name) {
        super(name);
        this. statistics = new ArrayList<>();
    }
   private static List<DailyStatistic> statistics;

    private static class DailyStatistic {
         LocalDate date;
         int cases;
         int deaths;

        public DailyStatistic(LocalDate date, int cases, int deaths) {
            this.cases = cases;
            this.date = date;
            this.deaths = deaths;
        }
        public List<LocalDate> getAllDates() {
            List<LocalDate> dates = new ArrayList<>();
            for (DailyStatistic stat : statistics) {
                dates.add(stat.date);
            }
            return dates;
        }
        public String toString() {
            return date + ": case=" + cases + ", deaths=" + deaths;
        }
    }
    public void addDailyStatistic(LocalDate date, int cases, int deaths){
        statistics.add(new DailyStatistic(date,cases,deaths));
    }
    public String toString() {
        StringBuilder sb = new StringBuilder("CountryWithoutProvinces[name=" + getName()+ "]\n");
        for(DailyStatistic stat : statistics) {
            sb.append(" ").append("\n");
        }
        return sb.toString();
    }
}
