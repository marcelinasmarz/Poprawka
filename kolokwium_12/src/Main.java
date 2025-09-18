import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Candidate> candidates = List.of(
                new Candidate("Kandydat A"),
                new Candidate("Kandydat B"),
                new Candidate("Kandydat C")
        );

        // Tworzymy obiekt ElectionTurn i wczytujemy głosy
        ElectionTurn election = new ElectionTurn(candidates);
        election.populate("votes.csv"); // Ścieżka do pliku CSV z głosami

        // Tworzymy mapę województw
        VoivodeshipMap map = new VoivodeshipMap();
        List<String> voivodeships = map.getVoivodeships();

        // Generujemy podsumowanie drugiej tury
        Map<String, Map<Candidate, Integer>> summary = election.summarize();

        // Wyświetlamy wyniki dla każdego województwa
        for(String voivodeship : voivodeships) {
            System.out.println("Województwo: " + voivodeship);
            Map<Candidate, Integer> votesInVoivodeship = summary.get(voivodeship);
            if(votesInVoivodeship != null) {
                for(Map.Entry<Candidate, Integer> entry : votesInVoivodeship.entrySet()) {
                    System.out.println("  " + entry.getKey().getName() + ": " + entry.getValue());
                }
            } else {
                System.out.println("  Brak głosów w tym województwie.");
            }
        }
    }
}