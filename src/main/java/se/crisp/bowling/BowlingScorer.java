package se.crisp.bowling;

class BowlingScorer {

    String legalDigits = "123456789";
    String legalCharacters = legalDigits + " -/X";

    int score(String pins) {

        CheckForIllegalCharacters(pins);

        int score = 0;

        String[] characters = pins.split("");

        for (String ch : characters) {
            if (legalDigits.contains(ch)) score += Integer.parseInt(ch);
        }


        return score;
    }

    private void CheckForIllegalCharacters(String pins) {

        String[] characters = pins.split("");

        for (String ch : characters) {
            if (! legalCharacters.contains(ch)) {
                throw new IllegalArgumentException("Illegal character");
            }
        }
    }

}
