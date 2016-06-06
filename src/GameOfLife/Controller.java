package GameOfLife;

import GameOfLife.CellCalculators.*;
import GameOfLife.Interfaces.CellCalculator;
import GameOfLife.canvas.CanvasManager;
import GameOfLife.game.BasicGame;
import GameOfLife.game.BasicGameGrid;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    private static final ImageView playImageView;
    private static final ImageView pauseImegeView;
    private static final ImageView fasterImageView;
    private static final ImageView nextGenerationImageView;

    static{
        Image image = new Image(Controller.class.getResource("data/img/play-icon.png").toExternalForm(), 20,20, true, true);

        playImageView = new ImageView(image);
        playImageView.setFitHeight(20);
        playImageView.setFitWidth(20);

        Image image0 = new Image(Controller.class.getResource("data/img/pause-icon.png").toExternalForm(), 20,20, true, true);

        pauseImegeView = new ImageView(image0);
        pauseImegeView.setFitHeight(20);
        pauseImegeView.setFitWidth(20);

        Image image1 = new Image(Controller.class.getResource("data/img/faster-icon.png").toExternalForm(), 20,20, true, true);

        fasterImageView = new ImageView(image1);
        fasterImageView.setFitHeight(20);
        fasterImageView.setFitWidth(20);

        Image image2 = new Image(Controller.class.getResource("data/img/next-icon.png").toExternalForm(), 20,20, true, true);

        nextGenerationImageView = new ImageView(image2);
        nextGenerationImageView.setFitHeight(20);
        nextGenerationImageView.setFitWidth(20);

    }


    private BasicGame mainGame;

    private SampleSettings settings;

    @FXML
    private Canvas mainCanvas;

    @FXML
    private Pane canvasPane;

    @FXML
    private Menu rulesMenu;

    @FXML
    private MenuItem nextGenerationItem;

    @FXML
    private Button playButton;

    @FXML
    private Button nextGenerationButton;

    @FXML
    private Button fasterButton;

    @FXML
    private void handleSettingsAction(ActionEvent event) throws Exception{
        // throw vs return value passing vs inline pair....
        SampleSettings tempSettings = this.settings.copy();
        if (Main.self.showPersonEditDialog(tempSettings)){
            this.settings = tempSettings;
            this.updateGameGrid();
        }
    }


    @FXML
    private void handleNextGeneration(ActionEvent event){
        this.mainGame.nextGeneration();
    }

    @FXML
    private void handlePlay(ActionEvent event){
        // TODO
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.mainCanvas.widthProperty().bind(
                this.canvasPane.widthProperty());
        this.mainCanvas.heightProperty().bind(
                this.canvasPane.heightProperty());

        this.settings = new SampleSettings(20,30, true, false);

        ArrayList<CellCalculator> calculators = new ArrayList<>(5);

        calculators.add(new AllAlive());
        calculators.add(new AllDead());
        calculators.add(new ConwaysGame(null));
        calculators.add(new Rule110(null));
        calculators.add(new Reverse(null));

        ToggleGroup rulesToggleGroup = new ToggleGroup();

        for(CellCalculator calculator: calculators){
            RadioMenuItem menuItem = new RadioMenuItem(calculator.getName());
            menuItem.setToggleGroup(rulesToggleGroup);

            menuItem.setOnAction(event -> {
                // On purpose, we only update rules and not whole grid, this enables some user experimentation
                this.mainGame.updateCalculator(calculator);
            });

            this.rulesMenu.getItems().add(menuItem);
        }

        nextGenerationItem.setOnAction(event -> this.mainGame.nextGeneration());

        makeGameGrid();

        playButton.setGraphic(playImageView);
        fasterButton.setGraphic(fasterImageView);
        nextGenerationButton.setGraphic(nextGenerationImageView);

    }

    private void makeGameGrid(){
        CanvasManager canvasManager = new CanvasManager(this.mainCanvas);
        this.mainGame = new BasicGame(null, canvasManager);
        this.updateGameGrid();
    }

    private void updateGameGrid(){
        this.mainGame.updateGameGrid(new BasicGameGrid(this.settings));
        this.mainGame.repaint();
    }

}
