package dad.geofx.client.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecurityController implements Initializable {


    @FXML
    private CheckBox crawlerCheck;

    @FXML
    private Label potentialLabel;

    @FXML
    private CheckBox proxyCheck;

    @FXML
    private Label securityLabel;

    @FXML
    private Label threatLabel;

    @FXML
    private CheckBox torCheck;

    @FXML
    private GridPane view;

    public SecurityController() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/SecurityView.fxml"));
        loader.setController(this);
        loader.load();

    }

    public GridPane getView() {
        return view;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
