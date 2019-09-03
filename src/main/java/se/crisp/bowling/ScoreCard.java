package se.crisp.bowling;

import java.util.ArrayList;
import java.util.List;

import se.crisp.bowling.BowlingFrame.Type;

public class ScoreCard {
	private String m_scoreCard;

	ScoreCard(String scoreCard) {
		m_scoreCard = scoreCard;
	}

	public List<BowlingFrame> parseScoreCard() throws ParseException {
		// String scoreCard = m_scoreCard.replaceAll("\\s","");
		char[] balls = m_scoreCard.toCharArray();
		int point;
		int ball1 = 0;
		int ball2 = 0;
		int i = 0;
		BowlingFrame.Type type = Type.NORMAL;
		BowlingFrame previousFrame = null;

		ArrayList<BowlingFrame> frames = new ArrayList<BowlingFrame>();

		for (i = 0; i < balls.length; i += 2) {
			type = Type.NORMAL;

			point = Character.getNumericValue(balls[i]);
			if (point >= 0 && point < 10) {
				ball1 = point;
			} else {
				type = decodePoint(balls[i]);

				switch (type) {
				case NORMAL:
					ball1 = 0;
					break;
				case SPARE:
					// Error
					break;
				case STRIKE:
					ball1 = 10;
					break;
				}
			}

			if (type != Type.STRIKE || i >= 20) {
				point = Character.getNumericValue(balls[i + 1]);
				if (point >= 0 && point < 10) {
					ball2 = point;
					if (ball1 + ball2 > 10) {
						throw new ParseException("Point sum of ball 1 and ball 2 cannot exceed 10");
					}
				} else {
					type = decodePoint(balls[i + 1]);

					switch (type) {
					case NORMAL:
						ball2 = 0;
						break;
					case SPARE:
						ball2 = 10 - ball1;
						break;
					case STRIKE:
						ball2 = 10;
						break;
					}
				}
			}

			BowlingFrame bowlingFrame = new BowlingFrame(type, ball1, ball2, previousFrame);
			frames.add(bowlingFrame);
			if (previousFrame != null) {
				previousFrame.addNextFrame(bowlingFrame);
			}

			previousFrame = bowlingFrame;
		}

		return frames;
	}

	private final BowlingFrame.Type decodePoint(char ball) {
		BowlingFrame.Type type = Type.NORMAL;

		switch (ball) {
		case ' ':
			type = BowlingFrame.Type.NORMAL;
			break;
		case '-':
			type = BowlingFrame.Type.NORMAL;
			break;
		case '/':
			type = BowlingFrame.Type.SPARE;
			break;
		case 'X':
			type = BowlingFrame.Type.STRIKE;
			break;
		}

		return type;
	}
}
