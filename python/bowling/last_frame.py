from . import frame


class LastFrame(frame.Frame):
    def __init__(self, first, second, third):
        super(LastFrame, self).__init__(first, second)
        self.__third = third

    def value(self):
        if self.first == LastFrame.STRIKE and self.second == LastFrame.STRIKE and self.__third == LastFrame.STRIKE:
            last = 10
            second_to_last = 20
            third_to_last = 30
            return last + second_to_last + third_to_last
        elif self.second == LastFrame.SPARE:
            return 10 + frame.parse(self.__third)
        else:
            return super(LastFrame, self).sum_both() + frame.parse(self.__third)

    def score(self):
        return super(LastFrame, self).score()
