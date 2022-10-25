package CS2020.assignment1.game;
import java.util.Random;
public class BattleShip extends AbstractBattleShip {

    private int boardWidth = 5, boardHeight = 7; 

    BattleShip(String name) {
        int randomInt = new Random().nextInt(2);
        this.name = name;
        this.shipOrientation = randomInt == 1 ? "horizontal" : "vertical";
        this.hits = 0;
        
    }
    private int randint(int minn,int maxx) {
        return (int) (Math.random() * maxx) + minn;
    }
    @Override 
    public boolean checkAttack (int row,int column){
        for (int i=0; i<this.shipCoordinates.length;i++) {
            for (int j=1; j<this.shipCoordinates[0].length;j++){
                if (this.shipCoordinates[i][j-1] == row && this.shipCoordinates[i][j] == column) {
                    if (this.hits < 3) {
                        this.hits++;
                        return true;
                    }
                }
            }
        }
        return false;
    }
	@Override 
	public String getName(){
        return this.name;
    }
    @Override 
	public int getHits() {
        return this.hits;
    }
	@Override 
	public String getShipOrientation() {
        return this.shipOrientation;
    }
	@Override 
	public void setHits(int numberOfHits){
        this.hits = numberOfHits;
    }
	@Override 
	public int[][] getShipCoordinates(){
        return shipCoordinates;
    }
	@Override 
	public void setShipCoordinates(int [][] coordinates){
        this.shipCoordinates = coordinates;
    }

}