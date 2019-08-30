import pytest

from bowling import bowling_scorer, bowlingframe


def test_no_score():
    assert bowling_scorer.score("--") == 0


def test_all_misses():
    assert bowling_scorer.score("--------------------") == 0


@pytest.fixture(params=['a', 'ö', ',', '&', '|', '\\', '0', '60'])
def illegal_character(request):
    return request.param


def test_illegal_characters(illegal_character):
    with pytest.raises(bowlingframe.IllegalCharacter):
        bowling_scorer.score(illegal_character)


@pytest.fixture(params=[("-1", 1), ("-2", 2), ("7-", 7), ("45", 9)])
def single_frame_and_result(request):
    return request.param


def test_single_frames(single_frame_and_result):
    frame = single_frame_and_result[0]
    result = single_frame_and_result[1]
    assert bowling_scorer.score(frame) == result


@pytest.fixture(params=["19", "55", "77"])
def illegal_frame(request):
    return request.param


def test_illegal_frames(illegal_frame):
    with pytest.raises(bowlingframe.IllegalFrame):
        bowling_scorer.score(illegal_frame)


@pytest.fixture(params=[
    ("15 15 15 15 15 15 15 15 15 15", 60),
    ("9- 9- 9- 9- 9- 9- 9- 9- 9- 9-", 90),
    ("-2 -2", 4)
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


