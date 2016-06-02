package GameOfLife.CellCalculators.base;

import GameOfLife.Interfaces.GameGrid;
import GameOfLife.cell.CellState;


/**
 * Could be an interface, if default methods were able to access instance fields
 */
public class NeighbourCalculator {

    private GameGrid gameGrid;

    public NeighbourCalculator(GameGrid gameGrid) {
        this.gameGrid = gameGrid;
    }

    protected int getAliveNeighbours(int x_coor, int y_coor){
        int alive = 0;

        for(int dx = -1; dx <=1; ++dx ){
            for(int dy = -1; dy <= 1; ++dy){
                if(dx==dy && dx==0){ // Ignore current cell
                    continue;
                }
                if(this.gameGrid.getCurrentState(x_coor + dx, y_coor + dy) == CellState.ALIVE){
                    ++alive;
                }
            }
        }
        return alive;
    }

}
