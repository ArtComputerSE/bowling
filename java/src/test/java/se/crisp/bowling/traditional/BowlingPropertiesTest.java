package se.crisp.bowling.traditional;

import net.jqwik.api.*;
import org.assertj.core.api.Assertions;
import se.crisp.bowling.BowlingScorer;
import se.crisp.bowling.Frame;
import se.crisp.bowling.Parser;

class BowlingPropertiesTest {
    private static final String FAKE = "fake";
    private TestParser mockedParser = new TestParser();
    private BowlingScorer bowlingScorer = new BowlingScorer(mockedParser, new TraditionalRules());


    @Property
    @Label("Single frame property test")
    void single_frame(@ForAll("frameValue") int left, @ForAll("frameValue") int right) {
        mockedParser.setFrame(new Frame(left, right, null));
        int score = bowlingScorer.score(FAKE);

        int sum = left + right;
        if (sum < 10) {
            Assertions.assertThat(score).isEqualTo(sum);
        } else {
            Assertions.assertThat(score).isEqualTo(0);
        }
    }

    @Provide
    Arbitrary<Integer> frameValue() {
        return Arbitraries.integers().between(0, 9);
    }

    @Property
    @Label("Score will always be between zero and 300")
    void multi_frame(@ForAll("multipleFrames") String rolls) {
        mockedParser.setFrame(convertStringToFrames(rolls));

        int score = bowlingScorer.score(FAKE);

        Assertions.assertThat(score)
                .isGreaterThanOrEqualTo(0)
                .isLessThanOrEqualTo(300);
    }

    private Frame convertStringToFrames(String rolls) {
        return convertStringToFrames(rolls, 0);
    }

    private Frame convertStringToFrames(String rolls, int start) {
        if (start + 1 >= rolls.length()) {
            return null;
        }
        int left = value(rolls.charAt(start++));
        char n = rolls.charAt(start++);
        int right = n == '/' ? 10 - left : value(n);
        return new Frame(left, right, convertStringToFrames(rolls, start));
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
            if (s.charAt(n) == 'X') {
                if (n % 2 != 0) {
                    return false;
                }
                if (n + 1 < s.length() && s.charAt(n + 1) != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private int value(Character c) {
        if (c.equals('-') || c.equals(' ')) {
            return 0;
        }
        return c - '0';
    }

    private static class TestParser implements Parser {
        private Frame frame;

        @Override
        public Frame parse(String pins) {
            return this.frame;
        }

        void setFrame(Frame frame) {
            this.frame = frame;
        }
    }
}