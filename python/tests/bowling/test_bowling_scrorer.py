import pytest

from bowling import bowling_scorer, frame


def test_no_score():
    assert bowling_scorer.score("--") == 0


def test_all_misses():
    assert bowling_scorer.score("--------------------") == 0


@pytest.fixture(params=['a', 'ö', ',', '&', '|', '\\', '0', '60'])
def illegal_character(request):
    return request.param


def test_illegal_characters(illegal_character):
    with pytest.raises(frame.IllegalCharacter):
        bowling_scorer.score(illegal_character)


@pytest.fixture(params=[("-1", 1), ("-2", 2), ("7-", 7), ("45", 9)])
def game_and_result(request):
    return request.param


def test_games(game_and_result):
    game = game_and_result[0]
    result = game_and_result[1]
    assert bowling_scorer.score(game) == result


@pytest.fixture(params=["19", "55", "77"])
def illegal_frame(request):
    return request.param


def test_illegal_frames(illegal_frame):
    with pytest.raises(frame.IllegalFrame):
        bowling_scorer.score(illegal_frame)
