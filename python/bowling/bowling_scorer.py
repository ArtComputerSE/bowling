from . import bowlingframe


def score(game_record):
    pins_per_roll = []  # The knocked down pins are shared between some frames, so we store it outside the frames
    frames = []
    frame_records = game_record.split(bowlingframe.frame_separator)
    nr_of_frames_in_game_record = len(frame_records)
    if nr_of_frames_in_game_record > 10:
        raise GameRecordError(
            "Input game record holds too many frames ({0})"
            "".format(nr_of_frames_in_game_record)
        )
    for frame_record in frame_records:
        if len(frames) < 9:
            frame = bowlingframe.BowlingFrame(pins_per_roll)
            frames.append(frame)
            for roll in frame_record:
                frame.add_roll(roll)
        else:
            frame = bowlingframe.TenthFrame(pins_per_roll)
            frames.append(frame)
            for roll in frame_record:
                frame.add_roll(roll)

    total_score = sum(frame.score() for frame in frames)
    return total_score


class GameRecordError(ValueError):
    pass
