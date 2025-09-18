package game;

public class Player {
    Gesture gesture;
    Duel duel;

    public void makeGesture (Gesture gesture){
        this.gesture = gesture;
    }
    public void enterDuel ( Duel duel){
        this.duel = duel;
    }
    public void leaveDuel(){
        this.duel = null;
    }
    public Gesture getGesture() {
        return gesture;
    }
    public boolean isDueling() {
        return  duel != null;
    }



}
