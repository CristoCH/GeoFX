package dad.geofx.client.api;

import dad.geofx.client.ipify.MyIp;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

public class MyIpService {

    private static final String BASE_URL = "https://api.ipfiy.org/";
    private MyIpInterface myIpInterface;
    public MyIpService() {
        ConnectionPool connectionPool = new ConnectionPool(1, 5, TimeUnit.SECONDS);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectionPool(connectionPool).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();

        myIpInterface = retrofit.create(MyIpInterface.class);
    }
    public String ip() throws Exception{
        Response<MyIp> response = myIpInterface.getMyIp("json").execute();
        MyIp myIp = response.body();
        return myIp.getIp();
    }
}
