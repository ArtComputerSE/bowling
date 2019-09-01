import pytest

from bowling import bowling_scorer, bowlingframe


def test_no_score():
    assert bowling_scorer.score("--") == 0


def test_all_misses():
    assert bowling_scorer.score("-- -- -- -- -- -- -- -- -- --") == 0


@pytest.fixture(params=['a', 'ö', ',', '&', '|', '\\', '0', '60'])
def illegal_character(request):
    return request.param


def test_illegal_characters(illegal_character):
    with pytest.raises(bowlingframe.IllegalCharacterError):
        bowling_scorer.score(illegal_character)


@pytest.fixture(params=[("-1", 1), ("-2", 2), ("7-", 7), ("45", 9), ("X", 0), ("X ", 0),
                        ("5/", 0), ("3/ 2", 12), ("X 2", 0), ("X 23", 20)])
def single_frame_and_result(request):
    return request.param


def test_single_frames(single_frame_and_result):
    frame = single_frame_and_result[0]
    result = single_frame_and_result[1]
    assert bowling_scorer.score(frame) == result


@pytest.fixture(params=["19", "55", "77", "1/1", "X1", "444", "111", "9X",
                        "X X X X X X X X X 9//",
                        "X X X X X X X X X X9/",
                        "X X X X X X X X X 811",
                        "X X X X X X X X X 81/",
                        "X X X X X X X X X XX/",
                        "X X X X X X X X X X/"
                        ])
def illegal_frame(request):
    return request.param


def test_illegal_frames(illegal_frame):
    with pytest.raises(bowlingframe.IllegalFrameError, match=r"^{0}$".format(illegal_frame.split()[-1])):
        bowling_scorer.score(illegal_frame)


@pytest.fixture(params=[
    ("15 15 15 15 15 15 15 15 15 15", 60),
    ("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-", 90),
    ("-2 -2", 4),
    ("45 2-", 11),
    ("5/ 7", 17),
    ("5/ 72", 26),
    ("5/ 7/", 17),
    ("1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/", 99),  # First 9 frames are 11, the 10:th is unfinished (0)
    ("1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/ 1/1", 110),  # All ten frames are 11
    ("5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/ 5/5", 150),  # All ten frames are 15
    ("X 23", 20),
    ("X 2", 0),
    ("X X X X X X X X X X", 240),  # First eight frames are 30 each, last two are unfinished (0)
    ("X X X X X X X X X 9/", 259),  # First seven frames are 30 each, 8:th is 29, 9:th 20, 10:th unfinished (0)
    ("X X X X X X X X X 9/1", 270),  # First seven frames are 30 each, 8:th is 29, 9:th 20, 10:th 11
    ("X X X X X X X X X 81", 266),  # First seven frames are 30 each, 8:th is 28, 9:th 19, 10:th 9
    ("X X X X X X X X X X91", 289),  # First eight frames are 30 each, 9:th 29, 10:th 20
    ("X X X X X X X X X 9/X", 279),  # First seven frames are 30 each, 8:th is 29, 9:th 20, 10:th 20
    ("X X X X X X X X X XX1", 291),  # First nine frames are 30 each, 10:th 21
    ("X X X X X X X X X XXX", 300),  # All ten frames are 30 each
])
def game_and_result(request):
    return request.param


def test_multi_frame_games(game_and_result):
    game = game_and_result[0]
    result = game_and_result[1]
    assert bowling_scorer.score(game) == result


def test_too_many_frames():
    with pytest.raises(bowling_scorer.GameRecordError):
        bowling_scorer.score("11 22 33 44 55 11 22 33 44 55 11")


