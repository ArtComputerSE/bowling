package se.crisp.bowling;

class LastFrame extends Frame {

    private final int third;

    LastFrame(int first, int second, int third) {
        super(first, second, null);
        this.third = third;
    }

    @Override
    public int value() {
        if (getFirst() == 10) {
            if (getSecond() == 10) {
                if (third == 10) {
                    int last = 10;
                    int secondLast = 20;
                    int thirdLast = 30;
                    return thirdLast + secondLast + last;
                }
            }
        }
        return sumBoth() + third;
    }

    @Override
    public int score() {
        return super.score();
    }
}
