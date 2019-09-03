def score(game_record, frame_creator, frame_splitter):

    frame_records = frame_splitter(game_record)

    frames = []
    frame_creator.create(frame_records, frames)

    total_score = sum(frame.score() for frame in frames)
    return total_score


