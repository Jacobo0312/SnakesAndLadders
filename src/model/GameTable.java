package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class GameTable {

    private Cell first;
    private int rows;
    private int cols;
    private int val;
    private int numPlayers;
    private Player turn;
    private PlayerList playerList;
    private boolean playerWon;

    // Characters and its getters and setters

    public GameTable(int n, int m, String players) {
        this.rows = n;
        this.cols = m;
        this.val = (n * m);
        this.numPlayers = players.length();
        playerList = new PlayerList();
        playerWon = false;
        startGame(players);

    }

    public String move(){
        int dices=rollDices();
        int turnPos=turn.getPos();
        Cell actualCell=searchCell(turnPos, first);


        String token=turn.getToken();

        String cellElements = actualCell.getPlayers();
        /*int index = cellElements.indexOf(token);*/
        // Revisar este método Substring
        StringBuilder test = new StringBuilder(cellElements);
        /*System.out.println("STRING ANTES: " + test);*/
        int indexTwo = test.indexOf(token);
        if (indexTwo >= 0) {
        	test = test.deleteCharAt(indexTwo);
        	/*System.out.println("\nSTRING DESPUES: " + test);*/
        }
        /*cellElements = cellElements.substring(0, index) + cellElements.substring(index + 1, cellElements.length());*/
        actualCell.setPlayers(test.toString());

        int newPos=dices+turnPos;
        turn.setMoves(turn.getMoves() + 1);
        turn.setPos(newPos);
        if (newPos>=val){
        	playerWon = true;
        	// Esto esta hardcodeado, pero es para que tenga el token del ganador
        	Cell finalCell = searchCell(val, first);
        	finalCell.setPlayers(turn.getToken());
        	try {
				saveWinners(turn, "Mirrorbeast");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
            return "Dices "+dices+" Player "+token+ "WIN";
        } else{
            Cell moveCell=searchCell(newPos, first);
            moveCell.setPlayers(moveCell.getPlayers()+token);
    
            turn=turn.getNextPlayer();
            return "Dices "+dices+" Player "+token;
        }
    }
    
    public void saveWinners(Player winner, String nickName) throws FileNotFoundException {
    	PrintWriter pw = new PrintWriter("./data/winners.csv");
    	pw.println("Nickname: " + nickName + ", Moves: " + winner.getMoves() + ", Token: " + winner.getToken());
    	pw.close();
    }
    
    public String returnScores() {
    	String scores = "";
    	try {
    		BufferedReader br = new BufferedReader(new FileReader("./data/winners.csv"));
			scores = returnScores(br.readLine(), br);
		} catch (IOException e) {
			scores = "NO SCORES!";
			//e.printStackTrace();
		}
    	return scores;
    }
    
    private String returnScores(String line, BufferedReader br) throws IOException {
    	String ln = line;
    	if (ln == null) {
    		return ""; 
    	} else {
    		return ln + returnScores(br.readLine(), br);
    	}
    }

    public void assignPlayers(int num, String characters/*,Player player*/) {
        if (num == 0) {
            //System.out.println("Inside default case");
            // If it is a default case
        	System.out.println("RESULTADO:\n" + playerList.toString());
        } else {
            Player player = new Player(characters.substring(0, 1));
            //System.out.println(player);
            //System.out.println(firstPlayer);
            playerList.append(player);
            //System.out.println(playerList.getHead().getToken() + " Cola: " + playerList.getTail().getToken());
            //System.out.println(pl);
            characters = characters.substring(1, num);
            assignPlayers(num - 1, characters/*,player.getNextPlayer()*/);
        }
    }

    public void startGame(String players) {
        first = new Cell(0, 0);
        // System.out.println("Se creo first");
        createRow(0, 0, first);
        assignPlayers(numPlayers, players/*,firstPlayer*/);
        // System.out.println(numPlayers);
        addVal(first);
        val=rows*cols;
        setupGame(players);

    }

    private void setupGame(String players){
        Cell cell1=searchCell(1, first);
        cell1.setPlayers(players);
        turn=getPlayerList().getHead();
    }

    private void createRow(int i, int j, Cell cell) {
        // System.out.println("Create row con la fila: "+i);
        createCol(i, j + 1, cell, cell.getUp());
        if (i + 1 < rows) {
            Cell current = new Cell(i + 1, j);
            current.setUp(cell);
            cell.setDown(current);
            createRow(i + 1, j, current);
        }
    }

    private void createCol(int i, int j, Cell cell, Cell prevRow) {
        if (j < cols) {
            // System.out.println("En create col con la columna: "+j);
            Cell current = new Cell(i, j);
            current.setPrev(cell);
            cell.setNext(current);

            if (prevRow != null) {
                prevRow = prevRow.getNext();
                current.setUp(prevRow);
                prevRow.setDown(current);

            }
            createCol(i, j + 1, current, prevRow);
        }
    }

    // ToString with horizontal link
    public String toString() {
        String table = "";

        table = toStringRow(first);

        return table;

    }

    private String toStringRow(Cell cell) {
        String message = "";

        if (cell != null) {
            message = toStringCol(cell) + "\n";
            message += toStringRow(cell.getDown());
        }
        return message;
    }

    private String toStringCol(Cell cell) {

        String message = "";

        if (cell != null) {
            message = cell.toString()/* + "\t" */;
            message += toStringCol(cell.getNext());
        }

        return message;
    }

    //-----------------------------------------

    // Verify vertical link
    public String toString2() {
        String table = "";

        table = toStringCol2(first);

        return table;

    }

    private String toStringCol2(Cell cell) {
        String message = "";

        if (cell != null) {
            message = toStringRow2(cell) + "\n";
            message += toStringCol2(cell.getDown());
        }
        return message;
    }

    private String toStringRow2(Cell cell) {

        String message = "";

        if (cell != null) {
            message = cell.toString();
            message += toStringRow2(cell.getNext());
        }

        return message;
    }

    //-------------------------------------------------------

    private void addVal(Cell cell) {
        cell.setVal(val--);
        // System.out.println(toString());
        if (val < 0) {
            // Finish recursion
        } else if (cell.getNext() != null && ((cell.getPrev() == null && cell.getNext().getVal() == 0)
                || (cell.getPrev() != null && cell.getNext().getVal() == 0))) {
            // System.out.println("Entro a next");
            addVal(cell.getNext());
        } else if (cell.getPrev() != null && ((cell.getNext() == null && cell.getPrev().getVal() == 0)
                || (cell.getPrev().getVal() == 0 && cell.getNext().getVal() != 0))) {
            // System.out.println("Entro a prev");
            addVal(cell.getPrev());
        } else if (cell.getDown() != null
                && ((cell.getNext() == null && cell.getPrev().getVal() != 0 && cell.getPrev() != null)
                        || (cell.getPrev() == null && cell.getNext().getVal() != 0))) {
            // System.out.println("Entro a down");
            addVal(cell.getDown());
        }

    }

    // Function for cell search with your value

    public Cell searchCell(int valCell, Cell cell) {
    		if (valCell == cell.getVal()) {
    			return cell;
    		} else if (cell.getRow() % 2 == 0) {
    			if (cell.getNext() == null) {
    				return searchCell(valCell, cell.getDown());
    			} else {
    				// System.out.println("Next");
    				return searchCell(valCell, cell.getNext());
    			}
    		} else {
    			if (cell.getPrev() == null) {
    				return searchCell(valCell, cell.getDown());
    			} else {
    				// System.out.println("Prev");
    				return searchCell(valCell, cell.getPrev());
    			}
    		}
    }

    // Function used to do test
    public Cell getFirst() {
        return this.first;
    }

    public PlayerList getPlayerList() {
        return this.playerList;
    }
    // ---------------------

    // Add snakes and ladders

    public void addSnake() { // Should be private

    }

    public void addLadder(String letter) {

        // Math.floor(Math.random()*(N-M+1)+M); // Value between M1 and N include both
        int init = (int) Math.floor(Math.random() * (((rows * cols) - cols) - 2 + 1) + 2);
        int end = (int) Math.floor(Math.random() * ((rows * cols) - (init + cols) + 1) + init + cols);
        //Cambiar rows por cols =val
        Cell cell = searchCell(init, first);
        cell.setElements(letter);
        cell = searchCell(end, first);
        cell.setElements(letter);

    }




    // Roll dices

    public int rollDices() {
        int dice1 = (int) (Math.random() * 6 + 1);
        //int dice2 = (int) (Math.random() * 6 + 1);

        return dice1;
    }

	public boolean isPlayerWon() {
		return playerWon;
	}

}
