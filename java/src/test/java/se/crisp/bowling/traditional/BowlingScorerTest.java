package se.crisp.bowling.traditional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.crisp.bowling.BowlingScorer;
import se.crisp.bowling.Frame;
import se.crisp.bowling.LastFrame;
import se.crisp.bowling.Parser;
import se.crisp.bowling.traditional.TraditionalRules;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("WeakerAccess")
public class BowlingScorerTest {

    public static final String FAKE = "fake";
    private BowlingScorer bowlingScorer;
    private TestParser mockedParser = new TestParser();

    @BeforeEach
    void setUp() {
        bowlingScorer = new BowlingScorer(mockedParser, new TraditionalRules());
    }

    @Test
    @DisplayName("All misses, no points")
    public void when_frame_is_all_miss_then_no_points() {
        Frame input = new Frame(0, 0, null);
        int expected = 0;

        assertScore(input, expected);
    }

    @ParameterizedTest(name = "{index} : frame = \"{0}\", expected = {1} ")
    @DisplayName("Single frame")
    @MethodSource("singleFrameCasesNoSpareNoStrike")
    public void single_frame(Frame input, int expected) {
        assertScore(input, expected);
    }

    @Test
    @DisplayName("Strike in first frame.")
    void strike_first_frame() {
        Frame input = getFrames(10, 0, 7, 1);
        int second = 7 + 1;
        int first = 10 + second;
        int expected = first + second;

        assertScore(input, expected);
    }

    @Test
    @DisplayName("Strike in first frame, no following frame.")
    void strike_first_frame_no_follow() {
        Frame input = new Frame(10, 0, null);
        int expected = 0;

        assertScore(input, expected);
    }

    @Test
    @DisplayName("Strike in two first frames, no following frame.")
    void strike_first_two_frames_no_follow() {
        Frame input = getFrames(10, 0, 10, 0);
        int expected = 0;

        assertScore(input, expected);
    }

    private Frame getFrames(Integer... rolls) {
        return getFrames(rolls, 0);
    }

    private Frame getFrames(Integer[] rolls, int start) {
        if (start >= rolls.length) {
            return null;
        }
        return new Frame(rolls[start], rolls[start + 1], getFrames(rolls, start + 2));

    }

    @Test
    @DisplayName("Spare in first frame.")
    void spare_in_first_frame() {
        Frame input = getFrames(6, 4, 8, 1);
        int expected = 6 + 4 + 8 + 8 + 1;

        assertScore(input, expected);
    }

    @Test
    @DisplayName("Spare in first frame, no following frame.")
    void spare_in_first_frame_no_following_frame() {
        Frame input = new Frame(2, 8, null);
        int expected = 0;

        assertScore(input, expected);
    }

    @Test
    @DisplayName("Double strike and then some")
    void double_strike_and_some() {
        Frame input = getFrames(10, 0, 10, 0, 1, 2);
        int third = 1 + 2;
        int second = 10 + 1 + 2;
        int first = 10 + 10 + 1;
        int expected = first + second + third;

        assertScore(input, expected);
    }

    @Test
    @DisplayName("Strike and spare")
    void strike_and_spare() {
        Frame inout = getFrames(10, 0, 5, 5, 3, 4);
        int first = 10 + 5 + 5;
        int second = 5 + 5 + 3;
        int third = 3 + 4;
        int expected = first + second + third;

        assertScore(inout, expected);
    }

    @Test
    @DisplayName("All strikes")
    void all_strikes() {
        Frame input = getFrames(10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0);
        LastFrame lastFrame = new LastFrame(10, 10, 10);
        appendFrame(input, lastFrame);
        int expected = 300;

        assertScore(input, expected);
    }

    @Test
    @DisplayName("Ten pairs of nine and miss")
    void ten_pairs_of_nine_and_miss() {
        Frame input = getFrames(9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0, 9, 0);
        LastFrame lastFrame = new LastFrame(9, 0, 0);
        appendFrame(input, lastFrame);
        int expected = 10 * 9;

        assertScore(input, expected);
    }

    @Test
    @DisplayName("Ten pairs of five and spare, ended with a final five,")
    void ten_pairs_of_five_and_spare_plus_final_five() {
        Frame input = getFrames(5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5);
        Frame lastFrame = new LastFrame(5, 5, 5);
        appendFrame(input, lastFrame);
        int expected = 10 * 15;

        assertScore((input), expected);
    }

    @Test
    @DisplayName("Last frame is a spare and a strike")
    void last_frame_with_spare_and_strike() {
        LastFrame input = new LastFrame(9, 1, 10);
        int expected = 9 + 1 + 10;

        assertScore((input), expected);
    }

    @Test
    @DisplayName("Last frame is two rolls")
    void last_frame_with_two_rolls() {
        Frame input = new LastFrame(3, 4, 0);
        int expected = 3 + 4;

        assertScore((input), expected);
    }

    @Test
    @DisplayName("Last frame is a strike and then two rolls.")
    void last_frame_is_strike_and_two_rolls() {
        Frame input = new LastFrame(10, 5, 5);
        int expected = 10 + 5 + 5;

        assertScore((input), expected);
    }

    @Test
    @DisplayName("Last frame is two strikes and a roll.")
    void last_frame_is_two_strikes_and_a_roll() {
        Frame input = new LastFrame(10, 10, 1);
        int expected = 10 + 10 + 1;

        assertScore((input), expected);
    }

    @Test
    @DisplayName("Last frame is three strikes.")
    void last_frame_is_three_strikes() {
        Frame input = new LastFrame(10, 10, 10);
        int expected = 10 + 10 + 10;

        assertScore((input), expected);
    }

    private void assertScore(Frame input, int expected) {
        mockedParser.setFrame(input);
        int actual = bowlingScorer.score(FAKE);

        assertEquals(expected, actual);
    }

    private void appendFrame(Frame input, Frame frame) {
        if (input.getNext() == null) {
            input.setNext(frame);
        } else {
            appendFrame(input.getNext(), frame);
        }
    }

    private static Stream<Arguments> singleFrameCasesNoSpareNoStrike() {
        List<Arguments> arguments = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                int sum = x + y;
                if (sum < 10) {
                    Frame frame = new Frame(x, y, null);
                    arguments.add(Arguments.arguments(frame, sum));
                }
            }
        }
        return arguments.stream();
    }

    private static class TestParser implements Parser {
        private Frame frame;

        @Override
        public Frame parse(String pins) {
            return this.frame;
        }

        public void setFrame(Frame frame) {
            this.frame = frame;
        }
    }
}
