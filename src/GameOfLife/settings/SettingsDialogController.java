package GameOfLife.settings;

import GameOfLife.SampleSettings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class SettingsDialogController implements Initializable{

    @FXML
    private TextField numberOfRowsTextField;

    @FXML
    private TextField numberOfColumnsTextField;

    @FXML
    private CheckBox wrapHorizontalCheckbox;

    @FXML
    private CheckBox wrapVerticalCheckbox;


    private Stage dialogStage;

    private boolean okClicked = false;

    private SampleSettings settings;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void updateView(SampleSettings settings){
        this.settings = settings;
        this.numberOfColumnsTextField.textProperty().setValue(settings.getNumberOfColumns() + "");
        this.numberOfRowsTextField.textProperty().setValue(settings.getNumberOfRows() + "");
        this.wrapHorizontalCheckbox.selectedProperty().set(settings.isWrapHorizontal());
        this.wrapVerticalCheckbox.selectedProperty().set(settings.isWrapVertical());
    }


    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        okClicked = true;
        this.settings.setNumberOfColumns(Integer.parseInt(numberOfColumnsTextField.textProperty().get()));
        this.settings.setNumberOfRows(Integer.parseInt(numberOfRowsTextField.textProperty().get()));
        this.settings.setWrapHorizontal(wrapHorizontalCheckbox.isSelected());
        this.settings.setWrapVertical(wrapVerticalCheckbox.isSelected());
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
