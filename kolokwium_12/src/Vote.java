import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Vote {
    private Map<List<Candidate>, Integer> voteForCandidate;

    private List<String> location;

    public Vote(List<String> location){
        this.voteForCandidate = new HashMap<List<Candidate>, Integer>();
        this.location = new ArrayList<>();
    }



    public void addVote(List<Candidate> candidate, int votes){
        voteForCandidate.merge(candidate,votes,Integer::sum);
    }
    public  int getVoteForCandidate(Candidate candidate){
        return  voteForCandidate.getOrDefault(candidate,0);
    }
    public List<String> getLocation() {
        return location;
    }
    public HashMap<List<Candidate>, Integer> getVoteForCandidateMap() {
        return new HashMap<>(voteForCandidate);
    }


    public static Vote fromCsvLine(String line) {
        List<Candidate> candidates = Election.getCandidates();

        String[] parts = line.split(",");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Niepoprawny format CSv: " + line);
        }
        List<String> location = List.of(parts[0], parts[1], parts[2]);
        Vote vote  = new Vote(location);
        for(int i = 3; i < parts.length; i ++){
            int votes = Integer.parseInt(parts[i]);
            Candidate ca = candidates.get(i-3);//dopisywanie kanydata do kolumny
            vote.addVote(candidates,votes);
             }


        return vote;
    }

    public static List<Vote> fromCsvFile(String path) throws IOException {
        List<Vote> votes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // Pomijamy nagłówki (pierwsze dwa wiersze)
            br.readLine(); // wiersz z nazwami państw/kandydatów
            br.readLine(); // wiersz z prowincjami lub nan

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue; // pomijamy puste linie
                votes.add(fromCsvLine(line));
            }
        }
        return votes;
    }
    public void setLocation(List<String> loc) {
        this.location = new ArrayList<>(loc);
    }

//łączenie listy z mapą
    public static Vote summarize(List<Vote> votes) {
        Vote summary = new Vote(Collections.emptyList());
        for(Vote v : votes) {
            for(Map.Entry<List<Candidate>,Integer> entry : v.voteForCandidate.entrySet()){
                List<Candidate> candidate = entry.getKey();
                int count = entry.getValue();

                summary.addVote(candidate, count);

            }
        }
        return summary;
    }
    public static Vote summarize(List<Vote> votes, List<String> locationFilter) {
        List<Vote> filtered = filterByLocation(votes, locationFilter);
        Vote result = summarize(filtered); // sumujemy tylko przefiltrowane głosy
        if (result != null) {
            result.setLocation(locationFilter); // ustawiamy lokalizację w nowym obiekcie
        }
        return result;
    }

    public int vote(Candidate candidate ) {

        return voteForCandidate.getOrDefault(candidate,0);
    }

    public double percentage(Candidate candidate) {
        float candidateVotes = vote(candidate);
        int totalVotes = voteForCandidate.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        //lub
        //int totalVotes = 0;
        //for(int v : votesForCandidates.values()){
        //totalVotes+=v;}
        if(totalVotes ==0) {
            return 0.0;
        }
        return (candidateVotes * 100.0) / totalVotes;
    }
    public String toString(List<Candidate> candidates) {
        String result = "";
        for (Candidate candidate : candidates) {
            double perc = percentage(candidate);
            result = "Kandydat" + candidate.name() + "\n wynik procentowy" + String.format("%.2f%%, perc") + "\n";
        }
        return result;
    }

    public static List<Vote> filterByLocation(List<Vote> votes, List<String> location) {
        return null;
    }

    public int votes(Candidate c) {
    }
}
