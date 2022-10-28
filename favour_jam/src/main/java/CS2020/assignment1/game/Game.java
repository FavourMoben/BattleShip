package CS2020.assignment1.game;
import java.util.Scanner;	
import java.util.Random;
public class Game implements GameControls {
    PlayerGameGrid player;
    OpponentGameGrid opponent;
    Integer numberOfShips;
    Integer width;
    Integer height;
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
                    if (xInput.equals(shipX) && yInput.equals(shipY)) {
                        ship.setHits(ship.getHits() + 1);
                        this.opponent.gameGrid[shipX][shipY] = "X";
                        foundCoordinate = true;
                        message = "HIT " + ship.getName() + "!!!";
                        break;
                    }
                    if (foundCoordinate) break;
                }
            }
            if (!foundCoordinate)this.opponent.gameGrid[xInput][yInput] = "%";
            // here is the for of the bot
            System.out.println(message);
            Integer oppX = new Integer(new Random().nextInt(this.height - 2));
            Integer oppY = new Integer(new Random().nextInt(this.width - 2));
            message = "MISS !!!";
            foundCoordinate = false;
            System.out.println("opponent is attacking: ");
            for (AbstractBattleShip ship: this.player.ships) {
                int[][] shipCoordinates = ship.getShipCoordinates();
                for (int[] coors: shipCoordinates) {
                    int shipX = coors[0];
                    int shipY = coors[1];
                    if (oppX.equals(shipX) && oppY.equals(shipY)) {
                        ship.setHits(ship.getHits() + 1);
                        this.player.gameGrid[shipX][shipY] = "X";
                        foundCoordinate = true;
                        message = "HIT " + ship.getName() + "!!!";
                        break;
                    }
                if (foundCoordinate) break;
                }
            }
            if (!foundCoordinate)this.player.gameGrid[oppX][oppY] = "%";
            System.out.println(message);
            this.player.printGrid();
            this.opponent.printGrid();
        }
        catch (Exception | Error e) {
            System.out.println("wrong Input! ");
            }
    }
    
	public boolean checkVictory (){
        // check victory for player
        int playerAllShipsCount = 0;
        boolean gameEnded = false;
        for (AbstractBattleShip ship: this.player.ships) {
            if (ship.getHits() >= 3) playerAllShipsCount++;
        }
        if (playerAllShipsCount >= this.numberOfShips){
            System.out.println("You have lost!");
            gameEnded = true;
            return gameEnded;
        }
        //check victory for opponent
        int opponentAllShipsCount = 0;
        for (AbstractBattleShip ship: this.opponent.ships) {
            if (ship.getHits() >= 3) opponentAllShipsCount++;
        }
        if (opponentAllShipsCount >= this.numberOfShips){
            System.out.println("You have won!");
            gameEnded = true;
            return gameEnded;
        }

        return gameEnded;
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