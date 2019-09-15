package se.crisp.bowling;

import java.util.Optional;

class LastFrame extends Frame {

    private final int third;

    LastFrame(char first, char second, char third) {
        super(first, second, null);
        this.third = parse(third);
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
