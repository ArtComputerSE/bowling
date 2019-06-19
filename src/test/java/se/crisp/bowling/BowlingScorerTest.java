package se.crisp.bowling;

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
        int score = bowlingScorer.score("--------------------");
        assertEquals(0, score);
    }


    @ParameterizedTest
    @DisplayName("Only accept legal characters")
    @MethodSource("illegalCharacterCases")
    public void illegalCharacter(String pins) {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertThrows(IllegalArgumentException.class, () ->bowlingScorer.score(pins));
    }

    private static Stream<Arguments> illegalCharacterCases(){
        return Stream.of(
                Arguments.arguments("a"),
                Arguments.arguments(","),
                Arguments.arguments("&")
        );
    }

    @ParameterizedTest
    @DisplayName("Partial and full games")
    @MethodSource("gameCases")
    public void games(String pins, int expected) {
        BowlingScorer bowlingScorer = new BowlingScorer();

        assertEquals(expected, bowlingScorer.score(pins));
    }

    private static Stream<Arguments> gameCases(){
        return Stream.of(
                Arguments.arguments("-1", 1),
                Arguments.arguments("1-", 1),
                Arguments.arguments("45", 9),
                Arguments.arguments("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-", 90)
        );
    }

}
