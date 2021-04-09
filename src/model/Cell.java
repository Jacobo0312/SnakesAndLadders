package model;

public class Cell {
	private int val; // Valor o numero que va a tener la celda
	private String elements;
	
	public Cell (int v, String el) {
		val = v;
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
	
}
