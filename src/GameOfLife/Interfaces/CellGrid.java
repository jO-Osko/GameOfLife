package GameOfLife.Interfaces;


import GameOfLife.cell.CellState;

public interface CellGrid {
    CellState getCellStateAt(int x_coor, int y_coor);
}
