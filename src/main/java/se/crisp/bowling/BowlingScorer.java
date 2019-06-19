package se.crisp.bowling;

class BowlingScorer {

    int score(String pins) {

        CheckForIllegalCharacters(pins);

        int score = 0;

        String[] characters = pins.split("");

        for (String ch : characters) {
            if (ch.equals("1")) score += 1;
            if (ch.equals("2")) score += 2;
            if (ch.equals("3")) score += 3;
            if (ch.equals("4")) score += 4;
            if (ch.equals("5")) score += 5;
            if (ch.equals("6")) score += 6;
            if (ch.equals("7")) score += 7;
            if (ch.equals("8")) score += 8;
            if (ch.equals("9")) score += 9;
        }


        return score;
    }

    private void CheckForIllegalCharacters(String pins) {
        String legalCharacters = new String("123456789 -/X");

        String[] characters = pins.split("");

        for (String ch : characters) {
            if (! legalCharacters.contains(ch)) {
                throw new IllegalArgumentException("Illegal character");
            }
        }
    }

}
