package se.crisp.bowling.belungwa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import se.crisp.bowling.Frame;
import se.crisp.bowling.LastFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BelungwaParserTest {
    private static final String FRAME_END = "-";
    private static final String STRIKE = "XXXXXXXXXX";

    private BelungwaParser parser;

    @BeforeEach
    void setUp() {
        parser = new BelungwaParser();
    }

    @Test
    @DisplayName("Gutter throw in a frame")
    void gutter_throw() {
        BelungwaParser parser = new BelungwaParser();

        Frame actual = parser.parse(makeString(roll(0), roll(0), FRAME_END));

        assertEquals(0, actual.getFirst());
        assertEquals(0, actual.getSecond());
    }

    @Test
    @DisplayName("Strike in the first throw")
    void strike() {
        BelungwaParser parser = new BelungwaParser();

        Frame actual = parser.parse(makeString(roll(10), FRAME_END));

        assertEquals(10, actual.getFirst());
        assertTrue(actual.isStrike());
    }

    @Test
    @DisplayName("A strike followed by a single and five")
    void strike_followed_by_single_and_then_five() {

        Frame actual = parser.parse(makeString(
                STRIKE, FRAME_END,
                roll(1), roll2nd(1, 6), FRAME_END
        ));

        assertTrue(actual.isStrike());
        assertEquals(1, actual.getNext().getFirst());
    }

    @Test
    void spare_and_then_3_and_4() {
        Frame actual = parser.parse(makeString(
                roll(9), STRIKE, FRAME_END,
                roll(3), roll2nd(3, 4), FRAME_END
        ));
        assertTrue(actual.isSpare());
        assertEquals(3, actual.getNext().getFirst());
    }

    @Test
    void last_frame_logic() {
        String gutter9 = "IIIIIIIIII\nIIIIIIIIII\n-\n".repeat(9);
        String last = "XXXXXXXXXX\nXXXXXXXXXX\nXXXXXXXXXX\n-";
        String input = "31\n" + gutter9 + last;

        Frame result = parser.parse(input);
        Frame actual = getLast(result);

        assertTrue(actual instanceof LastFrame);
        LastFrame lastFrame = (LastFrame) actual;
        assertEquals(10, lastFrame.getFirst());
        assertEquals(10, lastFrame.getSecond());
        assertEquals(10, lastFrame.getThird());
    }


    @SuppressWarnings("WeakerAccess")
    @ParameterizedTest(name = "{index} : frame = \"{0}\", expected = {1} ")
    @DisplayName("Single frame")
    @MethodSource("singleFrameCasesNoSpareNoStrike")
    public void single_frame(String frame, int expected) {
        Frame actual = parser.parse(frame);

        assertEquals(expected, actual.getFirst() + actual.getSecond());
    }

    private static Stream<Arguments> singleFrameCasesNoSpareNoStrike() {
        List<Arguments> arguments = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                int sum = x + y;
                if (sum < 10) {
                    String frame = makeString(roll(x), roll2nd(x, y), FRAME_END);
                    arguments.add(Arguments.arguments(frame, sum));
                }
            }
        }
        return arguments.stream();
    }

    private Frame getLast(Frame frame) {
        if (frame.getNext() == null) {
            return frame;
        }
        return getLast(frame.getNext());
    }

    private static String roll(int i) {
        return "X".repeat(i) + "I".repeat(10 - i);
    }

    private static String roll2nd(int first, int n) {
        return roll(first + n);
    }

    private static String makeString(String... strings) {
        String joined = String.join("\n", strings);
        return strings.length + "\n" + joined;
    }
}