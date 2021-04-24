package model;

public class Snake {
	private String letter;
	private int initPos;
	private int endPos;
	// Asegurarse de que initPos siempre es mayor a endPos;
	
	public Snake(String l, int i, int e) {
		letter = l;
		initPos = i;
		endPos = e;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
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
