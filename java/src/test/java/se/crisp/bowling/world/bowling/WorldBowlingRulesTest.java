package se.crisp.bowling.world.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.crisp.bowling.BowlingScorer;
import se.crisp.bowling.Frame;
import se.crisp.bowling.LastFrame;
import se.crisp.bowling.Parser;

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

    private static final String FAKE = "fake";

    private BowlingScorer bowlingScorer;
    private TestParser mockedParser = new TestParser();

    @BeforeEach
    void setUp() {
        bowlingScorer = new BowlingScorer(mockedParser, new WorldBowlingRules());
    }

    @Test
    @DisplayName("A gutter first throw is no points.")
    void gutter_game() {
        mockedParser.setFrame(new Frame(0, 0, null));

        int actual = bowlingScorer.score(FAKE);

        assertEquals(0, actual);
    }

    @Test
    @DisplayName("A strike in first frame scores 30.")
    void strike_in_first_frame() {
        mockedParser.setFrame(new Frame(10, 0, null));

        int actual = bowlingScorer.score(FAKE);

        assertEquals(30, actual);
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