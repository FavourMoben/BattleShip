package CS2020.assignment1.game;
//mvn exec:java -Dexec.mainClass="CS2020.assignment1.game.GameGrid"
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
        generateShips(this.numberOfShips);
        for (AbstractBattleShip ship: ships) {
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
        while(true){
            int randPosX = new Random().nextInt(this.width-2);
            int randPosY = new Random().nextInt(this.height-2);
            int rows = 3; // 3 rows 
            int columns = 2; // 2 columns
            ship.shipCoordinates = new int [rows][columns];
            ship.shipCoordinates[0][0] = randPosX;
            ship.shipCoordinates[0][1] = randPosY;
            if (ship.getShipOrientation().equals("vertical")) {
                for (int i=1; i<=2;i++) {
                    // x = previousX + 1 or previousX - 1 depending on its position
                    int prevX = ship.shipCoordinates[i - 1][0];
                    int prevY = ship.shipCoordinates[i - 1][1];

                    int newX = randPosX > this.width - 3 ? prevX - 1  : prevX + 1;
                    int newY = prevY;

                    ship.shipCoordinates[i][0] = newX;
                    ship.shipCoordinates[i][1] = newY;
                }
            }
            else {
                for (int i=1; i<=2;i++) {
                    int prevX = ship.shipCoordinates[i - 1][0];
                    int prevY = ship.shipCoordinates[i - 1][1];
                    // x = x
                    // y = previousY + 1 or previousY - 1 depending on its location
                    int newX = prevX;
                    int newY = randPosY > this.height - 3 ? prevY - 1: prevY + 1;
                    
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
        boolean isEmpty = true;
        for (int [] cor: coordinates){
            int x = cor[0];
            int y = cor[1];
            isEmpty = gameGrid[x][y].equals("*") ? false : true;
        } 
        return isEmpty; 
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