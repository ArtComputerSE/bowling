package se.crisp.bowling;

class BowlingScorer {

    /**
     * Calculate the current score in a bowling game, excluding unfinished frames
     * @param pins  Sequence describing number of pins knocked down in each delivery
     * @return      Score
     */
    int score(String pins) {
        int sum = 0;
        int framesPlayed = 0;

        // Remove ambiguous spaces (can be both separator and no roll), replace "-" with zeros
        pins = pins.replaceAll("\\s+","");
        pins = pins.replaceAll("-", "0");

        while (pins.length() > 0) {
            // Check that the first character of the sequence is valid
            if (!"0123456789X".contains(pins.substring(0,1)) ) {        // spare not possible on first delivery in frame
                throw new IllegalArgumentException();
            }

            if (pins.charAt(0) == 'X') {
                if (pins.length() >= 3) {
                    sum += (simpleScore(pins.substring(1, 3)) + 10);       // strike = bonus pins + 10
                }
                pins = pins.substring(1);

            } else {
                if (pins.length() == 1) {
                    break;                                              // unfinished half frame is not worth points
                } else if (pins.charAt(1) == '/') {
                    if (pins.length() >= 3) {
                        sum += (simpleScore(pins.substring(2, 3)) + 10);   // spare = bonus pins + 10
                    }
                } else {
                    sum += simpleScore(pins.substring(0, 2));
                }
                pins = pins.substring(2);
            }

            if (++framesPlayed >= 10) { break; }
        }
        return sum;
    }

    /**
     * Calculate the score of frames without bonus pins, ie the total number of pins knocked down
     * @param frame Sequence describing number of pins knocked down in each delivery
     * @return      Score of frames without bonus pins
     */
    private int simpleScore(String frame) {
        int total = 0;

        for (int i = 0; i < frame.length(); i++) {

            if( Character.isDigit(frame.charAt(i)) ){
                total = total + Character.getNumericValue(frame.charAt(i));
            } else if (frame.charAt(i) == 'X') {
                total += 10;
            } else if (frame.charAt(i) == '/') {
                return 10;                                              // score is always 10 with a spare
            } else {
                throw new IllegalArgumentException();
            }

        }
        return total;
    }

}
