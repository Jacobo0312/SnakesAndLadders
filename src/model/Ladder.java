package model;

public class Ladder {
	private String letter;
	private int initPos;
	private int endPos;
	// Asegurarse de que initPos siempre es menor a endPos;
	
	public Ladder(String n, int i, int e) {
		letter = n;
		initPos = i;
		endPos = e;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String n) {
		this.letter = n;
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
		return letter;
	}
	
	
}
