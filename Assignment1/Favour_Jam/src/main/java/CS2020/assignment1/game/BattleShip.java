package CS2020.assignment1.game;
import java.util.Random;

public class BattleShip extends AbstractBattleShip {
    private final int randomInt = new Random().nextInt(2);
    BattleShip(String name) {
        this.name = name;
        this.shipOrientation = randomInt == 1 ? "horizontal" : "vertical";
        this.hits = 0;        
    }
    @Override 
    public boolean checkAttack (int row,int column){
        //  checks for attack at a particular position on the game grid
        for (int [] coors:this.shipCoordinates) {
            Integer x = new Integer(coors[0]);
            Integer y = new Integer(coors[1]);
            if (x.equals(row) && y.equals(column) && this.hits < 3) {
                this.hits++;
                return true;
            }
        }
        return false;
    }
    // getters and setters
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
    // setters
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