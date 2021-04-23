package model;

public class Snake {
	private int number;
	private int initPos;
	private int endPos;
	// Asegurarse de que initPos siempre es mayor a endPos;
	
	public Snake(int l, int i, int e) {
		number = l;
		initPos = i;
		endPos = e;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(char number) {
		this.number = number;
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
