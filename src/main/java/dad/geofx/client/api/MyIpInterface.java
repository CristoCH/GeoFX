package dad.geofx.client.api;

import dad.geofx.client.ipify.MyIp;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyIpInterface {
    @GET("/")
    public Call<MyIp> getMyIp(@Query("format") String format);
}
