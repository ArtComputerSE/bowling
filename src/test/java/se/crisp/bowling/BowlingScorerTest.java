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
        String input = "X 12";
        int expected = 10 + 1 + 2 + 1 + 2;

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
