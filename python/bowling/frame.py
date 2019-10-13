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
        else:
            return self.next.first_ball()

    def next_two_balls(self):
        if self.next is None:
            return None
        else:
            next_ball = self.next.first_ball()
            next_next_ball = self.next.second_ball()  # Might be None
            if next_next_ball is None:
                return None
            else:
                return next_ball + next_next_ball

    def first_ball(self):
        return parse(self.first)

    def second_ball(self):
        if self.is_strike():
            if self.next is None:
                return None
            else:
                return self.next.first_ball()
        elif self.is_spare():
            return 10 - self.first_ball()
        else:
            if self.second is None:
                return None
            else:
                return parse(self.second)

    def score(self):
        if self.next is None:
            value = self.value()
        else:
            value = self.value() + self.next.score()
        return value

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
