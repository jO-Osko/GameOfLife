package GameOfLife.game;


import GameOfLife.CellCalculators.ConwaysGame;
import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.Interfaces.GameGrid;
import GameOfLife.Interfaces.UpdatableCellGrid;
import GameOfLife.cell.CellState;
import GameOfLife.SampleSettings;

import java.util.ArrayList;

public class BasicGameGrid implements GameGrid {

    private int numberOfRows;
    private int numberOfColumns;

    private UpdatableCellGrid cellGrid;
    private CellCalculator cellCalculator;

    public BasicGameGrid(SampleSettings settings){
        this(settings.getNumberOfRows(), settings.getNumberOfColumns(), settings.isWrapVertical(), settings.isWrapHorizontal(), settings.getCellCalculator());
    }

    public BasicGameGrid(int numberOfRows, int numberOfColumns) {
        this(numberOfRows, numberOfColumns, false, false, new ConwaysGame(null));
    }

    public BasicGameGrid(int numberOfRows, int numberOfColumns, boolean wrapVertical, boolean wrapHorizontal) {
        this(numberOfRows, numberOfColumns, wrapVertical, wrapHorizontal, new ConwaysGame(null));
    }

    public BasicGameGrid(int numberOfRows, int numberOfColumns, boolean wrapVertical, boolean wrapHorizontal, CellCalculator cellCalculator){
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.cellGrid = new ArrayListCellGrid(numberOfRows, numberOfColumns, wrapVertical, wrapHorizontal);
        this.cellCalculator = cellCalculator;
        this.cellCalculator.UpdateGrid(this);
    }

    @Override
    public int getNumberOfColumns() {
        return this.numberOfColumns;
    }

    @Override
    public int getNumberOfRows() {
        return this.numberOfRows;
    }

    @Override
    public CellState getCurrentState(int x_coor, int y_coor) {
        return this.cellGrid.getCellStateAt(x_coor, y_coor);
    }

    @Override
    public void updateCalculator(CellCalculator cellCalculator) {
        this.cellCalculator = cellCalculator;
        cellCalculator.UpdateGrid(this);
    }

    @Override
    public void nextGeneration() {
        ArrayList<ArrayList<CellState>> nextGeneration = new ArrayList<>(this.numberOfRows);
        for (int j = 0; j < this.numberOfRows; ++j) {
            ArrayList<CellState> row = new ArrayList<>(this.numberOfColumns);
            nextGeneration.add(row);
            for (int i = 0; i < this.numberOfColumns; ++i) {
                row.add(this.cellCalculator.getNextState(j, i));
            }
        }

        this.cellGrid.updateGrid(nextGeneration);
    }

    @Override
    public void setCell(int x, int y){
        this.cellGrid.toggleCell(x, y);
    }

}
