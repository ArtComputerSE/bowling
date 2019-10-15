class BowlingScorer:

    def __init__(self, parser):
        self.parser = parser

    def score(self, pins):
        return self.parser.create_frames(pins).score()
