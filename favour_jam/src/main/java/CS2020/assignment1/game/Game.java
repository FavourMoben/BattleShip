package CS2020.assignment1.game;
import java.util.Scanner;	
import java.util.Random;
public class Game implements GameControls {
    PlayerGameGrid player;
    OpponentGameGrid opponent;
    Integer numberOfShips;
    Integer width;
    Integer height;
    public static void main(String[] args) {
        Game game = new Game(10,10,4);
        game.player.printGrid();
        game.opponent.printGrid();
        game.opponent.ships[0].setHits(3);
        boolean gameIsRunning = true;
        while (gameIsRunning){
            System.out.println("Please enter a position you wish to attack: ");
            String input = new Scanner(System.in).next().trim();
            game.playRound(input);
            }
    }
    Game (int width,int height,int numberOfShips) {
        this.width = width;
        this.height = height;
        this.numberOfShips = numberOfShips;
        this.player = new PlayerGameGrid(width,height,numberOfShips);
        this.opponent = new OpponentGameGrid(width,height,numberOfShips);
    }
    public void playRound (String input){
        String message = "MISS !!!";
            try {
                String[] coordinates = input.split(",");
                Integer xInput = new Integer(Integer.parseInt(coordinates[0]));
                Integer yInput = new Integer(Integer.parseInt(coordinates[1]));
                System.out.println("Player is attacking: ");
                boolean foundCoordinate = false;
                for (AbstractBattleShip ship: this.opponent.ships) {
                    int[][] shipCoordinates = ship.getShipCoordinates();
                    for (int[] coors: shipCoordinates) {
                        int shipX = coors[0];
                        int shipY = coors[1];
                        // System.out.printf("shipX: %d shipY: %d\n",shipX,shipY);
                        if (xInput.equals(shipX) && yInput.equals(shipY)) {
                            this.opponent.gameGrid[shipX][shipY] = "X";
                            foundCoordinate = true;
                            message = "HIT " + ship.getName() + "!!!";
                            break;
                        }
                    if (foundCoordinate) break;
                    }
                if (!foundCoordinate)this.opponent.gameGrid[xInput][yInput] = "%";
                // here is the for of the bot

                System.out.println("opponent is attacking: ");
                Integer oppX = new Integer(new Random.nextInt(this.height - 2));
                Integer oppY = new Integer(new Random.nexInt(this.width - 2));
                



                System.out.println(message);
                this.player.printGrid();
                this.opponent.printGrid();
                }
            }catch (Exception | Error e) {
                System.out.println("wrong Input! ");
            }
    }
	public boolean checkVictory (){
        // check victory for player
        int playerAllShipsCount = 0;
        for (AbstractBattleShip ship: this.player.ships) {
            if (ship.getHits() >= 3) playerAllShipsCount++;
        }
        if (playerAllShipsCount >= this.numberOfShips) System.out.println("You have lost!");
        //check victory for opponent
        int opponentAllShipsCount = 0;
        for (AbstractBattleShip ship: this.opponent.ships) {
            if (ship.getHits() >= 3) opponentAllShipsCount++;
        }
        if (opponentAllShipsCount >= this.numberOfShips) System.out.println("You have won!");

        return playerAllShipsCount >= this.numberOfShips;
    }
	public void exitGame (String input){
        if (input.toLowerCase().equals("exit")) {
            System.out.println("Exiting game – thank you for playing");
        }else {
            System.out.println("Sorry I do not understand");
        }
    }
	public AbstractGameGrid getPlayersGrid (){
        return this.player;
    }
	
	public AbstractGameGrid getOpponentssGrid (){
        return this.opponent;
    }
}