from . import frame
from . import last_frame


class Parser:
    def __init__(self):
        pass

    def create_frames(self, pins):
        return self.__create_frames_impl(pins, 0)

    def __create_frames_impl(self, pins, start):
        frame_rolls = ""
        try:
            if start == 18 and len(pins) == 21:  # Special last frame
                frame_rolls = pins[start: start + 3]
                first_roll = frame_rolls[0]
                second_roll = frame_rolls[1]
                third_roll = frame_rolls[2]
                frame_type = get_frame_type(first_roll, second_roll)

                if frame_type == frame.FrameType.STRIKE:
                    first_pins = total_nr_of_pins
                    second_pins = parse_roll(second_roll)
                    if third_roll == SPARE_CHAR:
                        third_pins = total_nr_of_pins - second_pins
                    else:
                        third_pins = parse_roll(third_roll)
                elif frame_type == frame.FrameType.SPARE:
                    first_pins = parse_roll(first_roll)
                    second_pins = total_nr_of_pins - first_pins
                    third_pins = parse_roll(third_roll)
                else:  # frame_type == frame.FrameType.BASIC:
                    raise IllegalFrameError("Last frame with bonus throw is not a strike or a spare: {0}{1}{2}"
                                            "".format(first_roll, second_roll, third_roll))
                return last_frame.LastFrame(first_pins,
                                            second_pins,
                                            third_pins,
                                            frame_type)
            else:  # Normal frame, including normal 10:th frame
                frame_rolls = pins[start: start + 2]
                if len(frame_rolls) == 2:  # Full frame
                    first_roll = frame_rolls[0]
                    second_roll = frame_rolls[1]
                    frame_type = get_frame_type(first_roll, second_roll)

                    if frame_type == frame.FrameType.STRIKE:
                        first_pins = total_nr_of_pins
                        if second_roll == NO_THROW_CHAR:
                            second_pins = 0
                        else:
                            second_pins = parse_roll(second_roll)
                    elif frame_type == frame.FrameType.SPARE:
                        first_pins = parse_roll(first_roll)
                        second_pins = total_nr_of_pins - first_pins
                    else:  # frame_type == frame.FrameType.BASIC
                        first_pins = parse_roll(first_roll)
                        second_pins = parse_roll(second_roll)

                    return frame.Frame(first_pins,
                                       second_pins,
                                       frame_type,
                                       self.__create_frames_impl(pins, start + 2)
                                       )
                elif len(frame_rolls) == 1:  # Partial frame
                    first_roll = frame_rolls[0]
                    frame_type = get_frame_type(first_roll)
                    first_pins = parse_roll(first_roll)
                    return frame.Frame(first_pins,
                                       None,
                                       frame_type,
                                       None
                                       )
                else:  # No frame
                    return None
        except IllegalRoll:
            raise IllegalFrameError(frame_rolls)


def get_frame_type(first_pins, second_pins=None):
    strike = first_pins == STRIKE_CHAR
    spare = second_pins == SPARE_CHAR
    if strike:
        if spare:  # Cannot be both strike and spare at the same time
            raise IllegalFrameError("{0}{1}".format(first_pins, second_pins))
        else:
            return frame.FrameType.STRIKE
    elif spare:
        return frame.FrameType.SPARE
    else:
        return frame.FrameType.BASIC


total_nr_of_pins = 10
SPARE_CHAR = '/'
STRIKE_CHAR = 'X'
MISS_CHAR = '-'
NO_THROW_CHAR = ' '


def parse_roll(roll):
    if roll == MISS_CHAR:
        return 0
    elif roll in '123456789':
        return int(roll)
    elif roll == STRIKE_CHAR:
        return total_nr_of_pins
    else:
        raise IllegalRoll("Roll cannot be {0} at this position".format(roll))


class IllegalRoll(RuntimeError):
    pass


class IllegalFrameError(RuntimeError):
    pass
