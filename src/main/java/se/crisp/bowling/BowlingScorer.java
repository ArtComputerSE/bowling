package se.crisp.bowling;

import java.util.List;

public class BowlingScorer {

	public int score(String pins) {
		ScoreCard scoreCard = new ScoreCard(pins);
		
		List<BowlingFrame> frames = scoreCard.parseScoreCard();
		
		int points = 0;
		for (BowlingFrame frame : frames) {
			points += frame.countPoints();
		}
		
		return points;
	}

}
