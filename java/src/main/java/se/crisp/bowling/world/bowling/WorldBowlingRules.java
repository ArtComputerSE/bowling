package se.crisp.bowling.world.bowling;

import se.crisp.bowling.Frame;
import se.crisp.bowling.Rules;

public class WorldBowlingRules implements Rules {

    @Override
    public int score(Frame frame) {
        if (frame.getNext() != null) {
            return value(frame) + score(frame.getNext());
        }
        return value(frame);
    }

    private int value(Frame frame) {
        if (frame.isStrike()) {
            return 30;
        }
        if (frame.isSpare()) {
            return 10 + frame.getFirst();
        }
        return frame.getFirst() + frame.getSecond();
    }
}
