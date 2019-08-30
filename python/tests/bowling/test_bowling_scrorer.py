import pytest

from bowling import bowling_scorer


def test_no_score():
    assert bowling_scorer.score("--") == 0


def test_all_misses():
    assert bowling_scorer.score("--------------------") == 0


@pytest.fixture(params=['a', 'ö', ',', '&', '|', '\\'])
def illegal_character(request):
    return request.param


def test_illegal_characters(illegal_character):
    with pytest.raises(bowling_scorer.IllegalCharacter):
        bowling_scorer.score(illegal_character)


@pytest.fixture(params=[("-1", 1)])
def game_and_result(request):
    return request.param


def test_games(game_and_result):
    game = game_and_result[0]
    result = game_and_result[1]
    assert bowling_scorer.score(game) == result
