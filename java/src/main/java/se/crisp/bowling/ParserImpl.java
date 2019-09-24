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
        if (pins.length() == 21 && start == 18) {
            return new LastFrame(pins.charAt(start),
                    pins.charAt(start + 1),
                    pins.charAt(start + 2));
        }
        if (start >= pins.length() - 1) {
            return null;
        }
        return new Frame(
                pins.charAt(start),
                pins.charAt(start + 1),
                createFrames(pins, start + 2));
    }
}
