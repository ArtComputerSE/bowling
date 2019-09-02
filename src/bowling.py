from enum import IntEnum

Frametype: IntEnum = IntEnum('FrameType', ('Points', 'Spare', 'Strike', 'Error'))


class Frame:
    def __init__(self, last_frame=False):
        self.scores = list()
        self.last_frame = last_frame

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

    def get_score(self):
        pins = sum(self.scores)
        if self.is_finished():
            return pins
        else:
            return 0

    def is_finished(self):
        if sum(self.scores) < 10 and self.get_tries()>1:
            return True
        elif sum(self.scores) == 10:
            return True
        else:
            return False
    def get_tries(self):
        return len(self.scores)


class BowlingScorer:
    @staticmethod
    def split_into_pairs(seq):
        n = 2
        while seq:
            yield seq[:n]
            seq = seq[n:]

    @staticmethod
    def get_next_n_scores(rem_frames, n):
        scores = [score for frame in rem_frames for score in frame.scores]
        if len(scores) >= n:
            return sum(scores[:n])
        else:
            return 0

    @staticmethod
    def score(pins: str) -> int:
        points = 0
        pairs = list(BowlingScorer.split_into_pairs(pins))

        # convert chars to frames
        frames = list()
        for pair in pairs:
            frame = Frame()
            for c in pair:
                if frame.add_score(c) is False:
                    return -1
            frames.append(frame)

        for i in range(len(frames)):
            pins = frames[i].get_score()
            if pins < 10:
                points += frames[i].get_score()
            elif frames[i].get_tries() > 1:
                # spare, add next-comming score + 10
                next_points = BowlingScorer.get_next_n_scores(rem_frames=frames[i+1:], n=1)
                points = points + next_points + 10 if next_points != 0 else 0
            else:
                # strike
                next_points = BowlingScorer.get_next_n_scores(rem_frames=frames[i+1:], n=2)
                points = points + next_points + 10 if next_points != 0 else 0

        return points
