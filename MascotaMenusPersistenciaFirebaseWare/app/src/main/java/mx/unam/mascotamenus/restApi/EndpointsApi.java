package mx.unam.mascotamenus.restApi;

import mx.unam.mascotamenus.restApi.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by anahisalgado on 25/05/16.
 */
public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_SB)
    Call<MascotaResponse> getRecentMedia_user(@Path("user") String user);

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_PERFIL)
    Call<MascotaResponse> getRecentMedia_perfil(@Query("q") String jack, @Query("access_token") String access_token);
}
