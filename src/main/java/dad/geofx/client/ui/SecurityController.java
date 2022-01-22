package dad.geofx.client.ui;

import dad.geofx.client.ipapi.GeoIpData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    private static SimpleStringProperty ipSecurity = new SimpleStringProperty();
    private static BooleanProperty proxy = new SimpleBooleanProperty();
    private static BooleanProperty tor = new SimpleBooleanProperty();
    private static BooleanProperty crawler = new SimpleBooleanProperty();
    private static StringProperty threat = new SimpleStringProperty();
    private static StringProperty potential = new SimpleStringProperty();


    @FXML
    private Label securityLabel;

    @FXML
    private CheckBox proxyCheck;

    @FXML
    private CheckBox torCheck;

    @FXML
    private CheckBox crawlerCheck;

    @FXML
    private Label threatLabel;

    @FXML
    private Label potentialLabel;

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

        securityLabel.textProperty().bind(ipSecurityProperty());
        proxyCheck.selectedProperty().bind(proxyProperty());
        torCheck.selectedProperty().bind(torProperty());
        crawlerCheck.selectedProperty().bind(crawlerProperty());
        threatLabel.textProperty().bind(threatProperty());
        potentialLabel.textProperty().bind(potentialProperty());

    }

    public static void checkSecurity(GeoIpData geoIpData){

        ipSecurity.set(geoIpData.getSecurity()+"");
        proxy.set(geoIpData.getSecurity().getIsProxy());
        tor.set(geoIpData.getSecurity().getIsTor());
        crawler.set(geoIpData.getSecurity().getIsCrawler());
        threat.set(geoIpData.getSecurity().getThreatLevel());
        potential.set(geoIpData.getSecurity().getThreatTypes()+"");

    }

    public static String getIpSecurity() {
        return ipSecurity.get();
    }

    public static SimpleStringProperty ipSecurityProperty() {
        return ipSecurity;
    }

    public static void setIpSecurity(String ipSecurity) {
        SecurityController.ipSecurity.set(ipSecurity);
    }

    public static boolean isProxy() {
        return proxy.get();
    }

    public static BooleanProperty proxyProperty() {
        return proxy;
    }

    public static void setProxy(boolean proxy) {
        SecurityController.proxy.set(proxy);
    }

    public static boolean isTor() {
        return tor.get();
    }

    public static BooleanProperty torProperty() {
        return tor;
    }

    public static void setTor(boolean tor) {
        SecurityController.tor.set(tor);
    }

    public static boolean isCrawler() {
        return crawler.get();
    }

    public static BooleanProperty crawlerProperty() {
        return crawler;
    }

    public static void setCrawler(boolean crawler) {
        SecurityController.crawler.set(crawler);
    }

    public static String getThreat() {
        return threat.get();
    }

    public static StringProperty threatProperty() {
        return threat;
    }

    public static void setThreat(String threat) {
        SecurityController.threat.set(threat);
    }

    public static String getPotential() {
        return potential.get();
    }

    public static StringProperty potentialProperty() {
        return potential;
    }

    public static void setPotential(String potential) {
        SecurityController.potential.set(potential);
    }
}
