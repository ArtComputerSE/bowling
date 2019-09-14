package se.crisp.bowling;

import java.util.Optional;

@SuppressWarnings("WeakerAccess")
public class BowlingScorer {

    private static final char STRIKE = 'X';
    public static final char SPARE = '/';

    class Frame {
        Frame next;
        char first;
        char second;

        public Frame(char first, char second, Frame next) {
            this.first = first;
            this.second = second;
            this.next = next;
        }

        public int value() {
            if (isSpare()) {
                Optional<Integer> nextBall = nextBall();
                if (nextBall.isPresent()) {
                    return 10 + nextBall.get();
                } else {
                    return 0;
                }
            }
            if (isStrike()) {
                Optional<Integer> nextTwoBalls = nextTwoBalls();
                if (nextTwoBalls.isPresent()) {
                    return 10 + nextTwoBalls.get();
                } else {
                    return 0;
                }
            }
            return sumBoth();
        }

        private Optional<Integer> nextBall() {
            if (next == null) {
                return Optional.empty();
            }
            if (next.isStrike()) {
                return Optional.of(10);
            }
            return Optional.of(parse(next.first));
        }

        private Optional<Integer> nextTwoBalls() {
            if (next == null) {
                return Optional.empty();
            }
            if (next.isStrike()) {
                if (next.next == null) {
                    return Optional.empty();
                }
                if (next.next.isStrike()) {
                    return Optional.of(10 + 10);
                }
                return Optional.of(10 + parse(next.next.first));
            }
            if (next.isSpare()) {
                return Optional.of(10);
            }
            return Optional.of(next.value());
        }


        public int score() {
            if (next != null) {
                return value() + next.score();
            }
            return value();
        }

        public int sumBoth() {
            return parse(first) + parse(second);
        }

        private boolean isSpare() {
            return second == SPARE;
        }

        private boolean isStrike() {
            return first == STRIKE;
        }
    }


    public int score(String pins) {
        return createFrames(pins).score();
    }

    private Frame createFrames(String pins) {
        return createFrames(pins, 0);
    }

    private Frame createFrames(String pins, int start) {
        if (start >= pins.length() - 1) {
            return null;
        }
        return new Frame(
                pins.charAt(start),
                pins.charAt(start + 1),
                createFrames(pins, start + 2));
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
