package dad.geofx.client.ui;

import dad.geofx.client.ipapi.GeoIpData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable {

    private static StringProperty ip = new SimpleStringProperty();
    private static StringProperty isp = new SimpleStringProperty();
    private static StringProperty type = new SimpleStringProperty();
    private static StringProperty asn = new SimpleStringProperty();
    private static StringProperty host = new SimpleStringProperty();


    @FXML
    private Label ipLabel;

    @FXML
    private Label ispLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label asnLabel;

    @FXML
    private Label hostLabel;

    @FXML
    private GridPane view;

    public ConnectionController() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ConnectionView.fxml"));
        loader.setController(this);
        loader.load();

    }

    public GridPane getView() {
        return view;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ipLabel.textProperty().bind(ipProperty());
        ispLabel.textProperty().bind(ispProperty());
        typeLabel.textProperty().bind(typeProperty());
        asnLabel.textProperty().bind(asnProperty());
        hostLabel.textProperty().bind(hostProperty());

    }

    public static void checkConnection(GeoIpData geoIpData){

        ip.set(geoIpData.getIp());
        isp.set(geoIpData.getConnection().getIsp());
        type.set(geoIpData.getType());
        asn.set(geoIpData.getConnection().getAsn()+"");
        host.set(geoIpData.getHostname());




    }

    public static String getAsn() {
        return asn.get();
    }

    public static StringProperty asnProperty() {
        return asn;
    }

    public static void setAsn(String asn) {
        ConnectionController.asn.set(asn);
    }

    public static String getHost() {
        return host.get();
    }

    public static StringProperty hostProperty() {
        return host;
    }

    public static void setHost(String host) {
        ConnectionController.host.set(host);
    }

    public static String getIp() {
        return ip.get();
    }

    public static StringProperty ipProperty() {
        return ip;
    }

    public static void setIp(String ip) {
        ConnectionController.ip.set(ip);
    }

    public static String getIsp() {
        return isp.get();
    }

    public static StringProperty ispProperty() {
        return isp;
    }

    public static void setIsp(String isp) {
        ConnectionController.isp.set(isp);
    }

    public static String getType() {
        return type.get();
    }

    public static StringProperty typeProperty() {
        return type;
    }

    public static void setType(String type) {
        ConnectionController.type.set(type);
    }
}
