package se.crisp.bowling;

import java.util.Optional;

public class Frame {

    private Frame next;
    int first;
    int second;

    public Frame(int first, int second, Frame next) {
        this.first = first;
        this.second = second;
        this.next = next;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public Frame getNext() {
        return next;
    }

    public void setNext(Frame next) {
        this.next = next;
    }


    public Optional<Integer> nextBall() {
        if (next == null) {
            return Optional.empty();
        }
        if (next.isStrike()) {
            return Optional.of(10);
        }
        return Optional.of(next.first);
    }

    public Optional<Integer> nextTwoBalls() {
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

    public int sumBoth() {
        return first + second;
    }

    public boolean isSpare() {
        return first + second >= 10;
    }

    public boolean isStrike() {
        return first == 10;
    }

}
