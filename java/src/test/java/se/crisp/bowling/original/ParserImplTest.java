package se.crisp.bowling.original;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserImplTest {

    private static final String SOME_ILLEGAL_CHARACTERS = "xxx";

    @Test
    void parser_throws_exception_for_illegal_characters() {
        assertThrows(IllegalArgumentException.class, () -> new ParserImpl().parse(SOME_ILLEGAL_CHARACTERS));
    }
}