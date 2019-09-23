package se.crisp.bowling.traditional;

import se.crisp.bowling.Frame;
import se.crisp.bowling.LastFrame;
import se.crisp.bowling.Rules;

public class TraditionalRules implements Rules {
    @Override
    public int score(Frame frame) {
        if (frame.getNext() != null) {
            return value(frame) + score(frame.getNext());
        }
        if (frame instanceof LastFrame) {
            LastFrame lastFrame = (LastFrame) frame;
            return lastFrame.getFirst() + lastFrame.getSecond() + lastFrame.getThird();
        }
        return value(frame);
    }

    private int value(Frame frame) {
        if (frame.isStrike()) {
            return frame.nextTwoBalls().map(value -> 10 + value).orElse(0);
        }
        if (frame.isSpare()) {
            return frame.nextBall().map(value -> 10 + value).orElse(0);
        }
        return frame.sumBoth();
    }
}
