package se.crisp.bowling;

@SuppressWarnings("WeakerAccess")
public class BowlingScorer {

    public int score(String pins) {
        return pins.chars().map(this::parse).sum();
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
