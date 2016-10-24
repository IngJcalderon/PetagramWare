package mx.unam.mascotamenus.RestApiFireBase.adapter;

import mx.unam.mascotamenus.RestApiFireBase.ConsantesRestAPI;
import mx.unam.mascotamenus.RestApiFireBase.Endponits;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by john on 15/10/2016.
 */
public class RestApiAdapter {

    public Endponits establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConsantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        return retrofit.create(Endponits.class);

    }
}
