package se.crisp.bowling.belungwa;

import org.junit.jupiter.api.Test;
import se.crisp.bowling.Frame;

import static org.junit.jupiter.api.Assertions.*;

class BelungwaParserTest {

    @Test
    void gutter_throw() {
        BelungwaParser parser = new BelungwaParser();

        Frame actual = parser.parse("3\nIIIIIIIIII\nIIIIIIIIII\n-\n");

        assertEquals(0, actual.getFirst());
        assertEquals(0, actual.getSecond());
    }

    @Test
    void strike() {
        BelungwaParser parser = new BelungwaParser();

        Frame actual = parser.parse("2\nXXXXXXXXXX\n-\n");

        assertEquals(10, actual.getFirst());
        assertTrue(actual.isStrike());
    }
}