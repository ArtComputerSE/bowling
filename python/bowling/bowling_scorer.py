def score(game_record):
    check_for_illegal_characters(game_record)
    total_score = 0
    for roll in game_record:
        if roll in legalDigits:
            total_score += int(roll)
    return total_score


class IllegalCharacter(ValueError):
    pass


legalDigits = "123456789"
legalCharacters = legalDigits + " -/X"


def check_for_illegal_characters(game_record):
    for character in game_record:
        if character not in legalCharacters:
            raise IllegalCharacter(character)
