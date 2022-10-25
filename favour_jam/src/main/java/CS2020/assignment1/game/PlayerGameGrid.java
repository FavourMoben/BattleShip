package CS2020.assignment1.game;
public PlayerGameGrid extends GameGrid {
    PlayerGameGrid() {

    }
    @Override
    private void printGrid(GameGrid gameGrid,String name) {
        System.out.printf("%s's Grid: \n",name);
        for (String[] array : gameGrid) {
            String output = "";
            for (String str : array) {
                output += str != null ? " "+str: " .";
            }
            System.out.println(output);
        }      
    }
}