from . import bowlingframe


def score(game_record):
    frame = bowlingframe.BowlingFrame()
    for roll in game_record:
        frame.add_roll(roll)
    return frame.score()
