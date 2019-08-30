class BowlingScorer:
    def score(self, record):
        check_for_illegal_characters(record)
        return 0


class IllegalCharacter(ValueError):
    pass


legalDigits = "123456789"
legalCharacters = legalDigits + " -/X"


def check_for_illegal_characters(record):
    for character in record:
        if character not in legalCharacters:
            raise IllegalCharacter(character)
