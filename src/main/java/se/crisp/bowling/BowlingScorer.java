package se.crisp.bowling;

import java.util.Arrays;
import java.util.stream.Stream;

public class BowlingScorer {

  public int score(String pins) {
    return getFrames(pins).mapToInt(Frame::getScore).sum();
  }

  private Stream<Frame> getFrames(String pins) {
    return Arrays.stream(pins.trim().split(" ")).map(Frame::new);
  }

}
