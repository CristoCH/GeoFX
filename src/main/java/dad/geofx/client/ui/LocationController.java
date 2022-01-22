package dad.geofx.client.ui;

import dad.geofx.client.ipapi.GeoIpData;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LocationController implements Initializable {

    private static StringProperty latitude = new SimpleStringProperty();
    private static StringProperty longitude = new SimpleStringProperty();
    private static StringProperty location = new SimpleStringProperty();
    private static ObjectProperty<Image> flag = new SimpleObjectProperty<>();
    private static StringProperty city = new SimpleStringProperty();
    private static StringProperty language = new SimpleStringProperty();
    private static StringProperty callingCode = new SimpleStringProperty();
    private static StringProperty zipCode = new SimpleStringProperty();
    private static StringProperty timeZone = new SimpleStringProperty();
    private static StringProperty currency = new SimpleStringProperty();

    private Task<GeoIpData> geoIpDataTask;

    @FXML
    private Label cityLabel;

    @FXML
    private Label codeLabel;

    @FXML
    private Label currencyLabel;

    @FXML
    private ImageView iconView;

    @FXML
    private Label ipLocationLabel;

    @FXML
    private Label latitudeLabel;

    @FXML
    private Label languageLabel;

    @FXML
    private Label longitudeLabel;

    @FXML
    private GridPane view;

    @FXML
    private Label zipLabel;

    @FXML
    private Label zoneLabel;

    public GridPane getView() {
        return view;
    }

    public LocationController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LocationView.fxml"));
        loader.setController(this);
        loader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        latitudeLabel.textProperty().bind(latitude);
        longitudeLabel.textProperty().bind(longitude);
        ipLocationLabel.textProperty().bind(location);
        cityLabel.textProperty().bind(city);
        zipLabel.textProperty().bind(zipCode);
        languageLabel.textProperty().bind(language);
        zoneLabel.textProperty().bind(timeZone);
        codeLabel.textProperty().bind(callingCode);
        currencyLabel.textProperty().bind(currency);
        iconView.imageProperty().bind(flag);

    }

    public static void checkLocation(GeoIpData geoIpData){
        latitude.set(geoIpData.getLatitude()+"");
        longitude.set(geoIpData.getLongitude()+"");
        location.set(geoIpData.getCountryName()+" ("+geoIpData.getCountryCode()+")");
        city.set(geoIpData.getCity());
        zipCode.set(geoIpData.getZip());
        language.set(geoIpData.getLocation().getLanguages().get(0).getName()+" ("+
                geoIpData.getLocation().getLanguages().get(0).getCode().toUpperCase()+")");
        timeZone.set(geoIpData.getTimeZone().getCode());
        callingCode.set("+"+geoIpData.getLocation().getCallingCode());
        currency.set(geoIpData.getCurrency().getName()+" ("+
                geoIpData.getCurrency().getSymbol()+")");
        flag.set(new Image("/icon/64x42/"+geoIpData.getCountryCode()+".png"));

    }


    public String getLatitude() {
        return latitude.get();
    }

    public StringProperty latitudeProperty() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude.set(latitude);
    }

    public String getLongitude() {
        return longitude.get();
    }

    public StringProperty longitudeProperty() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude.set(longitude);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public Image getFlag() {
        return flag.get();
    }

    public ObjectProperty<Image> flagProperty() {
        return flag;
    }

    public void setFlag(Image flag) {
        this.flag.set(flag);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getLanguage() {
        return language.get();
    }

    public StringProperty languageProperty() {
        return language;
    }

    public void setLanguage(String language) {
        this.language.set(language);
    }

    public String getCallingCode() {
        return callingCode.get();
    }

    public StringProperty callingCodeProperty() {
        return callingCode;
    }

    public void setCallingCode(String callingCode) {
        this.callingCode.set(callingCode);
    }

    public String getZipCode() {
        return zipCode.get();
    }

    public StringProperty zipCodeProperty() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode.set(zipCode);
    }

    public String getTimeZone() {
        return timeZone.get();
    }

    public StringProperty timeZoneProperty() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone.set(timeZone);
    }

    public String getCurrency() {
        return currency.get();
    }

    public StringProperty currencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }


}
