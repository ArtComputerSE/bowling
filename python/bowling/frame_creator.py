from bowling import bowlingframe


class TraditionalFrameCreator:
    def __init__(self):
        # The knocked down pins are shared between some frames, so we store it outside the frames:
        self.pins_per_roll = []

    def create(self, frame_records, frames):
        for frame_record in frame_records:
            if len(frames) < 9:
                frame = bowlingframe.BowlingFrame(self.pins_per_roll)
            else:
                frame = bowlingframe.TenthFrame(self.pins_per_roll)
            frames.append(frame)
            for roll in frame_record:
                frame.add_roll(roll)
