from . import frame
from . import last_frame


class BowlingScorer:
    def score(self, pins):
        return self.__create_frames(pins).score()

    def __create_frames(self, pins):
        return self.__create_frames_impl(pins, 0)

    def __create_frames_impl(self, pins, start):
        if len(pins) == 21 and start == 18:
            return last_frame.LastFrame(pins[start],
                                        pins[start+1],
                                        pins[start+2])
        elif start >= len(pins) - 1:
            return None
        else:
            return frame.Frame(pins[start],
                               pins[start+1],
                               self.__create_frames_impl(pins, start + 2)
                               )
