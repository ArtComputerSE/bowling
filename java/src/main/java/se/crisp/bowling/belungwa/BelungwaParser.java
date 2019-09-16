package se.crisp.bowling.belungwa;

import se.crisp.bowling.Frame;
import se.crisp.bowling.LastFrame;
import se.crisp.bowling.Parser;

import java.util.List;
import java.util.stream.Collectors;

public class BelungwaParser implements Parser {

    private static final String FRAME_END = "-";

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
        if(lastFrame(lines, start)) {
            return createLastFrame(lines, start, first);
        }
        if(atFrameEnd(lines, start)){
            return new Frame(first, 0, createFrames(lines, start + 1));
        }
        int second = getKnockedDownInSecond(lines, start, first);
        return new Frame(first, second, createFrames(lines, start + 2));
    }

    private int getKnockedDownInSecond(List<String> lines, int start, int first) {
        return lineValue(lines.get(start)) - first;
    }

    private boolean lastFrame(List<String> lines, int start) {
        return start + 2 < lines.size() && atFrameEnd(lines, start + 2);
    }

    private boolean atFrameEnd(List<String> lines, int start) {
        return lines.get(start).equals(FRAME_END);
    }

    private Frame createLastFrame(List<String> lines, int start, int first) {
        int second = lineValue(lines.get(start++));
        int third = lineValue(lines.get(start));
        return new LastFrame(first, second, third);
    }

    private int lineValue(String line) {
        return ((Long) line.chars().filter(x -> x == 'X').count()).intValue();
    }
}
