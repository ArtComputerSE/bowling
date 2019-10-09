import unittest
from bowling.bowling_scorer import BowlingScorer


class BowlingScorerTest(unittest.TestCase):
    def test_no_score(self):
        scorer = BowlingScorer()
        self.assertEqual(scorer.score("--"), 0)
