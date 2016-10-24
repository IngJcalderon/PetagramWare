package mx.unam.mascotamenus.restApi.adapter;

import mx.unam.mascotamenus.restApi.ConstantesRestApi;
import mx.unam.mascotamenus.restApi.EndpointsApi;
import mx.unam.mascotamenus.restApi.deserializador.MascotaDeserializador;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mx.unam.mascotamenus.restApi.deserializador.MascotaDeserializadorDetalle;
import mx.unam.mascotamenus.restApi.model.MascotaResponse;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorMediaPerfil(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializadorDetalle());
        return gsonBuilder.create();
    }
}
