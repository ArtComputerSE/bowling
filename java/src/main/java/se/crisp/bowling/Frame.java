package se.crisp.bowling;

import java.util.Optional;

class Frame {

    private Frame next;
    private int first;
    private int second;

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    Frame(int first, int second, Frame next) {
        this.first = first;
        this.second = second;
        this.next = next;
    }

    public int value() {
        if (isSpare()) {
            return nextBall().map(value -> 10 + value).orElse(0);
        }
        if (isStrike()) {
            return nextTwoBalls().map(value -> 10 + value).orElse(0);
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
        }
        if (next.isStrike()) {
            if (next.next == null) {
                return Optional.empty();
            }
            if (next.next.isStrike()) {
                return Optional.of(10 + 10);
            }
            return Optional.of(10 + next.next.first);
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

    int sumBoth() {
        return first + second;
    }

    private boolean isSpare() {
        return first < 10 && first + second == 10;
    }

    private boolean isStrike() {
        return first == 10;
    }
}
