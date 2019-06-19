package se.crisp.bowling;

import java.util.Arrays;

class Frame {
  Frame(String pins) {
    this.pins = pins;
  }

  private String pins;

  int getScore() {
    return Arrays.stream(pins.split("")).mapToInt(s -> s.equals("-") ? 0 : Integer.parseInt(s)).sum();
  }
}
