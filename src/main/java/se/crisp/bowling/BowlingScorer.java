package se.crisp.bowling;

public class BowlingScorer {

    public int score(String pins) {
        if (pins.compareTo("X") == 0) {
            return 0;
        }

        char[] frame = pins.toCharArray();

        if (Character.isDigit(frame[0]) && Character.isDigit(frame[1])) {
            return Character.getNumericValue(frame[0]) + Character.getNumericValue(frame[1]);
        }
        if (Character.isDigit(frame[0]) && frame[1] == '-') {
            return Character.getNumericValue(frame[0]);
        }
        if (Character.isDigit(frame[1]) && frame[0] == '-') {
            return Character.getNumericValue(frame[1]);
        }

        return 0;
    }

}
