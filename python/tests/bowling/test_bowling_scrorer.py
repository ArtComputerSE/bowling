from bowling.bowling_scorer import BowlingScorer


def test_no_score():
    scorer = BowlingScorer()
    assert scorer.score("--") == 0
