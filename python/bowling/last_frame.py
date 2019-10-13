from . import frame


class LastFrame(frame.Frame):
    def __init__(self, first, second, third):
        if first != LastFrame.STRIKE and second != LastFrame.SPARE:
            raise LastFrameError("Last frame with bonus throw is not a strike or a spare")
        super(LastFrame, self).__init__(first, second)
        self.__third = third

    def value(self):
        if self.is_strike():
            if self.__third == self.SPARE:
                return 10 + 10
            else:
                return 10 + frame.parse(self.second) + frame.parse(self.__third)
        elif self.second == LastFrame.SPARE:
            return 10 + frame.parse(self.__third)
        else:
            raise LastFrameError("Last frame with bonus throw is not a strike or a spare")

    def second_ball(self):
        if self.second is None:
            return None
        else:
            return frame.parse(self.second)


class LastFrameError(RuntimeError):
    pass
