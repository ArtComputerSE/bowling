import pytest
from bowling import BowlingScorer, Frame


def test_chars_is_ok():
    assert BowlingScorer.chars_is_ok("X") == True
    assert BowlingScorer.chars_is_ok("v") == False
    assert BowlingScorer.chars_is_ok("") == False


def test_pin_string():
    assert BowlingScorer.score("X X X X X X X X X XXX") == 300
    assert BowlingScorer.score("9-9-9-9-9-9-9-9-9-9- ") == 90
    assert BowlingScorer.score("5/5/5/5/5/5/5/5/5/5/5") == 150

    assert BowlingScorer.score("45") == 9
    assert BowlingScorer.score("3/") == 0
    assert BowlingScorer.score("3/2") == 12

    assert BowlingScorer.score("X ") == 0

    assert BowlingScorer.score("X 2") == 0
    assert BowlingScorer.score("X 23") == 20


def test_Frame_first_char():
    # setup test OK chars
    ok_chars = ['1', '9', 'X', '-']
    exp_scores=[1, 9, 10 ,0]
    for i, c in enumerate(ok_chars):
        frame = Frame()
        assert frame.add_score(c) is True, f" char {c} expected to return True"
        assert frame.scores[0] == exp_scores[i]
        assert len(frame.scores) == 1, f"char {c} was not added to scores"

    # setup test failed fist chars
    failed_chars = [None, '/', '23', 3, ' ']
    for c in failed_chars:
        frame = Frame()
        assert frame.add_score(c)==False, f" char {c} expected to return False"
        assert len(frame.scores) == 0, f"char {c} shall not be appended to scores"


def test_Frame_second_char_when_first_char_is_number():
    # allow sum lower than 10
    frame = Frame()
    frame.add_score("5")
    assert frame.add_score('1') is True

    # allow zero
    frame = Frame()
    frame.add_score("-")
    assert frame.add_score('-') is True

    # allow spare
    frame = Frame()
    frame.add_score("-")
    assert frame.add_score('/') is True

    # not allowed sum equal or above 10
    frame = Frame()
    frame.add_score("5")
    assert frame.add_score('5') is False

    # not allowed strike
    frame = Frame()
    frame.add_score("5")
    assert frame.add_score('X') is False


def test_Frame_second_char_when_first_char_is_strike():
    frame = Frame()
    frame.add_score("X")
    assert frame.add_score(' ') is True

    # not allowed spar after strike
    frame = Frame()
    frame.add_score("X")
    assert frame.add_score('/') is False

    # not allowed number after strike
    frame = Frame()
    frame.add_score("X")
    assert frame.add_score('5') is False

    # not allowed zero
    frame = Frame()
    frame.add_score("X")
    assert frame.add_score('-') is False