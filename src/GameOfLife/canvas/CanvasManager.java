package GameOfLife.canvas;


import GameOfLife.Interfaces.GameGrid;
import GameOfLife.cell.CellState;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sun.rmi.runtime.Log;

public class CanvasManager {

    private final Canvas canvas;
    private GameGrid gameGrid;

    private int lineHeight;
    private int lineWidth;
    private double cellWidth;
    private double cellHeight;

    public CanvasManager(Canvas canvas, GameGrid gameGrid, int lineHeight, int lineWidth, double cellWidth, double cellHeight) {
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

    public CanvasManager(Canvas canvas){
        this(canvas, null);
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
        context.setFill(cellState == CellState.ALIVE ? Color.BLACK: Color.TRANSPARENT);
        // Zamenjamo x in y
        context.fillRect(y_ind*this.cellWidth, x_ind*this.cellHeight, this.cellWidth, this.cellHeight);


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

        gc.setStroke(color);
        final double canvasHeight = this.canvas.getHeight();

        final double canvasWidth = this.canvas.getWidth();
        //neumno ampak dela :)
        gc.clearRect(0,0,1920, 1080);

        // vertical
        gc.setLineWidth(this.lineWidth);
        for(int x = 1; x < this.gameGrid.getNumberOfColumns(); ++x){
            final double x_coor = x*this.cellWidth;
            gc.strokeLine(x_coor, 0, x_coor, canvasHeight);
        }

        // horizontal
        gc.setLineWidth(this.lineHeight);
        for(int y = 1; y < this.gameGrid.getNumberOfRows(); ++y){
            final double y_coor = y*this.cellHeight;
            gc.strokeLine(0, y_coor, canvasWidth, y_coor);
        }

    }

    private void recalculateCellHeight(){
        this.cellHeight = this.canvas.heightProperty().doubleValue()/this.gameGrid.getNumberOfRows();
    }

    private void recalculateCellWidth(){
        this.cellWidth = this.canvas.widthProperty().doubleValue()/this.gameGrid.getNumberOfColumns();
    }

    public void recalculateCellDimensions(){
        this.recalculateCellHeight();
        this.recalculateCellWidth();
        this.redraw();
    }

    private void setUpListeners(){
        this.canvas.heightProperty().addListener((observable, oldValue, newValue) -> {
            // Poracunaj nove visine celic itd
            this.recalculateCellHeight();
            this.redraw();
        });

        this.canvas.widthProperty().addListener((observable, oldValue, newValue) -> {
            // Poracunaj nove sirine celic itd
            this.recalculateCellWidth();
            this.redraw();
        });

        this.canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                double x_cor = event.getX()/ cellWidth;
                double y_cor =  event.getY()/ cellHeight;
                gameGrid.setCell((int) y_cor,(int) x_cor);
                redraw();
            }
        });

    }

}
