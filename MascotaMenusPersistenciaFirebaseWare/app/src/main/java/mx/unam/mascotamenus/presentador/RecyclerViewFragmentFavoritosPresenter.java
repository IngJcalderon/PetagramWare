package mx.unam.mascotamenus.presentador;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

import mx.unam.mascotamenus.R;
import mx.unam.mascotamenus.db.ConstructorMascotas;
import mx.unam.mascotamenus.pojo.Mascota;
import mx.unam.mascotamenus.restApi.EndpointsApi;
import mx.unam.mascotamenus.restApi.adapter.RestApiAdapter;
import mx.unam.mascotamenus.restApi.model.MascotaResponse;
import mx.unam.mascotamenus.vista.fragment.IRecyclerViewFragmentView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by john on 04/09/2016.
 */
public class RecyclerViewFragmentFavoritosPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private  Context context;
    private ConstructorMascotas constructorMacotas;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Mascota> mascotasAux;

    public RecyclerViewFragmentFavoritosPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView=iRecyclerViewFragmentView;
        this.context=context;
        //obtenerMascotas();

        mascotas=new ArrayList<>();
        //obtenerMediosRecientes();
        mascotasAux=new ArrayList<>();
        String [] opciones = {
                context.getResources().getString(R.string.user1_id),
                context.getResources().getString(R.string.user2_id)
                //context.getResources().getString(R.string.user3_id),
                // context.getResources().getString(R.string.user4_id),
                // context.getResources().getString(R.string.user5_id)
        };
        for (int i = 0; i < opciones.length; i++) {
            obtenerMediosRecientesUsuario(opciones[i]);
        }
    }

    @Override
    public void obtenerMascotas() {
        constructorMacotas=new ConstructorMascotas(context);
        mascotas=constructorMacotas.obtenerMascotaFavoritas();
        mostrarMascotasRV();
    }

    @Override
    public void obtenerMediosRecientes() {

    }

    @Override
    public void obtenerMediosRecientesUsuario(String user) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia_user(user);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotasAux = mascotaResponse.getMascotas();
                for (int j = 0; j < mascotasAux.size(); j++) {
                    mascotas.add(mascotasAux.get(j));
                    // Ordenando las mascotas segun la cantidad de likes
                    for (int i = 0; i < mascotas.size()-1; i++) {
                        for (int k = i+1; k < mascotas.size(); k++) {
                            if (mascotas.get(k).getRaiting()>mascotas.get(i).getRaiting())
                                mascotas.set(k,mascotas.set(i,mascotas.get(k)));
                        }
                    }

                    //Seleccionando las 5 primeras ("las 5 favoritas")
                    ArrayList<Mascota> aux=new ArrayList<>();
                    if (mascotas.size()>5){
                        for (int i = 0; i < 5; i++) {
                            aux.add(mascotas.get(i));

                        }
                        mascotas=aux;
                    }

                    mostrarMascotasRV();
                }
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
              //  Toast.makeText(context, context.getResources().getString(R.string.msg_error_conexion), Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION",t.toString());
            }
        });
    }

    @Override
    public void obtenerMediosPerfil(String nomUser) {

    }

    @Override
    public void obtenerFotos() {

    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
    }

}
