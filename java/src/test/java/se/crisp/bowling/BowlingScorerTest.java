package se.crisp.bowling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("WeakerAccess")
public class BowlingScorerTest {
	
    @Test
    @DisplayName("All misses, no points")
    public void when_frame_is_all_miss_then_no_points() throws ParseException {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertEquals(bowlingScorer.score("00"), 0);
    }

    @Test
    void testScorePointsOverFlow() {
      Assertions.assertThrows(ParseException.class, () -> {
    	  BowlingScorer bowlingScorer = new BowlingScorer();
    	  bowlingScorer.score("77");
      });
     
    }
    
    @ParameterizedTest
    @DisplayName("Single frame")
    @MethodSource("singleFrameCases")
    public void single_frame(String pins, int expected) throws ParseException {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertEquals(expected, bowlingScorer.score(pins));
    }

    private static Stream<Arguments> singleFrameCases(){
        return Stream.of(
                Arguments.arguments("-1", 1),
                Arguments.arguments("1-", 1),
                Arguments.arguments("11", 2),
                Arguments.arguments("1/11", 13),
                Arguments.arguments("X 23", 20),
                Arguments.arguments("X X X X X X X X X X XX", 300),
                Arguments.arguments("X 23X 54", 48),
                Arguments.arguments("1/114/43", 34),
                Arguments.arguments("4/43X 62", 47)
        );
    }
}
