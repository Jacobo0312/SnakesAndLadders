package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ScoresTree {
	private Player root;
	
	public ScoresTree() {
		root = null;
	}
	
	public void addScore(Player pl) {
		if (root == null) {
			root = pl;
		} else {
			addScore(root, pl);
		}
		
	}
	
	private void addScore(Player current, Player pl) {
		if (pl.getScore() <= current.getScore()) {
			if (current.getLeft() != null) {
				addScore(current.getLeft(), pl);
			} else {
				current.setLeft(pl);
			}
		} else {
			if (current.getRight() != null) {
				addScore(current.getRight(), pl);
			} else {
				current.setRight(pl);
			}
		}
	}
	
	/*public void saveScores() {
		// Para guardar tendría que tener en consideración preorden, puesto que mantendría de mejor forma el arbol para importarlo después
		// Serializar no resultó ser bueno si no son arrays o arraylist
	}*/
	
	// Prepara el método
	public void prepareLoadScores() throws IOException {
		if (new File("./data/winners.csv").exists()) {			
			BufferedReader br = new BufferedReader(new FileReader("./data/winners.csv"));
			loadScores(br.readLine(), br);
			br.close();
		}
	}
	
	// Va leyendo recursivamente y añadiendo los nodos.
	public void loadScores(String line, BufferedReader br) throws IOException {
		if (line == null) {
			// DO nothing
		} else {
			try {				
				String [] parts = line.split(",");
				Player pl = new Player(parts[1]);
				pl.setNickName(parts[0]);
				pl.setMoves(Integer.parseInt(parts[2]));
				pl.setScore(Integer.parseInt(parts[3]));
				addScore(pl);
				loadScores(br.readLine(), br);
			} catch (ArrayIndexOutOfBoundsException e) {
				// Do nothing
			}			
		}
	}
	
	public String prepareToString() {
		String result = "";
		result = inOrder(root);		
		//System.out.println(result);
		return result;
	}
	
	public String inOrder(Player current) {
		if (current == null) {
			return "";
		} else {
			
			return inOrder(current.getLeft()) + "\n" + current.toString() +"\n" + inOrder(current.getRight());
		}
	}
	
	public Player returnNode(Player pl) {
		return pl;
	}
	// Tendría que imprimirlos en Inorden, si no estoy mal.
	// Puesto que, Inorden: Izquierda, Raíz, Derecha

	public Player getRoot() {
		return root;
	}

	public void setRoot(Player root) {
		this.root = root;
	}
}
