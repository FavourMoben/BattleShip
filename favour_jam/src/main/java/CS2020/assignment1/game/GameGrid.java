package CS2020.assignment1.game;
//mvn exec:java -Dexec.mainClass="CS2020.assignment1.game.GameGrid"
import java.util.Random;
public class GameGrid extends AbstractGameGrid {
    private int width;
    private int height;
    private int numberOfShips;
    public static void main(String[] args) {
        GameGrid grid = new PlayerGameGrid(10,10,4);
        ((PlayerGameGrid) grid).printGrid();
        GameGrid grid2 = new OpponentGameGrid(10,10,4);
        ((OpponentGameGrid) grid2).printGrid();
    }
	GameGrid (int width,int height,int numberOfShips) {
        this.width = width;
        this.height = height;
        this.numberOfShips = numberOfShips;
        gameGrid = new String[this.height][this.width];
        initializeGrid();
        generateShips(this.numberOfShips);
        for (AbstractBattleShip ship: ships) {
            placeShip(ship);
        }
        // printGrid();
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
        while(true){
            int randPosX = new Random().nextInt(this.width-2);
            int randPosY = new Random().nextInt(this.height-2);
            ship.shipCoordinates = new int [3][2];
            ship.shipCoordinates[0][0] = randPosX;
            ship.shipCoordinates[0][1] = randPosY;
            if (ship.getShipOrientation().equals("vertical")) {
                for (int i=1; i<=2;i++) {
                    // x = previousX + 1 or previousX - 1 depending on its position
                    int newX = ship.shipCoordinates[i - 1][0] + 1;
                    if (randPosX > this.width - 2) newX = ship.shipCoordinates[i - 1][0] - 1;
                    // y = y
                    int newY = ship.shipCoordinates[i - 1][1];
                    ship.shipCoordinates[i][0] = newX;
                    ship.shipCoordinates[i][1] = newY;
                }
            }
            else {
                for (int i=1; i<=2;i++) {
                    // x = x
                    int newX = ship.shipCoordinates[i - 1][0];
                    // y = previousY + 1 or previousY - 1 depending on its location
                    int newY = ship.shipCoordinates[i - 1][1] + 1;
                    if (randPosY > this.height - 2){
                        newY = ship.shipCoordinates[i - 1][1] - 1;
                    }
                    ship.shipCoordinates[i][0] = newX;
                    ship.shipCoordinates[i][1] = newY;
                }
            }
            if (isEmptySpace(ship.shipCoordinates)) break;
        }
        updateGrid(ship.shipCoordinates);
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
    private boolean isEmptySpace(int [][] coordinates) {
        for (int [] cor: coordinates){
            int x = cor[0];
            int y = cor[1];
            return gameGrid[x][y].equals("*") ? false : true;
        } 
        return true; 
    }
    private void updateGrid(int [][] coordinates){
        for (int [] cor: coordinates){
            int x = cor[0];
            int y = cor[1];
            gameGrid[x][y] = "*";
        }        
    }
    public void printGrid() {
        for (String[] array : gameGrid) {
            String output = "";
            for (String str : array) {
                output += str != null ? " "+str: " .";
            }
            System.out.println(output);
        }      
    }
	
}