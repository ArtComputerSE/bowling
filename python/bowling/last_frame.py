from . import frame


class LastFrame(frame.Frame):
    """Special last frame class for the case where the last frame has three
    rolls"""
    def __init__(self, first, second, third, frame_type):
        super(LastFrame, self).__init__(first, second, frame_type)
        self.__third = third

    def value(self):
        return self.first + self.second + self.__third

    def second_ball(self):
        return self.second
