package GameOfLife.canvas;


import GameOfLife.Interfaces.GameGrid;
import GameOfLife.cell.CellState;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasManager {

    private final Canvas canvas;
    private GameGrid gameGrid;

    private int lineHeight;
    private int lineWidth;
    private int cellWidth;
    private int cellHeight;

    public CanvasManager(Canvas canvas, GameGrid gameGrid, int lineHeight, int lineWidth, int cellWidth, int cellHeight) {
        this.canvas = canvas;
        this.gameGrid = gameGrid;
        this.lineHeight = lineHeight;
        this.lineWidth = lineWidth;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;

        setUpListeners();

    }

    public CanvasManager(Canvas canvas, GameGrid gameGrid){
        this(canvas, gameGrid, 1, 1, 10, 10);
    }

    public void redraw(boolean drawGrid){
        if(drawGrid){
            this.drawGrid(this.canvas.getGraphicsContext2D());
        }
        this.drawCells();
    }

    public void redraw(){
        this.redraw(true);
    }

    public void changeGameGrid(GameGrid gameGrid){
        this.gameGrid = gameGrid;
        this.redraw();
    }

    private void drawCell(int x_ind, int y_ind, CellState cellState, GraphicsContext context){
        //Narisi kvadrat v x_ind vrstici in v y_ind stolpcu

        //context.strokeRect(igranje s piksli);
        context.setFill(cellState == CellState.ALIVE ? Color.BLACK: Color.RED);
        context.fillRect(y_ind*10, x_ind*10, y_ind*10 + 5, x_ind*10 + 5);


    }

    private void drawCells(){
        final GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        for(int x_ind = 0; x_ind < this.gameGrid.getNumberOfRows(); ++x_ind){
            for(int y_ind = 0; y_ind < this.gameGrid.getNumberOfColumns(); ++y_ind){
                this.drawCell(x_ind, y_ind, this.gameGrid.getCurrentState(x_ind, y_ind), graphicsContext);
            }
        }
    }

    private void drawGrid(GraphicsContext gc){
        drawGrid(gc, Color.BLACK);
    }

    private void drawGrid(GraphicsContext gc, Color color){
        // TODO Uredi da bo lepo izrisalo na canvas
        gc.setStroke(color);
        final double canvasHeight = this.canvas.getHeight();
        final double canvasWidth = this.canvas.getWidth();
        gc.clearRect(0,0,canvasHeight, canvasWidth);
        // vertical
        gc.setLineWidth(this.lineWidth);
        for(int x = 1; x < this.gameGrid.getNumberOfColumns(); ++x){
            final double x_coor = x*this.lineWidth + x*this.cellWidth - 0.5;
            gc.strokeLine(x_coor, 0, x_coor, canvasHeight);
        }

        // horizontal
        gc.setLineWidth(this.lineHeight);
        for(int y = 1; y < this.gameGrid.getNumberOfRows(); ++y){
            final double y_coor = y*this.lineHeight + y*this.cellHeight - 0.5;
            gc.strokeLine(0, y_coor, canvasWidth, y_coor);
        }
    }

    private void setUpListeners(){
        this.canvas.heightProperty().addListener((observable, oldValue, newValue) -> {
            // Poracunaj nove visine celic itd (
            // newValue.doubleValue();
            this.redraw();
        });

        this.canvas.widthProperty().addListener((observable, oldValue, newValue) -> {
            // Poracunaj nove sirine celic itd (
            // newValue.doubleValue();
            this.redraw();
        });

    }

}
