package se.crisp.bowling;

class LastFrame extends Frame {

    private final char third;

    LastFrame(char first, char second, char third) {
        super(first, second, null);
        this.third = third;
    }

    @Override
    public int value() {
        if (first == STRIKE) {
            if (second == STRIKE) {
                if (third == STRIKE) {
                    int last = 10;
                    int secondLast = 20;
                    int thirdLast = 30;
                    return thirdLast + secondLast + last;
                }
            }
        }
        if (second == SPARE) {
            return 10 + parse(third);
        }
        return sumBoth() + parse(third);
    }

    @Override
    public int score() {
        return super.score();
    }
}
