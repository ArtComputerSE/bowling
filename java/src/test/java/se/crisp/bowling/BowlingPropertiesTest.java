package se.crisp.bowling;

import net.jqwik.api.*;
import org.assertj.core.api.Assertions;

class BowlingPropertiesTest {

    @Property
    @Label("Single frame property test")
    void single_frame(@ForAll("frameValue") String left, @ForAll("frameValue") String right) {
        BowlingScorer bowlingScorer = createScorer();

        int expect;
        int sum = value(left) + value(right);
        int score;
        if(sum == 10) {
            score = bowlingScorer.score(left + "/");
            expect = 0;
        }
        else {
            score = bowlingScorer.score(left + right);
            expect = sum;
        }
        Assertions.assertThat(score).isEqualTo(expect);
    }

    @Provide
    Arbitrary<String> frameValue() {
        return Arbitraries.strings()
                .withChars('-', '1', '2', '3', '4', '5', '6', '7', '8', '9')
                .ofLength(1);
    }

    @Property
    @Label("Score will always be between zero and 300")
    void multi_frame(@ForAll("multipleFrames") String rolls) {
        BowlingScorer bowlingScorer = createScorer();

        int score = bowlingScorer.score(rolls);

        Assertions.assertThat(score)
                .isGreaterThanOrEqualTo(0)
                .isLessThanOrEqualTo(300);
    }


    @Provide
    Arbitrary<String> multipleFrames() {
        return Arbitraries.strings()
                .withChars('-', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'X', ' ', '/')
                .ofMinLength(2)
                .ofMaxLength(21)
                .filter(this::sparesAndSpacesRightAndStrikesLeft);
    }

    private boolean sparesAndSpacesRightAndStrikesLeft(String s) {
        for (int n = 0; n < s.length(); n++) {
            if (s.charAt(n) == '/' || s.charAt(n) == ' ') {
                if (n % 2 == 0)
                    return false;
            }
            if (s.charAt(n) == 'X'){
                if (n % 2 != 0) {
                    return false;
                }
                if (n +1 < s.length() && s.charAt(n + 1) != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private int value(String c) {
        if (c.equals("-")) {
            return 0;
        }
        return c.toCharArray()[0] - '0';
    }

    private BowlingScorer createScorer() {
        return new BowlingScorer(new ParserImpl());
    }
}