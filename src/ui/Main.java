
package ui;

import java.io.FileNotFoundException;
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
            System.out.println(game.returnScores());
            System.out.println(game.returnClassScores());
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
        int snakes = Integer.parseInt(parts[2]);
        int ladders = Integer.parseInt(parts[3]);
        String players = parts[4];
        System.out.println("ladders: "+ladders);
        game = new GameTable(rows, cols, players,ladders,snakes);

        System.out.println(game.toString2());
        System.out.println(game);
        play();

    }


    public void play(){
        System.out.print("Presione enter para lanzar los dados: ");
                String line = sc.nextLine();
        if (line.isEmpty()){
            if (!game.isPlayerWon()){
                System.out.println(game.move());
                System.out.println(game);
                play();
            }else{
           winner();
            }

        }else if  (line.equals("num")){
            System.out.println("----TABLA INICIAL----");
            System.out.println(game.toString2());
            play();
        }else if (line.equals("menu")){
           //Exit
        }else if (line.equals("simul")){
            simulator();
        }else{
            System.out.println("Error ! NO INGRESE CARACTERES---PRESIONE ENTER----");
            play();
        }


    }


    private void simulator(){
        if (!game.isPlayerWon()){
            System.out.println(game.move());
            System.out.println(game);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            simulator();
        }else{
            winner();
        }       
    }


    private void winner(){
        System.out.println("El jugador " + game.getTurn().getToken() + " ha ganado el juego, con " + game.getTurn().getMoves() + " movimientos.\n");
        System.out.println("Ingrese el nombre o nickname del jugador");
        String line = sc.nextLine();
        try {
            game.saveWinners(game.getTurn(), line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
