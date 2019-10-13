from hypothesis import given, strategies

from bowling.bowling_scorer import BowlingScorer
from bowlingtestpkg.frame_regex import multiple_full_frames_regex, frame_regex, multiple_frames_regex


@given(strategies.from_regex(frame_regex, fullmatch=True).filter(lambda f: f != 'X ' and f[1] != '/'))
def test_score_for_simple_frames_is_sum(frame):
    score = BowlingScorer().score(frame)
    first_value = int(frame[0]) if frame[0] != '-' else 0
    second_value = int(frame[1]) if frame[1] != '-' else 0

    assert score == first_value + second_value


@given(strategies.from_regex(multiple_full_frames_regex, fullmatch=True))
def test_score_between_zero_and_300(frames):
    assert 0 <= BowlingScorer().score(frames) <= 300


@given(strategies.from_regex(multiple_frames_regex, fullmatch=True))
def test_score_between_zero_and_300(frames):
    assert 0 <= BowlingScorer().score(frames) <= 300
