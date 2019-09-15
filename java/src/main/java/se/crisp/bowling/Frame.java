package se.crisp.bowling;

import java.util.Optional;

class Frame {

    private static final char SPARE = '/';

    private Frame next;
    int first;
    int second;

    Frame(char first, char second, Frame next) {
        this.first = parse(first);
        if (second == SPARE) {
            this.second = 10 - this.first;
        } else {
            this.second = parse(second);
        }
        this.next = next;
    }

    public int value() {
        if (isStrike()) {
            return nextTwoBalls().map(value -> 10 + value).orElse(0);
        }
        if (isSpare()) {
            return nextBall().map(value -> 10 + value).orElse(0);
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
        return Optional.of(next.first);
    }

    private Optional<Integer> nextTwoBalls() {
        if (next == null) {
            return Optional.empty();
        } else {
            return next.comingTwoBalls();
        }

    }

    protected Optional<Integer> comingTwoBalls() {
        if (this.isStrike()) {
            if (next == null) {
                return Optional.empty();
            }
            return Optional.of(10 + next.first);
        }
        return Optional.of(first + second);
    }

    int score() {
        if (next != null) {
            return value() + next.score();
        }
        return value();
    }

    private int sumBoth() {
        return first + second;
    }

    private boolean isSpare() {
        return first + second >= 10;
    }

    private boolean isStrike() {
        return first == 10;
    }

    static int parse(int i) {
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
            case 'X':
                return 10;
        }
        return 0;
    }
}
