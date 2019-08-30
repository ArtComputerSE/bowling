from . import frame


def score(game_record):
    my_frame = frame.Frame()
    for roll in game_record:
        my_frame.add_roll(roll)
    return my_frame.score()
