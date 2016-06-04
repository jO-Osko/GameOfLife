package GameOfLife.Interfaces;


import GameOfLife.cell.CellState;

public interface CellCalculator {

    CellState getNextState(int x_coor, int y_coor);

    String getName();

    default void UpdateGrid(GameGrid gameGrid){}

}
