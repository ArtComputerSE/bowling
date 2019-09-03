from . import bowlingframe


def score(game_record):

    frame_records = frame_splitter(game_record)

    frames = []
    frame_creator = FrameCreator()
    frame_creator.frame_creator(frame_records, frames)

    total_score = sum(frame.score() for frame in frames)
    return total_score


class FrameCreator:
    def __init__(self):
        # The knocked down pins are shared between some frames, so we store it outside the frames:
        self.pins_per_roll = []

    def frame_creator(self, frame_records, frames):
        for frame_record in frame_records:
            if len(frames) < 9:
                frame = bowlingframe.BowlingFrame(self.pins_per_roll)
            else:
                frame = bowlingframe.TenthFrame(self.pins_per_roll)
            frames.append(frame)
            for roll in frame_record:
                frame.add_roll(roll)


def frame_splitter(game_record):
    frame_records = game_record.strip().split(bowlingframe.frame_separator)
    nr_of_frames_in_game_record = len(frame_records)
    if nr_of_frames_in_game_record > 10:
        raise GameRecordError(
            "Input game record holds too many frames ({0})"
            "".format(nr_of_frames_in_game_record)
        )
    return frame_records


class GameRecordError(ValueError):
    pass
