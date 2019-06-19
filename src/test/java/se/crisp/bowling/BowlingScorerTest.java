package se.crisp.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("WeakerAccess")
public class BowlingScorerTest {

    BowlingScorer bowlingScorer;

    @BeforeEach
    public void initBowlingScorer() {
         bowlingScorer = new BowlingScorer();
    }

    @Test
    @DisplayName("All misses, no points")
    public void when_frame_is_all_miss_then_no_points() {
        assertEquals(bowlingScorer.score("00"), 0);
    }

    @ParameterizedTest
    @DisplayName("Single frame")
    @MethodSource("singleFrameCases")
    public void single_frame(String pins, int expected) {
        assertEquals(expected, bowlingScorer.score(pins));
    }

    private static Stream<Arguments> singleFrameCases(){
        return Stream.of(
                Arguments.arguments("-1", 1),
                Arguments.arguments("1-", 1),
                Arguments.arguments("11", 2),
                Arguments.arguments("--", 0)
        );
    }

    @Test
    @DisplayName("All strikes")
    public void when_frame_is_all_strikes() {
        assertEquals(300, bowlingScorer.score("X X X X X X X X X X X X"));
    }

    @Test
    @DisplayName("All 9-")
    public void when_frame_is_all_nine_miss() {
        assertEquals(90, bowlingScorer.score("9- 9- 9- 9- 9- 9- 9- 9- 9- 9- "));
    }

    @Test
    @DisplayName("With extra last roll")
    public void when_has_extra_final_roll() {
        assertEquals(150, bowlingScorer.score("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 "));
    }
}
