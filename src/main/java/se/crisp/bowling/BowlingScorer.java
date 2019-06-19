package se.crisp.bowling;

class BowlingScorer {

    int score(String pins) {
        if (pins.contains("-")) {
            return 1;
        }
        return 0;
    }

}
