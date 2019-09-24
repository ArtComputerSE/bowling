package se.crisp.bowling;

@SuppressWarnings("WeakerAccess")
public class BowlingScorer {
    private ParserIfc parser;

    public BowlingScorer(ParserIfc parser) {
        this.parser = parser;
    }

    public int score(String pins) {
        return parser.parse(pins).score();
    }
}
