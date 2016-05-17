package GameOfLife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        Scene scene = new Scene(root, this.windowWidth, this.windowHeight);

        primaryStage.setMinHeight(this.minWindowHeight);
        primaryStage.setMinWidth(this.minWindowWidth);

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
