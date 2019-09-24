package se.crisp.bowling;

@SuppressWarnings("WeakerAccess")
public class BowlingScorer {

    public int score(String pins) {
        ParserIfc parser = new ParserImpl();
        return parser.parse(pins).score();
    }
}
