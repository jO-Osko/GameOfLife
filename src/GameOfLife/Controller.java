package GameOfLife;

import GameOfLife.CellCalculators.*;
import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.canvas.CanvasManager;
import GameOfLife.game.BasicGame;
import GameOfLife.game.BasicGameGrid;
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

        CanvasManager canvasManager = new CanvasManager(this.mainCanvas);

        this.mainGame = new BasicGame(null, canvasManager);
        this.mainGame.updateGameGrid(BasicGameGrid.demoGameGrid());

        ArrayList<CellCalculator> calculators = new ArrayList<>(4);

        calculators.add(new AllAlive());
        calculators.add(new AllDead());
        calculators.add(new ConwaysGame(null));
        calculators.add(new Rule110(null));
        calculators.add(new Reverse(null));

        for(CellCalculator calculator: calculators){
            MenuItem menuItem = new MenuItem(calculator.getName());

            menuItem.setOnAction(event -> {
                // On purpose, we only update rules and not whole grid, this enables some user experimentation
                this.mainGame.updateCalculator(calculator);
            });

            this.rulesMenu.getItems().add(menuItem);
        }

        nextGenerationItem.setOnAction(event -> this.mainGame.nextGeneration());

    }

}
