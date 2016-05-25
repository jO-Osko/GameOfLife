package GameOfLife.Interfaces;

import GameOfLife.cell.CellState;

public interface GameGrid {

    // private int numberOfRows = 0;
    // private int numberOfCollumns;


    //default int getNumberOfCollumns() {
    //    return this.numberOfCollumns;
    //}

    int getNumberOfColumns();
    int getNumberOfRows();

    CellState getCurrentState(int x_coor, int y_coor);

    void nextGeneration();

}
