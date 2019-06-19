package se.crisp.bowling;

import java.util.Objects;

public class BowlingScorer {

    public int score(String pins) {

        int score = 0;
        int i=0;
        while(i<pins.length()) {
            char c = pins.charAt(i);
            if (Character.isDigit(c)) {
                score += Character.getNumericValue(c);
            }
            else if (Objects.equals(c, '-')) {
                score += 0;
            }
            else if (Objects.equals(c, 'X')) {
                if (i>=pins.length()-4) {
                    score += 0;
                    break;
                }
            }
            else if (Objects.equals(c, '/')) {
                if (i>=pins.length()-3) {
                    score -= Character.getNumericValue(pins.charAt(i-1));
                    break;
                }
            }
            i++;
        }
        return score;
    }

}
