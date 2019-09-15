package se.crisp.bowling;

@SuppressWarnings("WeakerAccess")
public class BowlingScorer {

    private Parser parser = new Parser();

    public int score(String pins) {
        return parser.parse(pins).score();
    }


}
