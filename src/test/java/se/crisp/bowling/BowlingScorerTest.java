package se.crisp.bowling;

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
	public void when_frame_is_all_miss_then_no_points() {
		BowlingScorer bowlingScorer = new BowlingScorer();

		assertEquals(bowlingScorer.fullScore("00"), 0);
	}

	@Test
	public void test_4_5() {
		BowlingScorer bowlingScorer = new BowlingScorer();

		assertEquals(bowlingScorer.fullScore("45"), 9);
	}

	@Test
	public void test_X_58() {
		BowlingScorer bowlingScorer = new BowlingScorer();

		assertEquals(bowlingScorer.fullScore("X 58"), 10 + 5 + 8 + 5 + 8);
	}

	@Test
	public void test_X_5() {
		BowlingScorer bowlingScorer = new BowlingScorer();

		assertEquals(bowlingScorer.fullScore("X 5"), 0);
	}

	@Test
	public void test_3_spare_7() {
		BowlingScorer bowlingScorer = new BowlingScorer();

		assertEquals(bowlingScorer.fullScore("5/7"),10 + 7);
	}

	@Test
	public void test_3_spare() {
		BowlingScorer bowlingScorer = new BowlingScorer();

		assertEquals(bowlingScorer.fullScore("5/"),0);
	}

	@ParameterizedTest
	@DisplayName("Single frame")
	@MethodSource("singleFrameCases")
	public void single_frame(String pins, int expected) {
		BowlingScorer bowlingScorer = new BowlingScorer();

		assertEquals(expected, bowlingScorer.fullScore(pins));
	}

	private static Stream<Arguments> singleFrameCases() {
		return Stream.of(Arguments.arguments("-1", 1), Arguments.arguments("1-", 1));
	}
}
