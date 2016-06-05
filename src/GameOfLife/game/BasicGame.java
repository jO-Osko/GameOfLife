package GameOfLife.game;

import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.Interfaces.GameGrid;
import GameOfLife.canvas.CanvasManager;

public class BasicGame {
    private GameGrid gameGrid;
    private CanvasManager canvasManager;


    public BasicGame(GameGrid gameGrid, CanvasManager canvasManager) {
        this.gameGrid = gameGrid;
        this.canvasManager = canvasManager;
    }

    public void updateGameGrid(GameGrid gameGrid){
        this.gameGrid = gameGrid;
        this.canvasManager.changeGameGrid(gameGrid);
    }

    public void nextGeneration(){
        this.gameGrid.nextGeneration();
        this.canvasManager.redraw();
    }

    public void repaint(){
        this.canvasManager.recalculateCellDimensions();

    }

    public void updateCalculator(CellCalculator cellCalculator){
        this.gameGrid.updateCalculator(cellCalculator);
    }

}
