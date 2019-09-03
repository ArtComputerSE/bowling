package se.crisp.bowling;

class BowlingScorer {

    int score(String pins) {
        FramesParser fp = new FramesParser(pins);
        return fp.getFrameList().get(0).getValue();
    }

}

//String[] splitPins = str.split(" ");