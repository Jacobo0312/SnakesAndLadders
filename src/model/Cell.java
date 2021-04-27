package model;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

public class Cell {
	private int val; // Valor o numero que va a tener la celda
	private String players;
	private int run;
	
	@FXML
	private Label lb;

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
		if (run == 0) {			
			lb.setText(toString());
		}
	}

	private int row;
	private int col;


	private Cell prev;
	private Cell next;
	private Cell up;
	private Cell down;
	private Snake snake;
	private Ladder ladder;

	
	public Cell (int row, int col, int run) {
		this.run = run;
		this.row=row;
		this.col=col;
		val = 0;
		players = "";
		snake=null;
		ladder=null;
		if (run == 0) {			
			lb = new Label(toString());
			lb.setTextFill(Paint.valueOf("#ffffff"));
		}
	}
	
	public Label getLabel() {
		return lb;
	}
	
	public void setLabel(String s) {
		lb.setText(s);
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
		if (run == 0) {			
			lb.setText(toString());
		}
	}

	

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return this.col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Cell getPrev() {
		return this.prev;
	}

	public void setPrev(Cell prev) {
		this.prev = prev;
	}

	public Cell getNext() {
		return this.next;
	}

	public void setNext(Cell next) {
		this.next = next;
	}

	public Cell getUp() {
		return this.up;
	}

	public void setUp(Cell up) {
		this.up = up;
	}

	public Cell getDown() {
		return this.down;
	}

	public void setDown(Cell down) {
		this.down = down;
	}


	public Snake getSnake() {
		return this.snake;
	}

	public void setSnake(Snake snake) {
		this.snake = snake;
		if (run == 0) {			
			lb.setText(toString());
		}
	}

	public Ladder getLadder() {
		return this.ladder;
	}

	public void setLadder(Ladder ladder) {
		this.ladder = ladder;
		if (run == 0) {			
			lb.setText(toString());
		}
	}

	public Boolean hasElement(){
		Boolean hasElement=false;
		if (snake!=null || ladder!=null){
			hasElement=true;
		}
		return hasElement;
	}
	

	public String toString(){

		String element="";
		if (snake!=null){
			element=snake.toString();
		}else if (ladder!=null){
			element=ladder.toString();
		}
		//return ("("+row+","+col+")");
		if (run == 0) {			
			return val + " " + element + " " + players;
		} else {
			return " ["+element+"  "+players+"] ";
		}
	}
	
	public String getElement() {
		String element="";
		if (snake!=null){
			element=snake.toString();
		}else if (ladder!=null){
			element=ladder.toString();
		}
		return element;
	}


	public String toString2(){

		String element="";
		if (snake!=null){
			element=snake.toString();
		}else if (ladder!=null){
			element=ladder.toString();
		}
		return (" ["+val+" "+element+"] ");
	}



}
