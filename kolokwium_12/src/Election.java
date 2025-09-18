import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Election {
    public static List<Candidate> getCandidates() {
        return getCandidates();
    }

    private List<Candidate> candidates;
    private ElectionTurn firstTurn = new ElectionTurn(candidates);
    private ElectionTurn secondTurn = null;





    public Election (List<Candidate> candidates){
        this.candidates = new ArrayList<>();
    }


    public void addCandidate(Candidate candidate){
        candidates.add(candidate);
    }


    public List<Candidate> getCandidatesCopy() {
        return new ArrayList<>(candidates);
    }


    public void populateCandidates(String filePath) throws IOException {

        candidates.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank()) {
                    candidates.add(new Candidate(line.trim()));
                }
            }
        }

    }

    public void populate(String filePath) throws IOException {
        try{
             Candidate winner = firstTurn.winner();
        } catch(NotWinnerException e) {
            List<Candidate> runoff = firstTurn.runOffCandidates();
            secondTurn = new ElectionTurn(runoff);
            List<Vote> vote2 = Collections.singletonList(Vote.fromCsvLine(Vote.fromCsvFile("2.csv").toString()));
            for(Vote v : vote2) {
                secondTurn.addVote(v);
            }
            try{
                Candidate winner = secondTurn.winner();
            } catch(NotWinnerException ex) {
                System.out.println("Brak zwyciezcy w obu turach");
            }
        }


    }



    public ElectionTurn getSecondTurn() {
        return secondTurn;
    }

    public void setSecondTurn(ElectionTurn secondTurn) {
        this.secondTurn = secondTurn;
    }




    public ElectionTurn getFirstTurn() {
        return firstTurn;
    }

    public void setFirstTurn(ElectionTurn firstTurn) {
        this.firstTurn = firstTurn;
    }
}
