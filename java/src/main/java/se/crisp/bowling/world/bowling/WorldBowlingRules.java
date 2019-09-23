package se.crisp.bowling.world.bowling;

import se.crisp.bowling.Frame;
import se.crisp.bowling.Rules;

public class WorldBowlingRules implements Rules {

    @Override
    public int score(Frame frame) {
        if (frame.isStrike()) {
            return 30;
        }
        return 0;
    }
}
