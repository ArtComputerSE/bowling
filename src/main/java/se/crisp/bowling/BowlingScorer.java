package se.crisp.bowling;

public class BowlingScorer {

    public int score(String pins) {
        int sum = 0;
        int framesPlayed = 0;

        pins = pins.replaceAll("\\s+","");
        pins = pins.replaceAll("-", "0");

        while (pins.length() > 0) {
            // System.out.println(pins); // for debug
            checkIfFrameIsValid(pins);

            if (pins.charAt(0) == 'X') {

                if (pins.length() >= 3) {
                    if (pins.charAt(2) == '/') {
                        sum += 20;
                    } else {
                        sum += (rawScore(pins.substring(1, 3)) + 10);
                    }
                }

                pins = pins.substring(1);

            } else {

                if (pins.length() == 1) {
                    // unfinished half frame is not worth points
                    break;
                } else if (pins.charAt(1) == '/') {
                    if (pins.length() >= 3) {
                        sum += (rawScore(pins.substring(2,3)) + 10);
                    }
                } else {
                    sum += rawScore(pins.substring(0, 2));
                }

                pins = pins.substring(2);
            }

            if (++framesPlayed >= 10) {break;}
        }



        return sum;
    }

    private int rawScore(String subString) {
        int total = 0;

        for (int i = 0; i < subString.length(); i++) {

            if( Character.isDigit(subString.charAt(i)) ){
                total = total + Character.getNumericValue(subString.charAt(i));
            } else if (subString.charAt(i) == 'X') {
                total += 10;
            }

        }
        return total;

    }

    private void checkIfFrameIsValid(String input) {
        if (input.length() >= 1 && !"0123456789X".contains(input.substring(0,1)) ) {
            throw new IllegalArgumentException();
        }
    }

}
