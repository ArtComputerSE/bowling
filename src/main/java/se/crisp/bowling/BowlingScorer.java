package se.crisp.bowling;

class BowlingScorer {

    int score(String pins) {

        CheckForIllegalCharacters(pins);
        
        if (pins.contains("1")) {
            return 1;
        }
        return 0;
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



class StringUtil
{
    // Iterate over characters of a String
    public static void main (String[] args)
    {
        String s = "Techie Delight";

        String[] arr = s.split("");

        for (String ch : arr) {
            System.out.print(ch);
        }
    }
}