import pytest
from bowling import BowlingScorer, Frame


def test_pin_string():
    assert BowlingScorer.score("45") == 9
    assert BowlingScorer.score("3/") == 0
    assert BowlingScorer.score("3/2") == 12

    assert BowlingScorer.score("X ") == 0

    assert BowlingScorer.score("X 2") == 0
    assert BowlingScorer.score("X 23") == 20

    assert BowlingScorer.score("9-9-9-9-9-9-9-9-9-9- ") == 90
    assert BowlingScorer.score("5/5/5/5/5/5/5/5/5/5/5") == 150
    assert BowlingScorer.score("X X X X X X X X X XXX") == 300


def test_Frame_first_char():
    # setup test OK chars
    ok_chars = ['1', '9', 'X', '-']
    exp_scores = [1, 9, 10, 0]
    for i, c in enumerate(ok_chars):
        frame = Frame()
        frame.add_score(c)
        assert frame.scores[0] == exp_scores[i]
        assert len(frame.scores) == 1, f"char {c} was not added to scores"

    # setup test failed fist chars
    failed_chars = [None, '/', '23', ' ', 3]

    for c in failed_chars:
        frame = Frame()
        with pytest.raises(Exception):
            frame.add_score(c)


def test_Frame_second_char_when_first_char_is_number():
    # allow sum lower than 10
    frame = Frame("51")
    assert frame.get_score() == 6
    assert frame.get_tries() == 2

    # allow zero
    frame = Frame("--")
    assert frame.get_score() == 0
    assert frame.get_tries() == 2

    # allow spare
    frame = Frame("-/")
    assert frame.get_score() == 10
    assert frame.get_tries() == 2

    # not allowed sum equal or above 10
    with pytest.raises(Exception):
        Frame("55")

    # not allowed strike
    with pytest.raises(Exception):
        Frame("5X")


def test_Frame_second_char_when_first_char_is_strike():
    frame = Frame("X ")
    assert frame.get_score() == 10
    assert frame.get_tries() == 1

    # not allowed spar after strike
    with pytest.raises(Exception):
        frame = Frame("X/")

    # not allowed number after strike
    with pytest.raises(Exception):
        Frame("X5")

    # not allowed zero
    with pytest.raises(Exception):
        Frame("X-")


def test_create_last_frame():
    frame = Frame('36 ', last_frame=True)
    assert frame.is_finished() is True
    assert frame.get_score() == 9
    assert frame.get_tries() == 2

    # check spare
    frame = Frame('5/4', last_frame=True)
    assert frame.get_score() == 14
    assert frame.get_tries() == 3

    frame = Frame('5/X', last_frame=True)
    assert frame.get_score() == 20
    assert frame.get_tries() == 3

    frame = Frame('5/-', last_frame=True)
    assert frame.get_score() == 10
    assert frame.get_tries() == 3

    frame = Frame('XXX', last_frame=True)
    assert frame.get_score() == 30
    assert frame.get_tries() == 3

    with pytest.raises(Exception):
        frame = Frame('111', last_frame=True)
