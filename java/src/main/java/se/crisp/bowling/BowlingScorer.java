package se.crisp.bowling;

@SuppressWarnings("WeakerAccess")
public class BowlingScorer {

    private final Parser parser;

    public BowlingScorer(Parser parser) {
        this.parser = parser;
    }

    public int score(String pins) {
        return parser.parse(pins).score();
    }


}
