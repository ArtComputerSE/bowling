package se.crisp.bowling.traditional;

import se.crisp.bowling.Frame;
import se.crisp.bowling.Rules;

public class TraditionalRules implements Rules {
    @Override
    public int score(Frame frame) {
        return frame.score();
    }
}
