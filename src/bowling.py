from enum import IntEnum

Frametype: IntEnum = IntEnum('FrameType', ('Points', 'Spare', 'Strike', 'Error'))


class Frame:
    def __init__(self):
        self.scores = list()

    def add_score(self, str_char) -> bool:
        if len(self.scores) == 0:
            score = self._first_char_to_score(str_char)
        elif len(self.scores) == 1:
            score = self._second_char_to_score(str_char)
        if score is None:
            return True
        elif score >= 0:
            self.scores.append(score)
            return True
        else:
            return False

    def _basic_check(self, char):
        if type(char) is not str:
            return False
        if len(char) != 1:
            return False
        return True

    def _first_char_to_score(self, first_char: str) -> (int):
        if not self._basic_check(first_char):
            return -1

        if first_char.isdigit():
            try:
                return int(first_char)
            except BaseException as b:
                return -1
        elif first_char == '-':
            return 0
        elif first_char == 'X':
            return 10
        else:
            return -1

    def _second_char_to_score(self, second_char: str) -> (int):
        if not self._basic_check(second_char):
            return -1
        if len(self.scores) != 1:
            return -1

        if second_char.isdigit():
            try:
                nbr = int(second_char)
                if self.scores[0] + nbr > 9:
                    return -1
                else:
                    return nbr
            except BaseException as b:
                return -1
        elif second_char == '-':
            return 0 if self.scores[0] < 10 else -1
        elif second_char == '/':
            return 10 - self.scores[0] if self.scores[0] < 10 else -1
        elif second_char == ' ':
            return None if self.scores[0] == 10 else -1
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
