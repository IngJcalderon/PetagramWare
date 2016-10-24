package mx.unam.mascotamenus.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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
public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private  Context context;
    private ConstructorMascotas constructorMacotas;
    private ArrayList<Mascota> mascotas;
    private ArrayList<Mascota> mascotasAux;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
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
        mascotas=constructorMacotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia();

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse contactoResponse = response.body();
                mascotas = contactoResponse.getMascotas();
                mostrarMascotasRV();
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
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

                try{
                    mascotasAux = mascotaResponse.getMascotas();
                    Toast.makeText(context, "Aux"+mascotasAux.size(), Toast.LENGTH_LONG).show();
                    for (int j = 0; j < mascotasAux.size(); j++) {
                        mascotas.add(mascotasAux.get(j));
                    }
                    mostrarMascotasRV();
                }
                catch (Exception e){
                    mascotasAux = new ArrayList<>();
                    Log.e("Error en el OnResponse",e.toString());
                }

            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, context.getResources().getString(R.string.msg_error_conexion), Toast.LENGTH_LONG).show();
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
