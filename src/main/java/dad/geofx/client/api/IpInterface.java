package dad.geofx.client.api;

import dad.geofx.client.ipapi.GeoIpData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IpInterface {
    @GET("ip_api.php/")
    public Call<GeoIpData> getGeoIpData(@Query("ip")String ip);
}
