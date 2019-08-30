class BowlingFrame:
    def __init__(self):
        self.rolls = []
        self.pins_per_roll = []

    def add_roll(self, roll):
        if roll in legal_value_chars:
            if roll in legal_digits:
                pins = int(roll)
                if pins + sum(self.pins_per_roll) < 10:
                    self.pins_per_roll.append(pins)
                    self.rolls.append(roll)
                else:
                    raise IllegalFrame("".join(self.rolls) + roll)
        else:
            raise IllegalCharacter(roll)

    def score(self):
        return sum(self.pins_per_roll)


class IllegalCharacter(ValueError):
    pass


class IllegalFrame(ValueError):
    pass


legal_digits = "123456789"
legal_value_chars = legal_digits + "-/X"
frame_separator = " "
