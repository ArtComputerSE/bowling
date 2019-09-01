import pytest
from bowling import BowlingScorer, Frame

def test_chars_is_ok():
    assert BowlingScorer.chars_is_ok("X")== True
    assert BowlingScorer.chars_is_ok("v") == False
    assert BowlingScorer.chars_is_ok("") == False

def test_pin_string():
    assert BowlingScorer.score("X X X X X X X X X XXX")==300
    assert BowlingScorer.score("9-9-9-9-9-9-9-9-9-9- ")==90
    assert BowlingScorer.score("5/5/5/5/5/5/5/5/5/5/5")==150

    assert BowlingScorer.score("45") == 9
    assert BowlingScorer.score("3/") == 0
    assert BowlingScorer.score("3/2") == 12

    assert BowlingScorer.score("X ") == 0

    assert BowlingScorer.score("X 2") == 0
    assert BowlingScorer.score("X 23") == 20

def test_Frame():
    assert Frame("45").points == 9
    assert Frame("3/").points == 0
    assert Frame("X ") == 0
