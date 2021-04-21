package model;

public class Player {
	private String token;
	private int pos;
	
	public Player(String t) {
		token = t;
		pos = 1; // Ya que empieza en la primera celda 
	}

	public String getToken() {
		return token;
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
	
	@Override
	public String toString() {
		String details = "Token: " + token + "\nPos: " + pos;
		return details;
	}
	
}
