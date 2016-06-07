package GameOfLife;

import GameOfLife.settings.SettingsDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // BAD BAD BAD BAD practice
    public static Main self;

    private int windowHeight = 600;
    private int windowWidth = 800;

    private Stage primaryStage;

    private String title = "Conway's game of life";


    public Main(){
        Main.self = this;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("main_view.fxml"));
        primaryStage.setTitle(this.title);
        //primaryStage.setResizable(false); // javafx bug on windows 8

        Scene scene = new Scene(root, this.windowWidth, this.windowHeight);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public boolean showPersonEditDialog(SampleSettings person) {
        try {
            FXMLLoader loader = new FXMLLoader(SettingsDialogController.class.
                    getResource("settings_dialog_view.fxml"));
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nastavitve");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(loader.load());
            dialogStage.setScene(scene);

            SettingsDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.updateView(person);

            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
