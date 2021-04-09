
package ui;

import java.util.Scanner;

public class Main {

    private Scanner sc;

    public Main() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("******** Starting  *********");
        Main game = new Main();

        int option = 0;

        do {
            option = game.showMenu();
            game.executeOperation(option);

        } while (option != 3);

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
        System.out.println("Play game");
            break;
        case 2:
            System.out.println("Scores");
            break;

        default:
            break;
        }

    }

}