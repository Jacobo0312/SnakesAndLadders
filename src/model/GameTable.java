package model;

public class GameTable {

    private Cell first;
    private int rows;
    private int cols;
    private int val;

    public GameTable(int n, int m) {
        this.rows = n;
        this.cols = m;
        this.val = (n * m);
        startGame();
        addVal(first);

    }

    public void startGame() {
        // int index=n*m;
        // System.out.println("Se crea la matriz");
        first = new Cell("", 0, 0);
        // System.out.println("Se creo first");
        createRow(0, 0, first);

    }

    private void createRow(int i, int j, Cell cell) {
        // System.out.println("Create row con la fila: "+i);
        createCol(i, j + 1, cell, cell.getUp());
        if (i + 1 < rows) {
            Cell current = new Cell("", i + 1, j);
            current.setUp(cell);
            cell.setDown(current);
            createRow(i + 1, j, current);
        }
    }

    private void createCol(int i, int j, Cell cell, Cell prevRow) {
        if (j < cols) {
            // System.out.println("En create col con la columna: "+j);
            Cell current = new Cell("", i, j);
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
            message = cell.toString();
            message += toStringCol(cell.getNext());
        }

        return message;
    }

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
                //System.out.println("Next");
                return searchCell(valCell, cell.getNext());
            }
        } else {
            if (cell.getPrev() == null) {
                return searchCell(valCell, cell.getDown());
            } else {
                //System.out.println("Prev");
                return searchCell(valCell, cell.getPrev());
            }
        }
    }

    public Cell getFirst() {
        return this.first;
    }

}
