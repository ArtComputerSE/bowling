package se.crisp.bowling;

class Parser {
    private static final char SPARE = '/';

    Frame parse(String pins) {
        return createFrames(pins, 0);
    }

    private Frame createFrames(String pins, int start) {
        if (start >= pins.length() - 1) {
            return null;
        }
        int first = parse(pins.charAt(start));
        int second = parseSecondChar(first, pins.charAt(start + 1));
        if (pins.length() == 21 && start == 18) {
            return new LastFrame(
                    first,
                    second,
                    parse(pins.charAt(start + 2)));
        }
        return new Frame(
                first,
                second,
                createFrames(pins, start + 2));
    }

    private int parseSecondChar(int first, char spareMaybe) {
        if (spareMaybe == SPARE) {
            return 10 - first;
        }
        return parse(spareMaybe);
    }

     private int parse(char c) {
        switch (c) {
            case '-':
            case '/':
            case ' ':
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
            default:
                throw new IllegalArgumentException("Illegal character " + c);
        }
    }
}
