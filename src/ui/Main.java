
package ui;

import java.util.Scanner;

import model.GameTable;

public class Main {
	
    private Scanner sc;
    private GameTable game;

    public Main() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("******** Starting  *********");
        Main game = new Main();

        int option = 0;

        whileRecursive(game, option);

    }

    private int showMenu() {
        System.out.println("Select an option");
        System.out.println("1.PLAY GAME\n2.SCORES\n3.EXIT");
        int option = Integer.parseInt(sc.nextLine());
        return option;
    }

    private void executeOperation(int option) {
        switch (option) {
        case 1:
            startGame();
            break;
        case 2:
            System.out.println("Scores");
            break;

        default:
            break;
        }

    }

    private static void whileRecursive(Main game, int option) {

        if (option != 3) {
            option = game.showMenu();
            game.executeOperation(option);
            whileRecursive(game, option);
        }

    }

    private void startGame() {
        System.out.println("S T A R T I N G  N E W  G A M E...\n");
        System.out.println("Insert a single string like the next example:\n" +
        "5 4 2 3 #%*\n" + "In which a matrix 5 x 4 is created with:\n2 snakes, 3 ladders and 3 players, one for each symbol (Max 9 players)");
        String entry = sc.nextLine();
        String [] parts = entry.split(" ");
        int rows = Integer.parseInt(parts[0]);
        int cols = Integer.parseInt(parts[1]);
        /*int snakes = Integer.parseInt(parts[2]);
        int ladders = Integer.parseInt(parts[3]);*/
        String players = parts[4];
        game = new GameTable(rows, cols, players);
        System.out.println(game);

        //For add letter of the snakes 
        //System.out.println((char) ('A'+1));

        System.out.println(game.getFirstPlayer());
    }

}
