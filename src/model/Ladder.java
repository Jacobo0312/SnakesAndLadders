package model;

public class Ladder {
	private int number;
	private int initPos;
	private int endPos;
	// Asegurarse de que initPos siempre es menor a endPos;
	
	public Ladder(int n, int i, int e) {
		number = n;
		initPos = i;
		endPos = e;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int n) {
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


	@Override
	public String toString() {
		return Integer.toString(number);
	}
	
	
}
