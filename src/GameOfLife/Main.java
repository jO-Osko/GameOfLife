package GameOfLife;

import GameOfLife.canvas.CanvasManager;
import GameOfLife.canvas.ResizableCanvas;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private int windowHeight = 600;
    private int windowWidth = 800;

    private int minWindowWidth = windowWidth/2;
    private int minWindowHeight = windowHeight/2;

    private String title = "Conway's game of life";

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main_view.fxml"));
        primaryStage.setTitle(this.title);
        //primaryStage.setResizable(false); // javafx bug on windows 8

        this.windowHeight = Settings.windowHeight;
        this.windowWidth = Settings.windowWidth;


        Scene scene = new Scene(root, this.windowWidth, this.windowHeight);
        ResizableCanvas canvas = (ResizableCanvas) scene.lookup("#mainCanvas");

        Pane canvasPane = (Pane) scene.lookup("#canvasPane");

        primaryStage.setScene(scene);
        canvas.widthProperty().bind(
                canvasPane.widthProperty());
        canvas.heightProperty().bind(
                canvasPane.heightProperty());

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
