package se.crisp.bowling;

import java.util.Optional;

class Frame {

    private Frame next;
    int first;
    int second;

    Frame(int first, int second, Frame next) {
        this.first = first;
        this.second = second;
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


}
