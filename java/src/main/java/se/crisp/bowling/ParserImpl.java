package se.crisp.bowling;

import java.util.HashMap;
import java.util.Map;

class ParserImpl implements Parser {
    private static final char SPARE = '/';

    @Override
    public Frame parse(String pins) {
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
        Integer value = characterValues.get(c);
        if (value == null) {
            throw new IllegalArgumentException("Illegal character " + c);
        }
        return value;
    }

    private static Map<Character, Integer> characterValues = initCharacterValues();

    private static Map<Character, Integer> initCharacterValues() {
        Map<Character, Integer> result = new HashMap<>();
        result.put('-', 0);
        result.put('/', 0);
        result.put(' ', 0);
        result.put('X', 10);
        result.put('1', 1);
        result.put('2', 2);
        result.put('3', 3);
        result.put('4', 4);
        result.put('5', 5);
        result.put('6', 6);
        result.put('7', 7);
        result.put('8', 8);
        result.put('9', 9);

        return result;
    }

}
