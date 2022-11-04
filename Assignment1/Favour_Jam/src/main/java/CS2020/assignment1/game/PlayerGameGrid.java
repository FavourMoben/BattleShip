package CS2020.assignment1.game;
public class PlayerGameGrid extends GameGrid {
    PlayerGameGrid(int width,int height,int numberOfShips){
        super(width,height,numberOfShips);
    }

    @Override
    public void printGrid() {
        System.out.println("player's grid: ");
        for (String[] array : gameGrid) {
            String output = "";
            for (String str : array) {
                output += str != null ? " "+str: " .";
            }
            System.out.println(output);
        }      
    }
}