package se.crisp.bowling;

//import com.sun.org.apache.xpath.internal.Arg;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("WeakerAccess")
public class BowlingScorerTest {

    @Test
    @DisplayName("All misses, no points")
    public void when_frame_is_all_miss_then_no_points() {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertEquals(bowlingScorer.score("00"), 0);
    }

    @ParameterizedTest
    @DisplayName("Single frame")
    @MethodSource("singleFrameCases")
    public void single_frame(String pins, int expected) {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertEquals(expected, bowlingScorer.score(pins));
    }

    private static Stream<Arguments> singleFrameCases(){
        return Stream.of(
                Arguments.arguments("-1", 1),
                Arguments.arguments("1-", 1),
                Arguments.arguments("22", 4),
                Arguments.arguments("X ", 0),
                Arguments.arguments("3/", 0)
        );
    }

    @ParameterizedTest
    @DisplayName("Multi frame")
    @MethodSource("multiFrameCases")
    public void multi_frame(String pins, int expected) {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertEquals(expected, bowlingScorer.score(pins));
    }

    private static Stream<Arguments> multiFrameCases(){
        return Stream.of(
                Arguments.arguments("-1-323", 9),
                Arguments.arguments("X X X", 30),
                Arguments.arguments("3/3/3-", 29),
                Arguments.arguments("X 2", 0),
                Arguments.arguments("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5 ", 150),
                Arguments.arguments("XXXXXXXXXX53", 283),
                Arguments.arguments("3/2", 12)
        );
    }

    @ParameterizedTest
    @DisplayName("Invalid frame")
    @MethodSource("invalidFrameCases")
    public void invalid_frame(String pins, int expected) {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertThrows(IllegalArgumentException.class, () ->bowlingScorer.score(pins));
    }

    private static Stream<Arguments> invalidFrameCases(){
        return Stream.of(
                Arguments.arguments("//", 9),
                Arguments.arguments("X/", 0),
                Arguments.arguments("QJWDKJASHDJKHAKDc,ansjdn921381203", 0)
        );
    }
}
