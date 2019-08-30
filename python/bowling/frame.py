class Frame:
    def __init__(self):
        self.rolls = []
        self.pins_per_roll = []

    def add_roll(self, roll):
        if roll in legalCharacters:
            if roll in legalDigits:
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


legalDigits = "123456789"
legalValueChars = legalDigits + "-/X"
legalSeparator = " "
legalCharacters = legalValueChars + legalSeparator
