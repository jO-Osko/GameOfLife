package GameOfLife.game;

import GameOfLife.Interfaces.GameGrid;
import GameOfLife.canvas.CanvasManager;

public class BasicGame {
    private GameGrid gameGrid;
    private CanvasManager canvasManager;


    public BasicGame(GameGrid gameGrid, CanvasManager canvasManager) {
        this.gameGrid = gameGrid;
        this.canvasManager = canvasManager;
    }
}
