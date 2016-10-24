package mx.unam.mascotamenus.RestApiFireBase;

/**
 * Created by john on 15/10/2016.
 */

import mx.unam.mascotamenus.RestApiFireBase.model.UsuarioResponse;
import mx.unam.mascotamenus.RestApiFireBase.model.UsuarioResponseLike;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface  Endponits {

    @FormUrlEncoded
    @POST(ConsantesRestAPI.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarTokenID(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario") String id_usuario);

    @FormUrlEncoded
    @POST(ConsantesRestAPI.KEY_LIKE)
    Call<UsuarioResponseLike> registrarTokenLike(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario") String id_usuario, @Field("id_foto") String id_foto);

    @GET(ConsantesRestAPI.KEY_TOQUE)
    Call<UsuarioResponseLike> toque(@Path("id") String id, @Path("id_dispositivo") String id_dispositivo/*, @Path("id_foto") String id_foto*/);

}
