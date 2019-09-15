package se.crisp.bowling.belungwa;

import se.crisp.bowling.Frame;
import se.crisp.bowling.LastFrame;
import se.crisp.bowling.Parser;

import java.util.List;
import java.util.stream.Collectors;

public class BelungwaParser implements Parser {

    @Override
    public Frame parse(String pins) {
        List<String> lines = pins.lines().collect(Collectors.toList());
        return createFrames(lines, 1);
    }

    private Frame createFrames(List<String> lines, int start) {
        if (start >= lines.size() - 1) {
            return null;
        }
        int first = lineValue(lines.get(start++));
        if(lines.get(start + 2).equals(("-"))) {
            int second = lineValue(lines.get(start++));
            int third = lineValue(lines.get(start));
            return new LastFrame(first, second, third);
        }
        int second = lines.get(start).equals("-") ?
                0 : lineValue(lines.get(start++)) - first;
        return new Frame(first, second, createFrames(lines, start + 1));
    }

    private int lineValue(String line) {
        return ((Long) line.chars().filter(x -> x == 'X').count()).intValue();
    }
}
