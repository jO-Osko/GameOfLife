package GameOfLife.game;

import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.Interfaces.GameGrid;
import GameOfLife.canvas.CanvasManager;
import javafx.animation.AnimationTimer;

import java.util.ArrayList;

public class BasicGame {
    private GameGrid gameGrid;
    private CanvasManager canvasManager;
    private boolean isPlaying;
    private AnimationTimer animation;
    private ArrayList<Integer> speedsArray = new ArrayList<>();
    private int animationSpeed = 0;
    private int speedIndex = 0;


    public BasicGame(GameGrid gameGrid, CanvasManager canvasManager) {
        this.gameGrid = gameGrid;
        this.canvasManager = canvasManager;
        this.createSpeedsArray();
        this.animationSpeed = this.speedsArray.get(speedIndex);
        this.animation = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if(now - lastUpdate >= animationSpeed){
                    nextGeneration();
                    lastUpdate = now;
                }
            }
        };
    }
    private void createSpeedsArray(){
        speedsArray.add(150_000_000);
        speedsArray.add(120_000_000);
        speedsArray.add(90_000_000);
        speedsArray.add(60_000_000);
        speedsArray.add(30_000_000);
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

    public void stop(){
        if(isPlaying){
            this.animation.stop();
            isPlaying = false;
        }
    }

    public boolean play(){
        if(isPlaying){
            animation.stop();
            isPlaying = false;
            return false;
        }
        else {
            animation.start();
            isPlaying = true;
            return true;
        }
    }


    public void changeAnimationSpeed(){
        this.speedIndex++;
        this.animationSpeed = speedsArray.get(this.speedIndex % speedsArray.size());
    }

}
