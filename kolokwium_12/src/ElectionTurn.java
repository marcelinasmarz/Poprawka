import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionTurn {

    private List<Candidate> candidates;
    private List<Vote> votes;

    public ElectionTurn(List<Candidate> candidates){
        this.candidates = candidates;
    }
    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void populateVotes(String filePath) throws IOException {

        candidates.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    votes.add(Vote.fromCsvLine(line, Election.getCandidates()));
                }
            }
        }

    }
    public void populate(String filePath) throws IOException {
        populateVotes(filePath);
    }

    public Candidate winner() throws NotWinnerException {
        Map<Candidate, Integer> totalVotes = new HashMap<>();
        for(Candidate c : candidates) {
            totalVotes.put(c,0);
        }
        for(Vote v : votes) {
            for(Candidate c : candidates){
                int current = totalVotes.get(c);
                totalVotes.put(c,current+v.vote(c));
            }

        }
        //zamiana Integera z mapy na inty do sumowania wartości WAŻNE!!!
        int sum = totalVotes.values().stream().mapToInt(Integer::intValue).sum();
        for(Candidate c : candidates){
            if(totalVotes.get(c) > sum / 2.0) {
                return c;
            }
        }
        throw  new NotWinnerException("Brak zwycięzcy w tej turze!");

    }
    public List<Candidate> runOffCandidates() {
        Map<Candidate, Integer> totalVotes = new HashMap<>();
        for(Candidate c : candidates) {
            totalVotes.put(c,0);
        }
        for(Vote v : votes) {
            for(Candidate c : candidates){
                int current = totalVotes.get(c);
                totalVotes.put(c,current+v.vote(c));
            }

        }
        //kodzik na sortowanie i subliste czy skrócenie posortowanej listy cool
        List<Candidate> top3 = new ArrayList<>(candidates);
        top3.sort((c1,c2) -> totalVotes.get(c2) - totalVotes.get(c1));
        return top3.subList(0,Math.min(2,top3.size()));
    }


}

