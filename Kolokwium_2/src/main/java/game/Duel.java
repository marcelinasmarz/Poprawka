package game;

public class Duel {
    private Player player1;
    private Player player2;
    private Gesture gesture1;
    private Gesture gesture2;

    public Duel(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;

        player1.enterDuel(this);
        player2.enterDuel(this);
    }
    public void setGesture(Player player, Gesture gesture) {
        if (player.equals(player1)) {
            this.gesture1 = gesture;
            player.makeGesture(gesture);
        } else if (player.equals(player2)) {
            this.gesture2 = gesture;
            player.makeGesture(gesture);
        }
    }

    // Sprawdzenie zwycięzcy
    public int result() {
        if (gesture1 == null || gesture2 == null) {
            throw new IllegalStateException("Obaj gracze muszą wybrać gest!");
        }

        return gesture1.compareWith(gesture2);
        // Zwraca:
        //  0 -> remis
        //  1 -> gracz 1 wygrał
        // -1 -> gracz 2 wygrał
    }

    // Zakończenie pojedynku
    public void endDuel() {
        player1.leaveDuel();
        player2.leaveDuel();
    }
    public void handleGesture(Player player, Gesture gesture) {
        if(player == player1){
            gesture1 = gesture;
        } else if(player == player2) {
            gesture2 = gesture;
        } else {
            throw new IllegalStateException("Gracz nie należy do pojedynku");
        }
    }
    public record Result(Player winner, Player loser){

    }
    public Result evaluate() {
        Gesture g1 = player1.getGesture();
        Gesture g2 = player2.getGesture();

        if (g1 == null || g2 == null) return null;
        if (g1 == g2) return null;

        boolean p1Wins =
                (g1 == Gesture.ROCK && g2 == Gesture.SCISSORS) ||
                        (g1 == Gesture.PAPER && g2 == Gesture.ROCK) ||
                        (g1 == Gesture.SCISSORS && g2 == Gesture.PAPER);

        return p1Wins ? new Result(player1, player2) : new Result(player2, player1);
    }



}



