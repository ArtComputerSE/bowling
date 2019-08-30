from . import bowlingframe


def score(game_record):
    frames = [bowlingframe.BowlingFrame() for i in range(10)]
    frame_records = game_record.split(bowlingframe.frame_separator)
    nr_of_frames_in_game_record = len(frame_records)
    if nr_of_frames_in_game_record > 10:
        raise GameRecordError(
            "Input game record holds too many frames ({0})"
            "".format(nr_of_frames_in_game_record)
        )
    for frame, frame_record in zip(frames, frame_records):
        for roll in frame_record:
            frame.add_roll(roll)
    total_score = sum(frame.score() for frame in frames)
    return total_score


class GameRecordError(ValueError):
    pass
