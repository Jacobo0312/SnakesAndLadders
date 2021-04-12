
package ui;

import java.util.Scanner;

import model.GameTable;

public class Main {

    private Scanner sc;

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
            GameTable game=new GameTable(5, 5);
            System.out.println(game);

            //For check vertical link
            //System.out.println(game.toString2());

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

}
