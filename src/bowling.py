from enum import IntEnum

Frametype: IntEnum = IntEnum('FrameType', ('Points', 'Spare', 'Strike', 'Error'))

SKIP = -1
SPARE = -2
STRIKE = -3
ERROR = -4

CHAR_TO_NUM = {'-': 0,
               '1': 1,
               '2': 2,
               '3': 3,
               '4': 4,
               '5': 5,
               '6': 6,
               '7': 7,
               '8': 8,
               '9': 9,
               'X': STRIKE,
               '/': SPARE,  # special case
               ' ': SKIP}  # special case


class Frame:
    def __init__(self, chars='', last_frame=False):
        self.scores = list()
        self.last_frame = last_frame
        if chars != '':
            for c in chars:
                self.add_score(c)

    def add_score(self, str_char) -> bool:
        self.char_to_score(str_char)

    def _basic_check(self, char):
        """
        checks that the string is valid
        :param char: string of one character

        """
        if type(char) is not str:
            raise Exception('only type of string is allowd')
        if len(char) != 1:
            raise Exception(f'One char is expected but received: {len(char)} num chars')
        if len(self.scores) >= 2 and self.last_frame == False:
            raise Exception('Only two chars is allowed per frame')
        if len(self.scores) >= 3 and self.last_frame == True:
            raise Exception('Only thee chars is allowed on last frame')
        return

    def char_to_score(self, char: str):
        self._basic_check(char)

        num = CHAR_TO_NUM.get(char, ERROR)
        if num == ERROR:
            raise Exception(f'Invalid char:{char}, type:{type(char)}')
        if num >= 0:
            if sum(self.scores) + num < 10:
                self.scores.append(num)
            elif sum(self.scores) % 10 == 0 and self.last_frame is True:
                self.scores.append(num)
            else:
                raise Exception(f'Cant add number{num}, to array {self.scores}')
        else:
            if num == SKIP:
                if len(self.scores) > 0 and sum(self.scores) % 10 == 0:
                    return
                elif self.last_frame:
                    return
                else:
                    raise Exception(f'Skip (space) not allowd in  frame {self.scores}')
            if num == SPARE:
                if len(self.scores) == 1 and sum(self.scores) < 10:
                    self.scores.append(10 - self.scores[0])
                else:
                    raise Exception(f'Spare not allowd in following sequence:{self.scores}')
            if num == STRIKE:
                if len(self.scores) == 0 or self.last_frame:
                    self.scores.append(10)
                else:
                    raise Exception(f'Not allowed to add Strike in following sequence{self.scores}')

    def get_score(self):

        pins = sum(self.scores)
        if self.is_finished():
            return pins
        else:
            return 0

    def is_finished(self):
        if sum(self.scores) < 10 and self.get_tries() > 1:
            return True
        elif sum(self.scores) == 10:
            return True
        elif self.get_tries() == 3 and self.last_frame:
            return True
        else:
            return False

    def get_tries(self):
        return len(self.scores)


class BowlingScorer:
    @staticmethod
    def split_into_pairs(seq):
        le = len(seq) // 2
        if le > 10:
            raise Exception("Too long sequence: {seq}")
        pairs = list()

        while seq:
            if len(pairs) == 9:
                pairs.append(seq[:])
                break
            else:
                pairs.append(seq[:2])
            seq = seq[2:]
        return pairs


    @staticmethod
    def get_next_n_scores(rem_frames, n):
        scores = [score for frame in rem_frames for score in frame.scores]
        if len(scores) >= n:
            return sum(scores[:n])+10
        else:
            return 0

    @staticmethod
    def score(pins: str) -> int:
        points = 0
        pairs = BowlingScorer.split_into_pairs(pins)

        # convert chars to frames
        frames = list()
        for i, pair in enumerate(pairs):
            is_last = (i==9)
            frames.append(Frame(pair, last_frame = is_last))

        for i in range(len(frames)):
            pins = frames[i].get_score()
            if pins < 10:
                points += frames[i].get_score()
            else:
                n = 1 if frames[i].get_tries() > 1 else 2

                # spare, add next-comming score + 10
                if frames[i].last_frame is True:
                    points += frames[i].get_score()
                else:
                    points += BowlingScorer.get_next_n_scores(rem_frames=frames[i + 1:], n=n)



        return points
