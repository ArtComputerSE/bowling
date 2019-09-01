from enum import IntEnum

Frametype: IntEnum = IntEnum('FrameType', ('Points', 'Spare', 'Strike', 'Error'))


class Frame:
    def __init__(self):
        self.first_score: int = 0

    def _check_first_char(self, first_char: str)->(int):
        if type(first_char) is not str:
            return -1
        if len(first_char) != 1:
            return -1

        if first_char.isdigit():
            try:
                return int(first_char)
            except BaseException as b:
                return -1
        elif first_char == '-':
            return 0
        elif first_char == '/':
            return -1
        elif first_char == 'X':
            return 10
        elif first_char == ' ':
            return -1
        else:
            return -1



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
