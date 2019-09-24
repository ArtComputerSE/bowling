package se.crisp.bowling;

import java.util.Arrays;
import java.util.stream.IntStream;

public class BelungwaParser implements ParserIfc {

    @Override
    public Frame parse(String input) {
        String[] headerAndScores = input.split("\n", 2);
        if (headerAndScores.length > 1) {
            String scores = headerAndScores[1];
            String[] pinLine = scores.replace("-", "").split("\n+");
            int[] splitScores = Arrays.stream(pinLine).mapToInt(BelungwaParser::countX).toArray();
            return parseFromIntArray(splitScores, 0);
        }
        return new Frame(0,0,null);
    }


    private Frame parseFromIntArray(int[] scores, int iscore) {
        if(iscore >= scores.length-1)
            return null;

        int value = scores[iscore];

        if(iscore == 18) {
            return new LastFrame(value, scores[iscore+1], scores[iscore+2]);
        } else {
            if(value == 10) {
                return new Frame(value, 0,
                        parseFromIntArray(scores, iscore+1));
            }
            // value != 10
            else {
                return new Frame(value, scores[iscore+1],
                        parseFromIntArray(scores,iscore+2));
            }
        }
    }

    static int countX(String pins) {
        return (int) pins.chars().filter(chr -> chr == 'X').count();
    }
}
