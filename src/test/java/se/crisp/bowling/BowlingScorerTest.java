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

    @ParameterizedTest
    @DisplayName("Single frame")
    @MethodSource("singleFrameCases")
    public void single_frame(String pins, int expected) {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertEquals(expected, bowlingScorer.score(pins));
    }

    private static Stream<Arguments> singleFrameCases(){
        Stream<Arguments> allCases;
        allCases = Stream.concat(dashFrames(), spareFrames());
        return allCases;
    }

    private static Stream<Arguments> dashFrames() {
        List<Arguments> frameCases = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            frameCases.add(Arguments.arguments("-"+(char)(i+'0'), i));
            frameCases.add(Arguments.arguments((char)(i+'0')+"-", i));
        }
        return frameCases.stream();
    }

    private static Stream<Arguments> spareFrames() {
        List<Arguments> frameCases = new ArrayList<>();
        frameCases.add(Arguments.arguments('/', 10));
        for (int i = 1; i < 10; ++i) {
            frameCases.add(Arguments.arguments((char)(i+'0')+"/", 10));
        }
        return frameCases.stream();
    }
}
