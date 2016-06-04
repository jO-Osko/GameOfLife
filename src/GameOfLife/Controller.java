package GameOfLife;

import GameOfLife.CellCalculators.AllAlive;
import GameOfLife.CellCalculators.AllDead;
import GameOfLife.CellCalculators.ConwaysGame;
import GameOfLife.CellCalculators.Rule110;
import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.canvas.CanvasManager;
import GameOfLife.game.BasicGame;
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

    @FXML
    private Canvas mainCanvas;

    @FXML
    private Pane canvasPane;

    @FXML
    private Menu rulesMenu;

    @FXML
    private void handleCanvasClick(MouseEvent event){

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.mainCanvas.widthProperty().bind(
                this.canvasPane.widthProperty());
        this.mainCanvas.heightProperty().bind(
                this.canvasPane.heightProperty());

        ArrayList<CellCalculator> calculators = new ArrayList<>(4);

        calculators.add(new AllAlive());
        calculators.add(new AllDead());
        calculators.add(new ConwaysGame(null));
        calculators.add(new Rule110(null));

        for(CellCalculator calculator: calculators){
            MenuItem menuItem = new MenuItem(calculator.getName());
            menuItem.setOnAction(event -> System.out.println(calculator.getName()));
            this.rulesMenu.getItems().add(menuItem);
        }

        CanvasManager canvasManager = new CanvasManager(this.mainCanvas, new BasicGame(10,20));


    }

}
