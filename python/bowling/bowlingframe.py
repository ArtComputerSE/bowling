from enum import Enum, auto


class FrameType(Enum):
    BASIC = auto()
    SPARE = auto()
    STRIKE = auto()


class BowlingFrame:
    def __init__(self, pins_per_roll):
        self.pins_per_roll = pins_per_roll
        self.begin_ix = None
        self.nr_of_rolls_in_frame_type = 2
        self.rolls = []
        self.frame_type = FrameType.BASIC

    def add_roll(self, roll):
        self.rolls.append(roll)
        if self.begin_ix is None:
            self.begin_ix = len(self.pins_per_roll)
        if roll in legal_value_chars:
            if not self.ok_to_add_roll():
                raise IllegalFrameError("".join(self.rolls))
            if roll in legal_digits:
                pins = int(roll)
                self.pins_per_roll.append(pins)
                if self.frame_type == FrameType.BASIC:
                    if self.score() >= 10:
                        raise IllegalFrameError("".join(self.rolls))
            elif roll == miss_char:
                pins = 0
                self.pins_per_roll.append(pins)
            elif roll == spare_char:
                if len(self.rolls) == 2 and self.frame_type == FrameType.BASIC:
                    pins = 10 - self.pins_per_roll[self.begin_ix]
                    self.pins_per_roll.append(pins)
                    self.nr_of_rolls_in_frame_type = 3
                    self.frame_type = FrameType.SPARE
                else:
                    raise IllegalFrameError("".join(self.rolls))
            else:  # Strike char
                if len(self.rolls) == 1:
                    pins = 10
                    self.pins_per_roll.append(pins)
                    self.nr_of_rolls_in_frame_type = 3
                    self.frame_type = FrameType.STRIKE
                elif self.frame_type in (FrameType.SPARE, FrameType.STRIKE):
                    pins = 10
                    self.pins_per_roll.append(pins)
                else:
                    raise IllegalFrameError("".join(self.rolls))
        else:
            raise IllegalCharacterError(roll)

    def score(self):
        if len(self.pins_slice) == self.nr_of_rolls_in_frame_type:
            return sum(self.pins_slice)
        else:
            return 0

    def ok_to_add_roll(self):
        return self.frame_type == FrameType.BASIC and len(self.rolls) <= 2

    @property
    def pins_slice(self):
        return self.pins_per_roll[self.begin_ix:self.end_ix]

    @property
    def end_ix(self):
        if self.begin_ix is None:
            raise IndexError("Begin and end indices are uninitialized until first roll has been added")
        return self.begin_ix + self.nr_of_rolls_in_frame_type


class TenthFrame(BowlingFrame):
    def ok_to_add_roll(self):
        if self.frame_type == FrameType.BASIC and len(self.rolls) <= 2:
            return True
        elif self.frame_type == FrameType.SPARE and len(self.rolls) <= 3:
            return True
        elif self.frame_type == FrameType.STRIKE and len(self.rolls) <= 3:
            return True
        else:
            return False


class IllegalCharacterError(ValueError):
    pass


class IllegalFrameError(ValueError):
    pass


legal_digits = "123456789"
miss_char = "-"
spare_char = "/"
strike_char = "X"
legal_value_chars = legal_digits + miss_char + spare_char + strike_char
frame_separator = " "
