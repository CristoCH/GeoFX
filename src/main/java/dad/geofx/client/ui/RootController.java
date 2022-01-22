package dad.geofx.client.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dad.geofx.client.ipapi.GeoIpData;
import dad.geofx.client.ipify.MyIp;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

public class RootController implements Initializable {

    private LocationController locationController = new LocationController();
    private SecurityController securityController = new SecurityController();
    private ConnectionController connectionController = new ConnectionController();

    @FXML
    private Button checkButton;

    @FXML
    private Tab connectionTab;

    @FXML
    private TextField ipText;

    @FXML
    private Tab locationTab;

    @FXML
    private Tab securityTab;

    @FXML
    private BorderPane view;

    public BorderPane getView() {
        return view;
    }

    public RootController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
        loader.setController(this);
        loader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Task<MyIp> getIp = new Task<MyIp>() {
            @Override
            protected MyIp call() throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("https://api.ipify.org/?format=json").get().build();
                Response response = client.newCall(request).execute();
                String json = response.body().string();
                Gson gson  = new GsonBuilder().create();

                MyIp ip = gson.fromJson(json, MyIp.class);
                return ip;
            }
        };

        getIp.setOnSucceeded(e->{
            try {
                ipText.setText(getIp.get().getIp());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        });

        getIp.setOnFailed(e->{
            getIp.getException().printStackTrace();
        });
        new Thread(getIp).start();

        locationTab.setContent(locationController.getView());
        securityTab.setContent(securityController.getView());
        connectionTab.setContent(connectionController.getView());

    }

    @FXML
    void onCheckIpAction(ActionEvent event) {

        Task<GeoIpData> geoIpDataTask = new Task<GeoIpData>() {
            @Override
            protected GeoIpData call() throws Exception {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("https://ipapi.com/ip_api.php?ip="+ipText.getText()).get().build();
                Response response = client.newCall(request).execute();

                String json = response.body().string();
                Gson gson = new GsonBuilder().create();
                GeoIpData geoIpData = gson.fromJson(json,GeoIpData.class);

                return geoIpData;
            }
        };

        geoIpDataTask.setOnSucceeded(e ->{
            try {
                LocationController.checkLocation(geoIpDataTask.get());
                ConnectionController.checkConnection(geoIpDataTask.get());
                SecurityController.checkSecurity(geoIpDataTask.get());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        });

        geoIpDataTask.setOnFailed(e ->{
            geoIpDataTask.getException().printStackTrace();
        });
        new Thread(geoIpDataTask).start();
    }


}
