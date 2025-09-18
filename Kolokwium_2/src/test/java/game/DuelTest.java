package game;

import org.junit.Test;

import static org.junit.Assert.*;

public class DuelTest {
    @Test
    public void playersShouldBeDuelingAfterEnteringDuel() {
        Player p1 = new Player();
        Player p2 = new Player();

        Duel duel = new Duel(p1, p2);

        assertTrue(p1.isDueling());
        assertTrue(p2.isDueling());
    }
    @Test
    public void testEvaluate_Player1Wins() {
        Player p1 = new Player();
        Player p2 = new Player();
        Duel duel = new Duel(p1, p2);

        // player1: ROCK, player2: SCISSORS → ROCK wygrywa
        p1.makeGesture(Gesture.fromString("p"));
        p2.makeGesture(Gesture.fromString("s"));

        Duel.Result result = duel.evaluate();

        assertNotNull(result);
        assertSame(p1, result.winner());

    }

    @Test
    public void testEvaluate_Draw() {
        Player p1 = new Player();
        Player p2 = new Player();
        Duel duel = new Duel(p1, p2);

        // obaj gracze: PAPER → remis
        p1.makeGesture(Gesture.PAPER);
        p2.makeGesture(Gesture.PAPER);

        Duel.Result result = duel.evaluate();

        assertNull(result);
    }
}
