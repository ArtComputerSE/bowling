package se.crisp.bowling;

public class BowlingScorer {

    public int score(String pins) {
        int len = pins.length();
        int sum = 0;
        for (int i = 0; i <  len; i++) {

            if(pins.charAt(i) == '-') {
                sum += 0;
            }
            else {
                sum += Character.getNumericValue(pins.charAt(i));
            }
        }
        return sum;
    }

}
