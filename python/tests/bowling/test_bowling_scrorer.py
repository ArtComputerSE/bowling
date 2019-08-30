import pytest

from bowling.bowling_scorer import BowlingScorer, IllegalCharacter


def test_no_score():
    scorer = BowlingScorer()
    assert scorer.score("--") == 0


def test_all_misses():
    scorer = BowlingScorer()
    assert scorer.score("--------------------") == 0


@pytest.fixture(params=['a', 'ö', ',', '&', '|', '\\'])
def illegal_character(request):
    return request.param


def test_illegal_characters(illegal_character):
    with pytest.raises(IllegalCharacter):
        BowlingScorer().score(illegal_character)
