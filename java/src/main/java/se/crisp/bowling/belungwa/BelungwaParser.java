package se.crisp.bowling.belungwa;

import se.crisp.bowling.Frame;
import se.crisp.bowling.Parser;

import java.util.List;
import java.util.stream.Collectors;

public class BelungwaParser implements Parser {
    @Override
    public Frame parse(String pins) {
        List<String> lines = pins.lines().collect(Collectors.toList());
        int first = lineValue(lines.get(1));
        return new Frame(first, 0, null);
    }

    private int lineValue(String line) {
        return ((Long) line.chars().filter(x -> x == 'X').count()).intValue();
    }
}
