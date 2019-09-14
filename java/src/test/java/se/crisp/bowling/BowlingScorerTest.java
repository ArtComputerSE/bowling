package se.crisp.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("WeakerAccess")
public class BowlingScorerTest {

    @Test
    @DisplayName("All misses, no points")
    public void when_frame_is_all_miss_then_no_points() {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertEquals(bowlingScorer.score("00"), 0);
    }

    @ParameterizedTest(name = "{index} : frame = \"{0}\", expected = {1} ")
    @DisplayName("Single frame")
    @MethodSource("singleFrameCasesNoSpareNoStrike")
    public void single_frame(String frame, int expected) {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertEquals(expected, bowlingScorer.score(frame));
    }

    @Test
    @DisplayName("Strike in first frame.")
    void strike_first_frame() {
        String input = "X 71";
        int second = 7 + 1;
        int first = 10 + second;
        int expected = first + second;

        BowlingScorer bowlingScorer = new BowlingScorer();
        int actual = bowlingScorer.score(input);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Strike in first frame, no following frame.")
    void strike_first_frame_no_follow() {
        String input = "X ";
        int expected = 0;

        BowlingScorer bowlingScorer = new BowlingScorer();
        int actual = bowlingScorer.score(input);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Spare in first frame.")
    void spare_in_first_frame() {
        String input = "6/81";
        int expected = 6 + 4 + 8 + 8 + 1;

        BowlingScorer bowlingScorer = new BowlingScorer();
        int actual = bowlingScorer.score(input);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Spare in first frame, no following frame.")
    void spare_in_first_frame_no_following_frame() {
        String input = "2/";
        int expected = 0;

        BowlingScorer bowlingScorer = new BowlingScorer();
        int actual = bowlingScorer.score(input);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Double strike and then some")
    void double_strike_and_some() {
        String input = "X X 12";
        int third = 1 + 2;
        int second = 10 + 1 + 2;
        int first = 10 + 10 + 1;
        int expected = first + second + third;

        BowlingScorer bowlingScorer = new BowlingScorer();
        int actual = bowlingScorer.score(input);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("All strikes")
    void all_strikes() {
        String input = "X X X X X X X X X XXX";
        int expected = 300;

        BowlingScorer bowlingScorer = new BowlingScorer();
        int actual = bowlingScorer.score(input);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> singleFrameCasesNoSpareNoStrike() {
        String chars = "-123456789";
        List<Arguments> arguments = new ArrayList<>();
        for (int x = 0; x < chars.length(); x++) {
            for (int y = 0; y < chars.length(); y++) {
                int sum = x + y;
                String frame = "" + chars.charAt(x) + chars.charAt(y);
                arguments.add(Arguments.arguments(frame, sum));
            }
        }
        return arguments.stream();
    }
}
