package se.crisp.bowling;

@SuppressWarnings("WeakerAccess")
public class BowlingScorer {

    private Parser parser = new ParserImpl();

    public int score(String pins) {
        return parser.parse(pins).score();
    }


}
