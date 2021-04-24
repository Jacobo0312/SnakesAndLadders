package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class GameTable {

    private Cell first;
    private int rows;
    private int cols;
    private int val;
    private int ladders;
    private int snakes;
    private int numPlayers;
    private Player turn;
    private PlayerList playerList;
    private boolean playerWon;
    private ScoresTree scores;

    // Characters and its getters and setters

    public GameTable(int n, int m, String players, int ladders, int snakes) {
        this.rows = n;
        this.cols = m;
        this.val = (n * m);
        this.numPlayers = players.length();
        playerList = new PlayerList();
        playerWon = false;
        this.ladders = ladders;
        this.snakes = snakes;
        scores = new ScoresTree();
        startGame(players);

    }

    public String move() {
        int dices = rollDices();
        int turnPos = turn.getPos();
        Cell actualCell = searchCell(turnPos, first);

        String token = turn.getToken();

        String cellElements = actualCell.getPlayers();
        /* int index = cellElements.indexOf(token); */
        // Revisar este metodo Substring
        StringBuilder test = new StringBuilder(cellElements);
        /* System.out.println("STRING ANTES: " + test); */
        int indexTwo = test.indexOf(token);
        if (indexTwo >= 0) {
            test = test.deleteCharAt(indexTwo);
            /* System.out.println("\nSTRING DESPUES: " + test); */
        }
        /*
         * cellElements = cellElements.substring(0, index) +
         * cellElements.substring(index + 1, cellElements.length());
         */
        actualCell.setPlayers(test.toString());

        int newPos = dices + turnPos;
        turn.setMoves(turn.getMoves() + 1);
        turn.setPos(newPos);
        if (newPos >= val) { // Creo que deberia ser mayor,no igual
            playerWon = true;
            // Esto esta hardcodeado, pero es para que tenga el token del ganador
            Cell finalCell = searchCell(val, first);
            finalCell.setPlayers(turn.getToken());
            turn.setScore(turn.getMoves() * val);
            scores.addScore(turn);
            return "Dices " + dices + " Player " + token + " Moves: " + turn.getMoves() + "Score: " + turn.getScore()
                    + " WIN";
        } else{
            Cell moveCell = searchCell(newPos, first);

            if (moveCell.hasElement()){
                
                int position=moveElement(moveCell);
                turn.setPos(position);
                moveCell=searchCell(position, first);  
            }
            moveCell.setPlayers(moveCell.getPlayers() + token);
            turn = turn.getNextPlayer();
            return "El jugador " + token + " ha lanzado el dado y obtuvo un puntaje  " + dices;
        }
    }

    private int moveElement(Cell cell){
        int position;


        if (cell.getLadder()!=null){
            position=cell.getLadder().getEndPos();
        }else{
            position=cell.getSnake().getInitPos();
        }
        return position;
    }

    public void saveWinners(Player winner, String nickName) throws IOException {
    	String result = "";
    	if (new File("./data/winners.csv").exists()) {
    		BufferedReader br = new BufferedReader(new FileReader("./data/winners.csv"));    		
    		result = readWinners(br.readLine(), br);
    	}
        PrintWriter pw = new PrintWriter("./data/winners.csv");
        if (!result.equals("")) {
        	pw.println(result);
        }
        winner.setNickName(nickName);
        pw.println(winner.getNickName() + "," + winner.getToken() + "," + winner.getMoves() + "," + winner.getScore());
        pw.close();
    }
    
    public String readWinners(String line, BufferedReader br) throws IOException {
    	if (line == null) {
    		return "";
    	} else {
    		String lineBefore = br.readLine();
    		if (lineBefore == null || lineBefore.equals("")) {
    			return line + readWinners(lineBefore, br);
    		} else {    			
    			return line + "\n" + readWinners(lineBefore, br);
    		}
    	}
    }
    
    public String returnClassScores() {
    	return scores.prepareToString();
    }

    public String returnScores() {
        String scores = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("./data/winners.csv"));
            scores = returnScores(br.readLine(), br);
        } catch (IOException e) {
            scores = "NO SCORES!";
            // e.printStackTrace();
        }
        return scores;
    }

    private String returnScores(String line, BufferedReader br) throws IOException {
        String ln = line;
        if (ln == null) {
            return "";
        } else {
        	String before = br.readLine();
        	if (before == null || before.equals("")) {
        		return ln + returnScores(before, br);
        	} else {        		
        		return ln + "\n" + returnScores(before, br);
        	}
        }
    }

    public void assignPlayers(int num, String characters/* ,Player player */) {
        if (num == 0) {
            // System.out.println("Inside default case");
            // If it is a default case
            System.out.println("RESULTADO:\n" + playerList.toString());
        } else {
            Player player = new Player(characters.substring(0, 1));
            // System.out.println(player);
            // System.out.println(firstPlayer);
            playerList.append(player);
            // System.out.println(playerList.getHead().getToken() + " Cola: " +
            // playerList.getTail().getToken());
            // System.out.println(pl);
            characters = characters.substring(1, num);
            assignPlayers(num - 1, characters/* ,player.getNextPlayer() */);
        }
    }

    public void startGame(String players) {
        try {
			scores.prepareLoadScores();
		} catch (IOException e) {
			e.printStackTrace();
		}
        first = new Cell(0, 0);
        // System.out.println("Se creo first");
        createRow(0, 0, first);
        assignPlayers(numPlayers, players/* ,firstPlayer */);
        // System.out.println(numPlayers);

        Cell firstCell = firstCell(first);
        addVal(firstCell, 1);
        // val = rows * cols;
        setupGame(players);

    }

    // Return first cell (ROWS,0)
    private Cell firstCell(Cell cell) {
        if (cell.getDown() != null) {
            return firstCell(cell.getDown());
        } else {
            return cell;
        }
    }

    private void setupGame(String players) {
        Cell cell1 = searchCell(1, first);
        cell1.setPlayers(players);
        turn = getPlayerList().getHead();

        addLadders(1);
        addSnakes(0);
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

    // -----------------------------------------

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
            message = cell.toString2();
            message += toStringRow2(cell.getNext());
        }

        return message;
    }

    // -------------------------------------------------------

    private void addVal(Cell cell, int valCell) {
        // System.out.println(cell.getRow()+" "+cell.getCol());
        cell.setVal(valCell);
        // System.out.println(toString());
        if (valCell >= val) {
            // Finish recursion
        } else if (cell.getNext() != null && ((cell.getPrev() == null && cell.getNext().getVal() == 0)
                || (cell.getPrev() != null && cell.getNext().getVal() == 0))) {
            // System.out.println("Entro a next");
            addVal(cell.getNext(), valCell + 1);
        } else if (cell.getPrev() != null && ((cell.getNext() == null && cell.getPrev().getVal() == 0)
                || (cell.getPrev().getVal() == 0 && cell.getNext().getVal() != 0))) {
            // System.out.println("Entro a prev");
            addVal(cell.getPrev(), valCell + 1);
        } else if (cell.getUp() != null
                && ((cell.getNext() == null && cell.getPrev().getVal() != 0 && cell.getPrev() != null)
                        || (cell.getPrev() == null && cell.getNext().getVal() != 0))) {
            // System.out.println("Entro a down");
            addVal(cell.getUp(), valCell + 1);
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

    private void addSnakes(int letter) {
        // Math.floor(Math.random()*(N-M+1)+M); // Value between M and N include both
        int init = (int) Math.floor(Math.random() * ((val - cols) - 2 + 1) + 2);
        int end = (int) Math.floor(Math.random() * ((val-1) - (init + cols) + 1) + init + cols);

        Cell cellInit = searchCell(init, first);
        Cell cellEnd = searchCell(end, first);

        if (letter >= snakes) {

        } else if (cellInit.hasElement() || cellEnd.hasElement()) {
            addSnakes(letter);
    
        } else {
            String valSnake = Character.toString((char) ('A' + letter));
            Snake newSnake=new Snake(valSnake, init, end);
            cellInit.setSnake(newSnake);
            cellEnd.setSnake(newSnake);
            addSnakes(letter + 1);  
        }

    }

    private void addLadders(int number) {

        // Math.floor(Math.random()*(N-M+1)+M); // Value between M and N include both
        int init = (int) Math.floor(Math.random() * ((val - cols) - 2 + 1) + 2);
        int end = (int) Math.floor(Math.random() * (val - (init + cols) + 1) + init + cols);

        Cell cellInit = searchCell(init, first);
        Cell cellEnd = searchCell(end, first);

        if (number > ladders) {

        } else if (cellInit.hasElement() || cellEnd.hasElement()) {


            addLadders(number);

        } else {
            Ladder newLadder=new Ladder(number, init, end);
            cellInit.setLadder(newLadder);
            cellEnd.setLadder(newLadder);
            addLadders(number + 1);
        }

    }

    // Roll dices

    public int rollDices() {
        int dice1 = (int) (Math.random() * 6 + 1);
        // int dice2 = (int) (Math.random() * 6 + 1);

        return dice1;
    }

    public boolean isPlayerWon() {
        return playerWon;
    }
    
    public Player getTurn() {
    	return turn;
    }

}
