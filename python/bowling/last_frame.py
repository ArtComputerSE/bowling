from bowling.frame import IllegalFrameError
from . import frame


class LastFrame(frame.Frame):
    """Special last frame class for the case where the last frame has three
    rolls"""
    def __init__(self, first, second, third):
        if first != LastFrame.STRIKE and second != LastFrame.SPARE:
            raise IllegalFrameError("Last frame with bonus throw is not a strike or a spare: {0}{1}{2}"
                                    "".format(first, second, third))
        super(LastFrame, self).__init__(first, second)
        self.__third = third

    def value(self):
        if self.is_strike():
            if self.__third == self.SPARE:
                return 10 + 10
            else:
                return 10 + frame.parse(self.second) + frame.parse(self.__third)
        else:  # self.is_spare():
            return 10 + frame.parse(self.__third)

    def second_ball(self):
        return frame.parse(self.second)
