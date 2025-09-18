import java.io.IOException;
import java.util.Map;

public class VoteMap extends VoivodeshipMap{

    private final Map<String, Vote> resultsByVoivodeship = new HashMap<>();
    private final Map<Candidate, String> candidateColors = new HashMap<>();

    // Ustawienie wyników dla województwa
    public void addResult(String voivodeship, Vote vote) {
        resultsByVoivodeship.put(voivodeship, vote);
    }

    // Powiązanie kandydata z kolorem
    public void setCandidateColor(Candidate candidate, String color) {
        candidateColors.put(candidate, color);
    }

    // Nadpisanie kolorowania – wybór zwycięzcy w województwie
    @Override
    protected String getColor(String voivodeship) {
        Vote vote = resultsByVoivodeship.get(voivodeship);
        if (vote == null) {
            return "lightgray"; // brak wyników → domyślny szary
        }

        // Szukamy zwycięzcy
        Candidate winner = null;
        int maxVotes = -1;

        for (Candidate c : candidateColors.keySet()) {
            int votes = vote.votes(c);
            if (votes > maxVotes) {
                maxVotes = votes;
                winner = c;
            }
        }

        return candidateColors.getOrDefault(winner, "black"); // domyślnie czarny
    }

    // Zapis do SVG – korzysta z VoivodeshipMap.saveToSvg()
    @Override
    public void saveToSvg(String filePath) throws IOException {
        super.saveToSvg(filePath);
    }
}
