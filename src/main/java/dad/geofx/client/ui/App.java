package dad.geofx.client.ui;

import dad.geofx.client.api.IpService;
import dad.geofx.client.api.MyIpService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private RootController rootController;
    private static IpService ipapi = new IpService();
    private static MyIpService ipify= new MyIpService();

    public static IpService getIpapi() {
        return ipapi;
    }

    public static void setIpapi(IpService ipapi) {
        App.ipapi = ipapi;
    }

    public static MyIpService getIpify() {
        return ipify;
    }

    public static void setIpify(MyIpService ipify) {
        App.ipify = ipify;
    }

    @Override
    public void start(Stage stage) throws Exception {
        rootController = new RootController();
        Scene scene = new Scene(rootController.getView());

        stage.setTitle("GeoFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
