package model;

public class Snake {
	private char letter;
	private int initPos;
	private int endPos;
	// Asegurarse de que initPos siempre es mayor a endPos;
	
	public Snake(char l, int i, int e) {
		letter = l;
		initPos = i;
		endPos = e;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
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
	
}
