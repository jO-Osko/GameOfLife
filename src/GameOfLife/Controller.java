package GameOfLife;

import GameOfLife.CellCalculators.*;
import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.canvas.CanvasManager;
import GameOfLife.game.BasicGame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private CanvasManager canvasManager;
    private BasicGame mainGame;


    @FXML
    private Canvas mainCanvas;

    @FXML
    private Pane canvasPane;

    @FXML
    private Menu rulesMenu;

    @FXML
    private MenuItem nextGenerationItem;

    @FXML
    private void handleCanvasClick(MouseEvent event){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.mainCanvas.widthProperty().bind(
                this.canvasPane.widthProperty());
        this.mainCanvas.heightProperty().bind(
                this.canvasPane.heightProperty());

        this.mainGame = new BasicGame(10,20);

        this.canvasManager = new CanvasManager(this.mainCanvas, this.mainGame);

        ArrayList<CellCalculator> calculators = new ArrayList<>(4);

        calculators.add(new AllAlive());
        calculators.add(new AllDead());
        calculators.add(new ConwaysGame(this.mainGame));
        calculators.add(new Rule110(this.mainGame));
        calculators.add(new Reverse(mainGame));

        for(CellCalculator calculator: calculators){
            MenuItem menuItem = new MenuItem(calculator.getName());

            menuItem.setOnAction(event -> {
                this.mainGame = new BasicGame(20,10);
                calculator.UpdateGrid(this.mainGame);
                this.canvasManager.changeGameGrid(this.mainGame);
            });

            this.rulesMenu.getItems().add(menuItem);
        }

        nextGenerationItem.setOnAction(event -> {
            this.mainGame.nextGeneration();
            this.canvasManager.redraw();
        });

    }

}
