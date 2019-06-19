package se.crisp.bowling;

public class BowlingScorer {


    public int score(String spacedPins) {
        String pins = spacedPins.replaceAll("\\s+","");
        System.out.println(pins);
        return  rawScore(pins)
                + strikeScore(pins)
                + spareScore(pins);
    }

    private int rawScore(String pins){
        return pins.chars().map(x -> rollValue((char)x)).sum();
    }

    private int strikeScore(String pins){
        int score = 0;
        int rollId = 0;
        for(int i = 0; i < pins.length() && rollId < 18; ++i){
            if(pins.charAt(i) == 'X'){
                if(i+1 < pins.length())
                    score += rollValue(pins.charAt(i+1));
                if(i+2 < pins.length())
                    score += rollValue(pins.charAt(i+2));
                rollId += 2;
            } else {
                rollId++;
            }
        }
        return score;
    }

    private int spareScore(String pins){
        int score = 0;
        int rollId = 0;
        for(int i = 0; i < pins.length(); ++i){
            if(pins.charAt(i) == '/' && i+1 < pins.length()){
                score += 10 - rollValue(pins.charAt(i-1));
                if(rollId < 18) {
                    score += rollValue(pins.charAt(i+1));
                }
            }
            rollId++;
            if(pins.charAt(i) == 'X') rollId++;
        }
        return score;
    }

    private static int rollValue(char rollSymbol) {
        if('0' <= rollSymbol && rollSymbol <= '9')
            return rollSymbol - '0';
        else if(rollSymbol == 'X')
            return 10;
        else if(rollSymbol == '/')
            return 0;
        else if(rollSymbol == '-')
            return 0;
        throw new IllegalArgumentException("A roll symbol received was not a number,'-','X' or '/'.");
    }

}
