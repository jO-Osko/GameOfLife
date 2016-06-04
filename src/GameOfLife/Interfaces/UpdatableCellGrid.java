package GameOfLife.Interfaces;

import GameOfLife.cell.CellState;

import java.util.ArrayList;

public interface UpdatableCellGrid extends CellGrid{
    void updateGrid(ArrayList<ArrayList<CellState>> grid);
    void addLiveCell(int x, int y);
}
