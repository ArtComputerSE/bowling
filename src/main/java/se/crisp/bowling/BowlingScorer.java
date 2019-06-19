package se.crisp.bowling;

public class BowlingScorer {
	int INCOMPLETE_FRAME = 100;

	public int fullScore(String pins) {
		int sum = 0;
		int mem = 0;

		for (String frame : pins.split(" ")) {
			int tmp = frameScore(frame);

			if (tmp < 0) {
				mem = tmp;
			} else {
				if (tmp != INCOMPLETE_FRAME) {
					sum += tmp;
					if (mem != 0) {
						sum += -mem;
						sum += tmp;
					}
				}
			}
		}
		return sum;
	}

	private int frameScore(String pins) {
		char[] charArray = pins.toCharArray();

		int sum = 0;
		for (char c : charArray) {
			if (c == 'X') {
				return -10;
			} else if (charArray.length == 1) {
				return INCOMPLETE_FRAME;
			} else if (c == '/') {
				if (charArray.length == 2) {
					return 0;
				} else {
					sum = 10;
				}
			} else if (c == '-') {
				sum += 0;
			} else {
				sum += c - '0';
			}
		}

		return sum;
	}

}
