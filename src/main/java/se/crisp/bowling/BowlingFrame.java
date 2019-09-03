package se.crisp.bowling;

public class BowlingFrame {
	public enum Type {
		NORMAL, SPARE, STRIKE
	}

	private Type m_type;
	private int m_ball1;
	private int m_ball2;
	private BowlingFrame m_nextFrame;

	public BowlingFrame(Type m_type, int m_ball1, int m_ball2, BowlingFrame m_nextFrame) {
		super();
		this.m_type = m_type;
		this.m_ball1 = m_ball1;
		this.m_ball2 = m_ball2;
		this.m_nextFrame = m_nextFrame;
	}

	public void addNextFrame(BowlingFrame nextFrame) {
		m_nextFrame = nextFrame;
	}

	public Type getType() {
		return m_type;
	}

	public int getBall1() {
		return m_ball1;
	}

	public int getBall2() {
		return m_ball2;
	}

	public int countPoints() {
		int sum = 0;

		switch (m_type) {
		case NORMAL:
			sum = m_ball1 + m_ball2;
			break;
		case SPARE:
			sum = 10;
			if (m_nextFrame != null) {
				sum += m_nextFrame.getBall1();
			}
			break;
		case STRIKE:
			sum = 10;
			if (m_nextFrame != null) {
				if (m_nextFrame.getType() == Type.STRIKE) {
					sum += m_nextFrame.getBall1() * 2;
				}
				else {
					sum += m_nextFrame.getBall1();
					sum += m_nextFrame.getBall2();
				}
			} 
			break;
		}

		return sum;
	}
}
