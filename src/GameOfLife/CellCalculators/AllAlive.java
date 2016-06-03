package GameOfLife.CellCalculators;


import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.cell.CellState;

public class AllAlive implements CellCalculator{

    @Override
    public CellState getNextState(int x_coor, int y_coor) {
        return CellState.ALIVE;
    }

    @Override
    public String getName() {
        return "Vsi preživijo";
    }
}
