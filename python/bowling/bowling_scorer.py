from . import frame
from . import last_frame


class BowlingScorer:
    def score(self, pins):
        return self.__create_frames(pins).score()

    def __create_frames(self, pins):
        return self.__create_frames_impl(pins, 0)

    def __create_frames_impl(self, pins, start):
        if start == 18 and len(pins) == 21:  # Special last frame
            return last_frame.LastFrame(pins[start],
                                        pins[start + 1],
                                        pins[start + 2])
        else:  # Normal frame, including normal 10:th frame
            frame_pins = pins[start: start + 2]
            if len(frame_pins) == 2:  # Full frame
                return frame.Frame(frame_pins[0],
                                   frame_pins[1],
                                   self.__create_frames_impl(pins, start + 2)
                                   )
            elif len(frame_pins) == 1:  # Partial frame
                return frame.Frame(frame_pins[0],
                                   None,
                                   None
                                   )
            else:  # No frame
                return None
