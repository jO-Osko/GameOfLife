package GameOfLife.CellCalculators;

import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.Interfaces.GameGrid;
import GameOfLife.cell.CellState;

public class Reverse implements CellCalculator {

    private GameGrid gameGrid;

    public Reverse(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    @Override
    public CellState getNextState(int x_coor, int y_coor) {
        return this.gameGrid.getCurrentState(x_coor, y_coor) == CellState.ALIVE ? CellState.DEAD: CellState.ALIVE;
    }

    @Override
    public String getName() {
        return "Reverse";
    }

    @Override
    public void UpdateGrid(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }
}
