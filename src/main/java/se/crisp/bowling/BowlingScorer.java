package se.crisp.bowling;

public class BowlingScorer {

    public int score(String pins) {
        int sum = 0;

        pins = pins.replaceAll("\\s+","");
        pins = pins.replaceAll("-", "0");

        while (pins.length() > 0) {
            System.out.println(pins);
            if (pins.charAt(0) == 'X') {
                pins = pins.substring(1);
            } else {
                if (pins.length() == 1) {
                    sum = sum + Character.getNumericValue(pins.charAt(0));
                } else if (pins.charAt(1) == '/') {
                    ;
                } else {
                    sum = sum + Character.getNumericValue(pins.charAt(0)) + Character.getNumericValue(pins.charAt(1));
                }

                pins = pins.substring(2);
            }

        }



        return sum;
    }

}
