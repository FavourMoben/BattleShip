package CS2020.assignment1.game;
import java.util.Scanner;	
public class RunGame {
    public static void main(String[] args) {
        try {
            System.out.printf("\t\tWelcome to the Battle Ship Game\n");
            int width = Integer.parseInt(args[0]);
            int height = Integer.parseInt(args[1]);
            int numberOfShips = Integer.parseInt(args[2]);
            Game game = new Game(width, height, numberOfShips);
            boolean gameIsRunning = true;
            while (gameIsRunning){
                System.out.println("Please enter a position you wish to attack:\n ");
                String input = new Scanner(System.in).next().trim();
                if (input.equals("exit")){ 
                    game.exitGame(input);
                    break;   
                }
                game.playRound(input);
                boolean gameEnded = game.checkVictory();
                if (gameEnded) {
                    gameIsRunning = false;
                    game.exitGame("exit");
                }
            }
        }catch (Exception e) {
            System.out.println("Incorrect input!! Or no arguments passed");
        }
    }
}