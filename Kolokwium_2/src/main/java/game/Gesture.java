package game;

public enum Gesture {
    ROCK,
    PAPER,
    SCISSORS;
    public static Gesture fromString(String input) {
        if(input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        switch (input.toLowerCase()) {
            case "r" : return ROCK;
            case "p" : return PAPER;
            case "s" : return SCISSORS;
            default:
                throw new IllegalArgumentException("Unknown gesture: " + input);
        }
    }
    public int compareWith(Gesture gesture){
        if(this == gesture){
            return 0;
        }
        switch (this){
            case ROCK:
                return(gesture == SCISSORS) ? 1 : -1;
            case PAPER:
                return (gesture == ROCK) ? 1 : -1;
            case SCISSORS:
                return( gesture == PAPER) ? 1 : -1;
            default:
                throw new IllegalStateException("Unexpected value" + this);
        }
    }
}
