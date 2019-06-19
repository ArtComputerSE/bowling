package se.crisp.bowling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BowlingScorer {

    public BowlingScorer() {
        m.put('-', 0);
        for (int i = 0; i < 10; ++i) {
            m.put((char)(i+'0'), i);
        }
    }

    private Map<Character, Integer> m = new HashMap<>();

    private int sumFrame(char[] frame) {
        int sum = 0;
        for (char c:frame) {
            if (c == '/') {
                return 10;
            }
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