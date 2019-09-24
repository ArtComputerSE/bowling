package se.crisp.bowling;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingScorerBelungwaTest {

    private static BowlingScorer createScorer() {
        return new BowlingScorer(new BelungwaParser());
    }

     private void assertScore(String input, int expected) {
        BowlingScorer bowlingScorer = createScorer();
        int actual = bowlingScorer.score(input);

        assertEquals(expected, actual);
    }

    @Test
    void test_empty() {
        assertScore("0",0);
    }

    @Test
    void strike_and_16() {
        String input = "5\n" +
                "XXXXXXXXXX\n" +
                "-\n"+
                "IIXIIIIIII\n" +
                "IIXIXXXXXI\n" +
                "-";
        assertScore(input,24);
    }

}
