package CS2020.assignment1.game;
import java.util.Random;

public class BattleShip extends AbstractBattleShip {
    private int randomInt = new Random().nextInt(2);
    BattleShip(String name) {
        this.name = name;
        this.shipOrientation = randomInt == 1 ? "horizontal" : "vertical";
        this.hits = 0;
        
    }
    @Override 
    public boolean checkAttack (int row,int column){
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