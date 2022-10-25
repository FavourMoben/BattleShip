package CS2020.assignment1.game;
public class OpponentGameGrid extends GameGrid {
    OpponentGameGrid(int width,int height,int numberOfShips){
        super(width,height,numberOfShips);
    }
    @Override
    public void printGrid() {
        System.out.println("opponent's grid: ");
        for (String[] array : gameGrid) {
            String output = "";
            for (String str : array) {
                str = !str.equals("*") ? str : ".";
                output += str != null ? " "+str: " .";
            }
            System.out.println(output);
        }      
    }
}