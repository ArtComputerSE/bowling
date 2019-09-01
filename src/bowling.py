class Frame:
    def __init__(self, pin):
        self.points: int = 0
        self.type = None


class BowlingScorer:
    @staticmethod
    def chars_is_ok(score_str: str) -> bool:
        ok_chars = "123456789-/X "
        if not score_str:
            return False

        for c in score_str:
            if c not in ok_chars:
                return False
        return True

    @staticmethod
    def score(pins: str) -> int:
        return 0
