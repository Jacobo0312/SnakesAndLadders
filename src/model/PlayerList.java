package model;

public class PlayerList {
	
	private Player head;
	private Player tail;
	private int length;
	
	public PlayerList() {
		head = null;
		tail = null;
		length = 0;
	}
	
	public void append(Player p) {
		//System.out.println("Inside!");
		if (head == null) {
			head = p;
			tail = head;
			head.setNextPlayer(tail);
			tail.setNextPlayer(head);
			//System.out.println("Changed Head!");
		} else {
			tail.setNextPlayer(p);
			tail = p;
			tail.setNextPlayer(head);
			//System.out.println("Changed Tail!");
		}
		setLength(getLength() + 1);
		//System.out.println(toString());
	}

	public Player getHead() {
		return head;
	}

	public void setHead(Player head) {
		this.head = head;
	}

	public Player getTail() {
		return tail;
	}

	public void setTail(Player tail) {
		this.tail = tail;
	}
	
	/*@Override
	public String toString( ) {
		String nodes = "";
		Player current = head;
		int times = 0;
		while (current != null && times < length) {
			nodes += current.getToken() + " ";
			times++;
		}
		return nodes;
	}*/
	@Override
	public String toString() {
		String nodes = "";
		int i = 0;
		nodes = toStringList(head, i);
		return nodes;
	}
	
	private String toStringList(Player current, int top) {
		if (top == length) {
			return "";
		} else {			
			return current.getToken() + " " + toStringList(current.getNextPlayer(), top + 1);
		}
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
