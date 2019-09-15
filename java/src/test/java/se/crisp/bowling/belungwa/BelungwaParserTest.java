package se.crisp.bowling.belungwa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.crisp.bowling.Frame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BelungwaParserTest {

    private BelungwaParser parser;

    @BeforeEach
    void setUp() {
        parser = new BelungwaParser();
    }

    @Test
    @DisplayName("Gutter throw in a frame")
    void gutter_throw() {
        BelungwaParser parser = new BelungwaParser();

        Frame actual = parser.parse(makeString("IIIIIIIIII", "IIIIIIIIII", "-"));

        assertEquals(0, actual.getFirst());
        assertEquals(0, actual.getSecond());
    }

    @Test
    @DisplayName("Strike in the first throw")
    void strike() {
        BelungwaParser parser = new BelungwaParser();

        Frame actual = parser.parse(makeString("XXXXXXXXXX", "-"));

        assertEquals(10, actual.getFirst());
        assertTrue(actual.isStrike());
    }

    @Test
    @DisplayName("A strike followed by a single and five")
    void strike_followed_by_single_and_then_five() {

        Frame actual = parser.parse(makeString(
                "XXXXXXXXXX",
                "-",
                "IIXIIIIIII",
                "IIXIXXXXXI",
                "-"));

        assertTrue(actual.isStrike());
        assertEquals(1, actual.getNext().getFirst());
    }

    @Test
    void spare_and_then_3_and_4() {
        Frame actual = parser.parse(makeString(
                "XXXXXXXXXI",
                "XXXXXXXXXX",
                "-",
                "XXXIIIIIII",
                "XXXXXXXIII",
                "-"));
        assertTrue(actual.isSpare());
        assertEquals(3, actual.getNext().getFirst());
    }

    private String makeString(String... strings) {
        String joined = String.join("\n", strings);
        return strings.length + "\n" + joined;
    }
}