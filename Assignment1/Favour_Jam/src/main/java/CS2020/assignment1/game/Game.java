package CS2020.assignment1.game;
import java.util.Scanner;	
import java.util.Random;
public class Game implements GameControls {
    private final PlayerGameGrid player;
    private final OpponentGameGrid opponent;
    private final Integer numberOfShips;
    private final Integer width;
    private final Integer height;
    Game (int width,int height,int numberOfShips) {
        this.width = width;
        this.height = height;
        this.numberOfShips = numberOfShips;
        this.player = new PlayerGameGrid(width,height,numberOfShips);
        this.opponent = new OpponentGameGrid(width,height,numberOfShips);
    }
    public void playRound (String input){
        /* 
        This Function takes in an input for x and y coordinates on the gameGrid,
        Checks for ship attack for all ships in opponent instance ,
        Creates Random values an also Checks for ship attack for all ships in player instance
        */
        String message = "MISS!!!";
        try {
            String[] coordinates = input.split(",");
            Integer xInput = new Integer(Integer.parseInt(coordinates[0]));
            Integer yInput = new Integer(Integer.parseInt(coordinates[1]));
            System.out.println("Player is attacking: ");
            boolean foundCoordinate = false;
            for (AbstractBattleShip ship: this.opponent.ships) {
                if (ship.checkAttack(xInput,yInput)) {
                    this.opponent.gameGrid[xInput][yInput] = "X";
                    message = "HIT " + ship.getName() + "!!!";
                    foundCoordinate = true;
                    break;
                }
            }
            if (!foundCoordinate)this.opponent.gameGrid[xInput][yInput] = "%";
            // here is the for of the bot
            System.out.println(message);
            message = "MISS!!!";
            foundCoordinate = false;
            Integer oppX = new Integer(new Random().nextInt(this.height - 2));
            Integer oppY = new Integer(new Random().nextInt(this.width - 2));
            System.out.println("opponent is attacking: ");
            for (AbstractBattleShip ship: this.player.ships) {
                if (ship.checkAttack(oppX,oppY)) {
                    // ship.setHits(ship.getHits() + 1);
                    this.player.gameGrid[oppX][oppY] = "X";
                    message = "HIT " + ship.getName() + "!!!";
                    foundCoordinate = true;
                    break;
                }
            }
            if (!foundCoordinate)this.player.gameGrid[oppX][oppY] = "%";
            System.out.println(message);
            // printing player and opponent Grid
            this.player.printGrid();
            this.opponent.printGrid();
        }
        catch (Exception | Error e) {
            System.out.println("Incorrect input or IndexOutOfRange Error! try again ");
        }
    }
    
	public boolean checkVictory (){
        // check victory for player
        int playerDestroyedShipCount = 0;
        boolean gameEnded = false;
        for (AbstractBattleShip ship: this.player.ships) {
            if (ship.getHits() >= 3) playerDestroyedShipCount++;
        }
        if (playerDestroyedShipCount >= this.numberOfShips){
            System.out.println("You have lost!");
            gameEnded = true;
            return gameEnded;
        }
        // check victory for opponent
        int opponentDestroyedShipCount = 0;
        for (AbstractBattleShip ship: this.opponent.ships) {
            if (ship.getHits() >= 3) opponentDestroyedShipCount++;
        }
        if (opponentDestroyedShipCount >= this.numberOfShips){
            System.out.println("You have won!");
            gameEnded = true;
            return gameEnded;
        }
        return gameEnded;
    }
	public void exitGame (String input){
        // prints out the exit message when the game has been ended
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