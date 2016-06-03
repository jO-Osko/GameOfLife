package GameOfLife.CellCalculators;


import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.Interfaces.GameGrid;
import GameOfLife.cell.CellState;

public class Rule110 implements CellCalculator{

    private final GameGrid gameGrid;

    public Rule110(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    @Override
    public CellState getNextState(int x_coor, int y_coor) {
        // Test if this line is already used
        for (int dx = 0; dx <= 1; ++dx){ // This row and one lower
            for(int dy = -1; dy <= 1; ++dy){
                if(this.gameGrid.getCurrentState(x_coor + dx, y_coor + dy) == CellState.ALIVE){
                    return this.gameGrid.getCurrentState(x_coor, y_coor); // We will not calculate already evolved cells
                }
            }
        }
        if(this.gameGrid.getCurrentState(x_coor-1, y_coor) == CellState.DEAD){
            if(this.gameGrid.getCurrentState(x_coor-1, y_coor + 1) == CellState.DEAD){
                return CellState.DEAD; // 100 and 000 are both DEAD
            }
            return CellState.ALIVE;
        }
        if(this.gameGrid.getCurrentState(x_coor-1, y_coor) == this.gameGrid.getCurrentState(x_coor-1, y_coor-1) &&
                this.gameGrid.getCurrentState(x_coor-1, y_coor + 1) == CellState.ALIVE){
            return CellState.DEAD; // 111 the last alive
        }
        return CellState.ALIVE;
    }

    @Override
    public String getName() {
        return "Rule 110";
    }
}
