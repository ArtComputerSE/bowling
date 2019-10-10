class Frame:
    STRIKE = 'X'
    SPARE = '/'

    def __init__(self, first, second, next_frame=None):
        self.first = first
        self.second = second
        self.next = next_frame

    def value(self):
        if self.is_spare():
            next_ball = self.next_ball()
            if next_ball is None:
                return 0
            else:
                return 10 + next_ball
        elif self.is_strike():
            next_two_balls = self.next_two_balls()
            if next_two_balls is None:
                return 0
            else:
                return 10 + next_two_balls
        else:
            return self.sum_both()

    def next_ball(self):
        if self.next is None:
            return None
        elif self.next.is_strike():
            return 10
        else:
            return parse(self.next.first)

    def next_two_balls(self):
        if self.next is None:
            return None
        elif self.next.is_strike():
            if self.next.next is None:
                return None
            elif self.next.next.is_strike():
                return 10 + 10
            else:
                return 10 + parse(self.next.next.first)
        elif self.next.is_spare():
            return 10
        else:
            return self.next.value()

    def score(self):
        if self.next is None:
            return self.value()
        else:
            return self.value() + self.next.score()

    def sum_both(self):
        return parse(self.first) + parse(self.second)

    def is_spare(self):
        return self.second == Frame.SPARE

    def is_strike(self):
        return self.first == Frame.STRIKE


def parse(roll):
    if roll == '-':
        return 0
    if roll in '123456789':
        return int(roll)
    if roll == 'X':
        return 10
    return 0
