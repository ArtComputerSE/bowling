frame_regex = (
    r"("
    r"-[-1-9/]|"
    r"1[-1-8/]|"
    r"2[-1-7/]|"
    r"3[-1-6/]|"
    r"4[-1-5/]|"
    r"5[-1-4/]|"
    r"6[-1-3/]|"
    r"7[-1-2/]|"
    r"8[-1/]|"
    r"9[-/]|"
    r"X "
    r")"
)

partial_frame_regex = r"([-1-9X])"

last_frame_regex = (
    r"("
    r"-[-1-9]|"
    r"1[-1-8]|"
    r"2[-1-7]|"
    r"3[-1-6]|"
    r"4[-1-5]|"
    r"5[-1-4]|"
    r"6[-1-3]|"
    r"7[-1-2]|"
    r"8[-1]|"
    r"9-|"
    r"[-1-9]/{extra}|"
    r"X{extra}{extra}"
    r")"
    r"".format(extra=r"[-1-9X]")
)

partial_last_frame_regex = (
    r"("
    r"[-1-9]|"
    r"[-1-9]/|"
    r"X{extra}"
    r")"
    r"".format(extra=r"[-1-9X]")
)

multiple_frames_regex = (
    r"{f}{{0,8}}{pf}|"
    r"{f}{{1,9}}|"
    r"{f}{{9}}{plf}|"
    r"{f}{{9}}{lf}"
    r"".format(pf=partial_frame_regex,
               f=frame_regex,
               plf=partial_last_frame_regex,
               lf=last_frame_regex
               )
)

multiple_full_frames_regex = (
    r"{f}{{1,9}}|"
    r"{f}{{9}}{lf}"
    r"".format(pf=partial_frame_regex,
               f=frame_regex,
               plf=partial_last_frame_regex,
               lf=last_frame_regex
               )
)
