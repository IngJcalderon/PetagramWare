package mx.unam.mascotamenus.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import mx.unam.mascotamenus.UserGral;
import mx.unam.mascotamenus.db.ConstructorMascotas;
import mx.unam.mascotamenus.pojo.Mascota;
import mx.unam.mascotamenus.restApi.ConstantesRestApi;
import mx.unam.mascotamenus.restApi.EndpointsApi;
import mx.unam.mascotamenus.restApi.adapter.RestApiAdapter;
import mx.unam.mascotamenus.restApi.model.MascotaResponse;
import mx.unam.mascotamenus.vista.fragment.IRecyclerViewFragmentViewFotos;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by john on 04/09/2016.
 */
public class RecyclerViewFragmentPresenterDetalle implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentViewFotos iRecyclerViewFragmentViewFotos;
    private  Context context;
    private ConstructorMascotas constructorMacotas;
    private ArrayList<Mascota> mascotas;
    String user_id=null,nombre=null,url_foto=null;

    public RecyclerViewFragmentPresenterDetalle(IRecyclerViewFragmentViewFotos iRecyclerViewFragmentViewFotos, Context context) {
        this.iRecyclerViewFragmentViewFotos=iRecyclerViewFragmentViewFotos;
        this.context=context;
        //obtenerFotos();
        //mascotaperfil=new ArrayList<>();
        user_id=UserGral.usuario;

        mascotas = new ArrayList<>();
        obtenerMediosPerfil(user_id);


    }

    @Override
    public void obtenerMascotas() {
        /*constructorMacotas=new ConstructorMascotas(context);
        mascotas=constructorMacotas.obtenerDatos();
        mostrarMascotasRV();*/
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
                MascotaResponse contactoResponse = response.body();
                mascotas = contactoResponse.getMascotas();
                mostrarMascotasRV();
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION2", t.toString());
            }
        });
    }

    @Override
    public void obtenerMediosPerfil(String user) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaPerfil();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);

        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getRecentMedia_perfil(user, ConstantesRestApi.ACCESS_TOKEN);
        // Log.i("HolaMundo","url detalle: "+ mascotaResponseCall.request());
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                if (user_id != null) {
                    MascotaResponse contactoResponse = response.body();
                    mascotas = contactoResponse.getMascotas();

                    user_id = mascotas.get(0).getId();
                    nombre  = mascotas.get(0).getNombre();
                    url_foto = mascotas.get(0).getFoto();
                    Log.i("FALLO LA CONEXION", url_foto);
                    mascotas = new ArrayList<>();
                    obtenerMediosRecientesUsuario(user_id);
                }
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void obtenerFotos() {
        constructorMacotas=new ConstructorMascotas(context);
        mascotas=constructorMacotas.obtenerFotos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentViewFotos.inicializarAdaptadorRV(iRecyclerViewFragmentViewFotos.crearAdaptador(mascotas));
        iRecyclerViewFragmentViewFotos.generarGridLayout();
    }

}
