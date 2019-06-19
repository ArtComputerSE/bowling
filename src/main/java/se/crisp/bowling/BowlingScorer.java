package se.crisp.bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowlingScorer {

    public BowlingScorer() {
        m.put('1', 1);
        m.put('-', 0);
        m.put('0', 0);
    }

    private Map<Character, Integer> m = new HashMap<Character, Integer>();

    private String[] scoreChars;

    private int sumFrame(char[] frame) {
        int sum = 0;
        for (char c:frame) {
            sum += m.get(c);
        }
        return sum;
    }

    public int score(String pins) {
        char[] splitFrame = pins.toCharArray();
        return sumFrame(splitFrame);
    }

}

//String[] splitPins = str.split(" ");