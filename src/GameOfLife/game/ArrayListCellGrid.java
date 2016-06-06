package GameOfLife.game;


import GameOfLife.Interfaces.CellGrid;
import GameOfLife.Interfaces.UpdatableCellGrid;
import GameOfLife.cell.CellState;

import java.util.ArrayList;

public class ArrayListCellGrid implements UpdatableCellGrid{

    private ArrayList<ArrayList<CellState>> grid;
    private int numberOfRows = 0;
    private int numberOfColumns = 0;

    private boolean warpVertical;
    private boolean wrapHorizontal;

    public ArrayListCellGrid(){
        this(0,0);
    }

    public ArrayListCellGrid(int numberOfRows, int numberOfColumns) {
        this(numberOfRows, numberOfColumns, false, false, CellState.DEAD);
    }

    public ArrayListCellGrid(int numberOfRows, int numberOfColumns, boolean wrapVertical, boolean wrapHorizontal) {
        this(numberOfRows, numberOfColumns, wrapVertical, wrapHorizontal, CellState.DEAD);
    }

    public ArrayListCellGrid(int numberOfRows, int numberOfColumns, boolean wrapVertical, boolean wrapHorizontal, CellState initialState){
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;

        this.grid = new ArrayList<>(numberOfRows);
        for(int j = 0; j < numberOfRows; ++j){
            ArrayList<CellState> row = new ArrayList<>(numberOfColumns);
            for(int i = 0; i < numberOfColumns; ++i){
                row.add(initialState);
            }
            this.grid.add(row);
        }

        this.warpVertical = wrapVertical;
        this.wrapHorizontal = wrapHorizontal;

    }

    @Override
    public void toggleCell(int x, int y) {
        this.grid.get(x).set(y, this.grid.get(x).get(y)==CellState.ALIVE? CellState.DEAD: CellState.ALIVE);
    }

    @Override
    public void updateGrid(ArrayList<ArrayList<CellState>> grid){
        this.grid = grid;
    }

    @Override
    public CellState getCellStateAt(int x_coor, int y_coor) {
        if(this.warpVertical){
            x_coor = (x_coor + this.numberOfRows) % this.numberOfRows;
        }
        if(wrapHorizontal){
            y_coor = (y_coor + this.numberOfColumns) % this.numberOfColumns;
        }
        if(x_coor < 0 || x_coor >= numberOfRows || y_coor < 0 || y_coor >= numberOfColumns){
            // Automatically dead if outside of grid
            return CellState.DEAD;
        }
        return this.grid.get(x_coor).get(y_coor);
    }
}
