package model;

public class Ladder {
	private int number;
	private int initPos;
	private int endPos;
	
	public Ladder(int n, int i, int e) {
		number = n;
		initPos = i;
		endPos = e;
	}

	public int getLetter() {
		return number;
	}

	public void setLetter(int n) {
		this.number = n;
	}

	public int getInitPos() {
		return initPos;
	}

	public void setInitPos(int initPos) {
		this.initPos = initPos;
	}

	public int getEndPos() {
		return endPos;
	}

	public void setEndPos(int endPos) {
		this.endPos = endPos;
	}
	
}
