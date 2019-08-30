class BowlingScorer:
    pass


def score(game_record):
    check_for_illegal_characters(game_record)
    for roll in game_record:
        if roll in legalDigits:
            return 1
    return 0


class IllegalCharacter(ValueError):
    pass


legalDigits = "123456789"
legalCharacters = legalDigits + " -/X"


def check_for_illegal_characters(game_record):
    for character in game_record:
        if character not in legalCharacters:
            raise IllegalCharacter(character)
