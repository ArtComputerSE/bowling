package se.crisp.bowling;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ParserImpl implements ParserIfc {

    @Override
    public Frame parse(String input)
    {
        return createFrames(input, 0);
    }

    private Frame createFrames(String pins, int start) {
        if (start >= pins.length() - 1) {
            return null;
        }
        int first = parse(pins.charAt(start));
        char secondChar = pins.charAt(start + 1);
        int second = secondChar == '/' ? 10 - first : parse(secondChar);

        if (pins.length() == 21 && start == 18) {
            int third = parse(pins.charAt(start + 2));

            return new LastFrame(first, second, third);
        }
        return new Frame(
                first, second,
                createFrames(pins, start + 2));
    }

    private static int parse(int i) {
        char c = (char) i;
        if(c == 'X') {
            return 10;
        } else if (c == ' ' || c == '-') {
            return 0;
        } else if (Character.isDigit(c)) {
            return c - '0';
        }
        throw new IllegalArgumentException("Invalid character found in input: '" + c + "'.");
    }

}
