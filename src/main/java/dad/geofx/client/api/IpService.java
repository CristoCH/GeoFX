package dad.geofx.client.api;

import dad.geofx.client.ipapi.GeoIpData;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class IpService {

    private static final String BASE_URL = "https://ipapi.com/";
    private IpInterface ipInterface;

    public IpService() {

        ConnectionPool connectionPool = new ConnectionPool(1, 5, TimeUnit.SECONDS);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectionPool(connectionPool).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();

        ipInterface = retrofit.create(IpInterface.class);
    }

    public GeoIpData getGeoIpData(String ip) throws IOException{
        Response<GeoIpData> response = null;
        response = ipInterface.getGeoIpData(ip).execute();
        return response.body();
    }
}
