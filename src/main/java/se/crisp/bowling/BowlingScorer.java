package se.crisp.bowling;

public class BowlingScorer {

	public int score(String pins) {
		char[] charArray = pins.toCharArray();

		int score = 0;
		for (char c : charArray) {
			if (c == '-') {
				score += 0;
			} else {
				score += c - '0';
			}
		}

		return score;
	}

}
