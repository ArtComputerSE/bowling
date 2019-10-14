from . import parser


class BowlingScorer:

    def __init__(self):
        self.parser = parser.Parser()

    def score(self, pins):
        return self.parser.create_frames(pins).score()
