import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public abstract class Country {
    private final String name;
    private static String pathDeaths;
    private static String pathConfirmated;
    public String getName() {
        return name;
    }

    public Country (String name){
        this.name = name;


    }
    public static void setFiles(String pathDeath, String pathConfirmate) throws FileNotFoundException {
    File f1 = new File(pathConfirmate);
    File f2 = new File(pathDeath);
    if(!f1.exists() || f1.canRead()){
        throw new FileNotFoundException(pathConfirmate);
    }
    if(!f2.exists() ||f2.canRead()){
        throw new FileNotFoundException(pathDeath);
    }
    pathConfirmated = pathConfirmate;
    pathDeaths = pathDeath;
    }
    public static Country fromCsv(String name) throws IOException, CountryNotFoundException {

        List<String[]> confirmed = readCsv(pathConfirmated);
        List<String[]> deaths = readCsv(pathDeaths);

        String[] countriesRow = confirmed.get(0);
        String[] provincesRow = confirmed.get(1);

        CountryColumns cc = getCountryColumns(countriesRow, name);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy", Locale.ENGLISH);

        if(cc.columnCount == 1 && provincesRow[cc.firstColumnIndex].equalsIgnoreCase("nan")){
            CountryWithoutProvinces country = new CountryWithoutProvinces(name);

            for(int i = 2; i < confirmed.size(); i++){
                String dateStr = confirmed.get(i)[0];
                LocalDate date = LocalDate.parse(dateStr,formatter);

                int cases = Integer.parseInt(confirmed.get(i)[cc.firstColumnIndex]);
                int deathNum = Integer.parseInt(deaths.get(i)[cc.firstColumnIndex]);

                country.addDailyStatistic(date, cases, deathNum);
            }
            return country;
        }

        CountryWithoutProvinces[] provinces = new CountryWithoutProvinces[cc.columnCount];
        for(int i = 0; i < cc.columnCount; i++){
            int col = cc.firstColumnIndex + i;
            provinces[i] = new CountryWithoutProvinces(provincesRow[col]);

            }
        for(int i = 2; i< confirmed.size(); i++){
            String dateStr = confirmed.get(i)[0];
            LocalDate date = LocalDate.parse(dateStr,formatter);
            for(int j = 0; j < cc.columnCount; j++){
                int col = cc.firstColumnIndex + j;
                int cases = Integer.parseInt(confirmed.get(i)[col]);
                int deathNum = Integer. parseInt(deaths.get(i)[col]);
                provinces[j].addDailyStatistic(date,cases,deathNum);
            }
        }
        return new CountryWithProvinces(name, provinces);


    }
//fajny kodzik na odczytywanie plików z info dzielącymi ; lub innym
    private static List<String[]> readCsv(String filepath) throws IOException{
        List<String[]> rows = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while((line = br.readLine()) != null) {
                rows.add(line.split(";"));
            }
        }
        return rows;



    }
    public static Country[] fromCsv(String[] countryNames) throws IOException {
        List<Country> result = new ArrayList<>();

        for (String name : countryNames) {
            try {
                Country country = fromCsv(name);  // wywołujemy wersję jednokrajową
                result.add(country);
            } catch (CountryNotFoundException e) {
                System.out.println(e.getMessage());  // wyświetlamy komunikat
                // i po prostu nie dodajemy nic do listy
            }
        }

        return result.toArray(new Country[0]);
    }

    private static int[] extractColumn(List<String[]> table, int col) {
        int[] values = new int[table.size() - 2];
        for(int i = 2; i < table.size();i++){
            values[i-2] = Integer.parseInt(table.get(i)[col]);
        }
        return values;
    }
    private static class CountryColumns {
       public final int firstColumnIndex;
       public final int columnCount;

       public CountryColumns(int firstColumnIndex, int columnCount){
           this.firstColumnIndex = firstColumnIndex;
           this.columnCount = columnCount;
       }


    }
    private static CountryColumns getCountryColumns(String[] csvRow,String countryName) throws CountryNotFoundException, IOException {
            int first = -1;
            int count = 0;
            for(int i = 0; i < csvRow.length; ++i){
                if(csvRow[i].equalsIgnoreCase(countryName)){
                    if(first==-1){
                        first= i;
                    }
                    count++;
                }
            }
            if(first==-1){
                throw new CountryNotFoundException(countryName);
            }
            return new CountryColumns(first,count);

    }
    public abstract int getConfirmedCases(LocalDate date);
    public abstract  int getDeaths(LocalDate date);

    public static void sortByDeaths(List<Country> countries, LocalDate startDate, LocalDate endDate ){
        countries.sort((c1,c2) -> {
            int deaths1 = 0, deaths2 = 0;
            LocalDate date = startDate;
            while (!date.isAfter(endDate)) {
                deaths1 += c1.getDeaths(date);
                deaths2 += c2.getDeaths(date);
                date = date.plusDays(1);
            }
            return Integer.compare(deaths2, deaths1);

        });
            }
            public void saveToDataFile (String path) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yy");
        try(BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            br.write("Date\tConfirmated\tDeaths");
            br.newLine();
            for(LocalDate date : getAllDates()) {
                int cases = getConfirmedCases(date);
                int deaths = getDeaths(date);
                br.write(date.format(formatter) + "\t" + cases + "\t" + deaths);
                br.newLine();

            }
        }
            }

    private LocalDate[] getAllDates() {
    }


    public List<LocalDate> getAllDates;
        }




