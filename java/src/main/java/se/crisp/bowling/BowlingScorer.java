package se.crisp.bowling;

public class BowlingScorer {

    private final Parser parser;
    private Rules rules;

    public BowlingScorer(Parser parser, Rules rules) {
        this.parser = parser;
        this.rules = rules;
    }

    public int score(String pins) {
        Frame frame = parser.parse(pins);
        return rules.score(frame);
    }


}
