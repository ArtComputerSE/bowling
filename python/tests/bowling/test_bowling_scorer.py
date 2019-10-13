import pytest

from bowling.bowling_scorer import BowlingScorer


def test_all_misses_no_points():
    assert BowlingScorer().score('00') == 0


def test_single_frame_no_spare_no_strike(single_frame_no_spare_no_strike):
    pins = single_frame_no_spare_no_strike[0]
    expected = single_frame_no_spare_no_strike[1]
    assert_score(pins, expected)


def test_strike_in_first_frame():
    pins = "X 71"
    second = 7 + 1
    first = 10 + second
    expected = first + second
    assert_score(pins, expected)


def test_strike_first_frame_no_follow():
    pins = "X "
    expected = 0
    assert_score(pins, expected)


def test_spare_in_first_frame():
    pins = "6/81"
    expected = 6 + 4 + 8 + 8 + 1
    assert_score(pins, expected)


def test_spare_in_first_frame_no_following_frame():
    pins = "2/"
    expected = 0
    assert_score(pins, expected)


def test_double_strike_and_some():
    pins = "X X 12"
    third = 1 + 2
    second = 10 + 1 + 2
    first = 10 + 10 + 1
    expected = first + second + third
    assert_score(pins, expected)


def test_strike_and_spare():
    pins = "X 5/34"
    first = 10 + 5 + 5
    second = 5 + 5 + 3
    third = 3 + 4
    expected = first + second + third
    assert_score(pins, expected)


def test_all_strikes():
    pins = "X X X X X X X X X XXX"
    expected = 300
    assert_score(pins, expected)


def test_ten_pairs_of_nine_and_miss():
    pins = "9-9-9-9-9-9-9-9-9-9-"
    expected = 10 * 9
    assert_score(pins, expected)


def test_ten_pairs_of_five_and_spare_plus_final_five():
    pins = "5/5/5/5/5/5/5/5/5/5/5"
    expected = 10 * 15
    assert_score(pins, expected)


def test_last_frame_with_spare_and_strike():
    pins = "--" * 9 + "9/X"
    expected = 9 + 1 + 10
    assert_score(pins, expected)


def test_last_frame_strike_strike_miss():
    pins = "------------------XX-"
    expected = 20
    assert_score(pins, expected)


def test_last_frame_strike_miss_miss():
    pins = "------------------X--"
    expected = 10
    assert_score(pins, expected)


def test_last_frame_strike_something_something():
    pins = "------------------X36"
    expected = 10 + 3 + 6
    assert_score(pins, expected)


def test_last_frame_strike_something_spare():
    pins = "------------------X3/"
    expected = 10 + 10
    assert_score(pins, expected)


def test_last_frame_with_two_rolls():
    pins = "--" * 9 + "34"
    expected = 3 + 4
    assert_score(pins, expected)


# Fixtures and helpers


def assert_score(pins, expected):
    assert BowlingScorer().score(pins) == expected


def single_frame_no_spare_no_strike_and_result():
    for pin1 in range(0, 10):
        pin1_char = '-' if pin1 == 0 else str(pin1)
        for pin2 in range(0, 10 - pin1):  # Can't hit more than nine pins in all
            pin2_char = '-' if pin2 == 0 else str(pin2)
            yield pin1_char + pin2_char, pin1 + pin2


@pytest.fixture(params=single_frame_no_spare_no_strike_and_result())
def single_frame_no_spare_no_strike(request):
    return request.param
