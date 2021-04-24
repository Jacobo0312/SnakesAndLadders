package model;

public class Player {

	// For the binary tree
	private Player right;
	private Player left;
	
	// Game
	
	private String nickName;
	
	private String token;
	
	private int pos;
	
	private int moves;
	
	private int score;
	
	private Player nextPlayer;
	
	public Player(String t) {
		nickName = "";
		token = t;
		pos = 1; // Ya que empieza en la primera celda
		moves = 0;
		score = 0;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getToken() {
		return token;
	}

	public Player getRight() {
		return right;
	}

	public void setRight(Player right) {
		this.right = right;
	}

	public Player getLeft() {
		return left;
	}

	public void setLeft(Player left) {
		this.left = left;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}


	public Player getNextPlayer() {
		return this.nextPlayer;
	}

	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Override
	public String toString() {
		String details = "Nickname: " + nickName + "\nToken: " + token + "\nScore: " + score;
		return details;
	}


	public int getMoves() {
		return this.moves;
	}

	public void setMoves(int moves) {
		this.moves = moves;
	}

	
}
