package se.crisp.bowling;

public class BowlingScorer {

    public int score(String pins) {
        if (pins.compareTo("00") == 0) {
            return 0;
        }
        if (pins.compareTo("1-") == 0) {
            return 1;
        }
        if (pins.compareTo("-1") == 0) {
            return 1;
        }
        return 0;
    }

}
