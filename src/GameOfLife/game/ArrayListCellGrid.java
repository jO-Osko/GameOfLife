package GameOfLife.game;


import GameOfLife.Interfaces.CellGrid;
import GameOfLife.Interfaces.UpdatableCellGrid;
import GameOfLife.cell.CellState;

import java.util.ArrayList;

public class ArrayListCellGrid implements UpdatableCellGrid{

    private ArrayList<ArrayList<CellState>> grid;
    private int numberOfRows = 0;
    private int numberOfColumns = 0;


    public ArrayListCellGrid(){
        this(0,0);
    }

    public ArrayListCellGrid(int numberOfRows, int numberOfColumns) {
        this(numberOfRows, numberOfColumns, CellState.DEAD);
    }

    public ArrayListCellGrid(int numberOfRows, int numberOfColumns, CellState initialState){
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

        //this.grid.get(5).set(5, CellState.ALIVE);
        //this.grid.get(5).set(6, CellState.ALIVE);
        //this.grid.get(5).set(7, CellState.ALIVE);

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
        if(x_coor < 0 || x_coor >= numberOfRows || y_coor < 0 || y_coor >= numberOfColumns){
            // Automatically dead if outside of grid
            return CellState.DEAD;
        }
        return this.grid.get(x_coor).get(y_coor);
    }
}
