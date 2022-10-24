package CS2020.assignment1.game;
import java.util.Random;
public class GameGrid extends AbstractGameGrid {
    private int width;
    private int height;
    private int numberOfShips;
	GameGrid (int width,int height,int numberOfShips) {
        this.width = width;
        this.height = height;
        this.numberOfShips = numberOfShips;
        gameGrid = new String[this.height][this.width];
        initializeGrid();
        generateShips(4);
        for (var ship: ships) {
            placeShip(ship);
        }
    }
    //populate the grid with "." characters
    @Override
	public void initializeGrid (){
        for (int row=0; row<this.height;row++){
            for (int column = 0; column<this.width;column++) {
                gameGrid[row][column] = ".";
            }
        }
    }
	
	//this should place the ship on the grid using "*" symbol
    @Override
	public void placeShip (AbstractBattleShip ship) {
        // goto a random position on the board
        int randPosX = new Random.nextInt(this.width);
        int randPosY = new Random.nextInt(this.height);
        ship.shipCoordinates = new int [2][1];
        ship.shipCoordinates[0][0] = randPosX;
        ship.shipCoordinates[0][1] = randPosY;
        if (ship.getShipOrientation().equals("horizontal")) {
            for (int i=1; i<=2;i++) {
                // x = previousX + 1 or previousX - 1 depending on its position
                if (randPosX > this.width - 2){
                    int newX = ship.shipCoordinates[i - 1][0] - 1;
                }else{
                    int newX = ship.shipCoordinates[i - 1][0] + 1;
                }
                
                // y = y
                int newY = ship.shipCoordinates[i - 1][1]

                ship.shipCoordinates[i][0] = newX;
                ship.shipCoordinates[i][1] = newY;
            }
        }else {
            for (int i=1; i<=2;i++) {
                // x = x
                int newX = ship.shipCoordinates[i - 1][0];
                // y = previousY + 1 or previousY - 1 depending on its location
                int newY = ship.shipCoordinates[i - 1][1] + 1;
                if (randPosY > this.height - 2){
                    int newY = ship.shipCoordinates[i - 1][1] - 1;
                }else{
                    int newY = ship.shipCoordinates[i - 1][1] + 1;
                }
                ship.shipCoordinates[i][0] = newX;
                ship.shipCoordinates[i][1] = newY;
            }
        }
        isEmptySpace(ship.shipCoordinates) ? updateGrid(ship.shipCoordinates) : null;
    }
	
	//this should generate ships for both player and the opponent 
    @Override
	public void generateShips (int numberOfShips) {
        ships = new AbstractBattleShip[numberOfShips];
        for (int n = 0; n < numberOfShips; n++ ){
            String shipName = "Ship " + Integer.toString(n + 1);
            BattleShip newShip = new BattleShip(shipName);
            ships[n] = newShip;
        }

    }
    private boolean isEmptySpace(int [][] positions) {
        for (int i=0;i<positions.length;i++){
            for (int j=0;j<positions[0].length;i++){
                if (gameGrid[i][j].equals("*")) return false
            }
        }
    return true;
    }
    private void updateGrid(int [][] positions){
        for (int i=0;i<positions.length;i++){
            for (int j=0;j<positions[0].length;i++){
                gameGrid[i][j] = "*";
            }
        }        
    }
	
}
class Point {
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
