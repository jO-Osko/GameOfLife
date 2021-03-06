package GameOfLife.CellCalculators;


import GameOfLife.CellCalculators.base.NeighbourCalculator;
import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.Interfaces.GameGrid;
import GameOfLife.cell.CellState;

public class ConwaysGame extends NeighbourCalculator implements CellCalculator {

    private GameGrid gameGrid;

    public ConwaysGame(GameGrid gameGrid) {
        super(gameGrid);
        this.gameGrid = gameGrid;
    }


    @Override
    public CellState getNextState(int x_coor, int y_coor) {
        final int alive = this.getAliveNeighbours(x_coor, y_coor);
        final CellState currentState = this.gameGrid.getCurrentState(x_coor, y_coor);
        if(alive == 3){
            return CellState.ALIVE;
        }
        if(alive == 2){
            return currentState;
        }
        return CellState.DEAD;
    }

    @Override
    public String getName() {
        return "Conway's game of life";
    }

    @Override
    public void UpdateGrid(GameGrid gameGrid) {
        super.UpdateGrid(gameGrid);
        this.gameGrid = gameGrid;
    }

}
