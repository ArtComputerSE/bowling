package se.crisp.bowling.world.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.crisp.bowling.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
World Bowling scoring
The World Bowling scoring system—described as "current frame scoring"[27]—awards pins as follows:

strike: 30 (regardless of ensuing rolls' results)
spare: 10 plus pinfall on first roll of the current frame
open: total pinfall for current frame
The maximum score is 300, achieved with ten, not twelve, consecutive strikes but with no bonus pins received in the tenth frame
 */
class WorldBowlingRulesTest {

    @Test
    @DisplayName("A gutter first throw is no points.")
    void gutter_game() {
        int actual = new WorldBowlingRules().score(new Frame(0, 0, null));

        assertEquals(0, actual);
    }

    @ParameterizedTest(name = "{index} : frame = \"{0}\", expected = {1} ")
    @DisplayName("Single frame")
    @MethodSource("singleFrameCasesNoSpareNoStrike")
    void single_frame(Frame input, int expected) {
        int actual = new WorldBowlingRules().score(input);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("A strike in first frame scores 30.")
    void strike_in_first_frame() {
        int actual = new WorldBowlingRules().score(new Frame(10, 0, null));

        assertEquals(30, actual);
    }

    @Test
    @DisplayName("Spare in first frame.")
    void spare_in_first_frame() {
        int actual = new WorldBowlingRules().score(getFrames(6, 4));
        int expected = 10 + 6;

        assertEquals(actual, expected);
    }

    @Test
    @DisplayName("All strikes")
    void all_strikes() {
        int actual = new WorldBowlingRules().score(getFrames(10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0));

        assertEquals(300, actual);
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

}