package model;


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
	
	public void saveScores() {
		// Para guardar tendr�a que tener en consideraci�n, puesto que mantendr�a de mejor forma el arbol para importarlo despu�s
		// Serializar no result� ser bueno si no son arrays o arraylist
	}
	
	// Prepara el m�todo
	public void prepareLoadScores() {
		
	}
	
	// Va leyendo recursivamente y a�adiendo los nodos.
	public void loadScores() {
		
	}
	
	// Tendr�a que imprimirlos en Inorden, si no estoy mal.
	// Puesto que, Inorden: Izquierda, Ra�z, Derecha

	public Player getRoot() {
		return root;
	}

	public void setRoot(Player root) {
		this.root = root;
	}
}
