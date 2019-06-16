package se.crisp.bowling;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class BowlingScorer {

    private static final char STRIKE = 'X';
    public static final char SPARE = '/';

    class Frame {
        char first, second;

        public int sumBoth() {
            return parse(first) + parse(second);
        }
    }


    public int score(String pins) {
        List<Frame> frames = createFrames(pins);
        int result = 0;
        for (int n = 0; n < frames.size(); n++) {
            if (isSpare(frames.get(n))) {
                if (n + 1 < frames.size()) {
                    Frame next = frames.get(n + 1);
                    result += 10 + parse(next.first);
                }
            } else if (isStrike(frames.get(n))) {
                if (n + 1 < frames.size()) {
                    Frame next = frames.get(n + 1);
                    result += 10 + next.sumBoth();
                }
            } else {
                result += frames.get(n).sumBoth();
            }
        }
        return result;
    }

    private boolean isSpare(Frame frame) {
        return frame.second == SPARE;
    }

    private boolean isStrike(Frame frame) {
        return frame.first == STRIKE;
    }

    private List<Frame> createFrames(String pins) {
        List<Frame> result = new ArrayList<>();
        for (int n = 0; n < pins.length(); n += 2) {
            Frame frame = new Frame();
            frame.first = pins.charAt(n);
            if (n + 1 < pins.length()) {
                frame.second = pins.charAt(n + 1);
            }
            result.add(frame);
        }
        return result;
    }

    private int parse(int i) {
        char c = (char) i;
        switch (c) {
            case '-':
                return 0;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return c - '0';
        }
        return 0;
    }

}
