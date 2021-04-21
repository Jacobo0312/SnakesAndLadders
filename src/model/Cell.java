package model;

public class Cell {
	private int val; // Valor o numero que va a tener la celda
	private String elements;

	private int row;
	private int col;


	private Cell prev;
	private Cell next;
	private Cell up;
	private Cell down;

	
	public Cell (String el,int row, int col) {
		this.row=row;
		this.col=col;
		val = 0;
		elements = el;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
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

	public String toString(){
		//return ("("+row+","+col+")");
		return "("+val+ elements + ")'";
	}


}
