
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

        whileRecursive(game,option);

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

    private static void whileRecursive(Main game,int option){

        if (option!=3){
            option = game.showMenu();
            game.executeOperation(option);
            whileRecursive(game, option);
        }

    }


    private void startGame(){
        System.out.println("S T A R T I N G  N E W  G A M E...\n");
        System.out.println("Insert the number of rows:\n");
        int rows = Integer.parseInt(sc.nextLine());
        System.out.println("Insert the number of columns:\n");
        int cols = Integer.parseInt(sc.nextLine());
        game = new GameTable(rows, cols);
        System.out.println(game);
        //System.out.println("For check vertical link");
        //System.out.println(game.toString2());
        

    }


}
