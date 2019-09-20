package se.crisp.bowling;

import java.util.Optional;

public class LastFrame extends Frame {

    private final int third;

    public LastFrame(int first, int second, int third) {
        super(first, second, null);
        this.third = third;
    }

    public int getThird() {
        return third;
    }

    @Override
    public int value() {
        return first + second + third;
    }

    @Override
    protected Optional<Integer> comingTwoBalls() {
        return Optional.of(first + second);
    }
}
