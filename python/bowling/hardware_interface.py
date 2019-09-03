from bowling import bowlingframe


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
