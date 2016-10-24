package mx.unam.mascotamenus.vista.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import mx.unam.mascotamenus.RestApiFireBase.Endponits;
import mx.unam.mascotamenus.RestApiFireBase.adapter.RestApiAdapter;
import mx.unam.mascotamenus.RestApiFireBase.model.UsuarioResponseLike;
import mx.unam.mascotamenus.UserGral;
import mx.unam.mascotamenus.adapter.MascotaAdaptador;
import mx.unam.mascotamenus.R;
import mx.unam.mascotamenus.RecyclerItemClickListener;
import mx.unam.mascotamenus.pojo.Mascota;
import mx.unam.mascotamenus.presentador.IRecyclerViewFragmentPresenter;
import mx.unam.mascotamenus.presentador.RecyclerViewFragmentPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by john on 29/08/2016.
 */
public class RecyclerViewFragment extends Fragment implements  IRecyclerViewFragmentView{

    Mascota mascota;
   // ArrayList<Mascota> listMacotas;
    private RecyclerView rvMascotas;
    private TextView tvfavorito;
    private IRecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_recyclerview,container,false);


        rvMascotas=(RecyclerView) v.findViewById(R.id.rvMascotas);


/*
        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // do it
            }
        });
        */

/*
        rvMascotas.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public void onTouchEvent(RecyclerView recycler, MotionEvent event) {
                // Handle on touch events here
            }

            @Override
            public boolean onInterceptTouchEvent(RecyclerView recycler, MotionEvent event) {
                return false;
            }

        });

        */

        rvMascotas.addOnItemTouchListener(
                 new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                       // Intent intent = new Intent(view.getContext(), fragment_favoritos.class);
                        //intent=new Intent(this,Activity_Contacto.class);
                       // startActivity(intent);
                        enviarToken(view);
                        Snackbar.make(view, "Mascota : "+position, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                })
        );


        presenter=new RecyclerViewFragmentPresenter(this,getContext());
        return v;
    }


    @Override
    public void generarLinearLayoutVertical() {
        // GridLayoutManager glm=new GridLayoutManager(getActivity(),2);
        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> Mascotas) {
        MascotaAdaptador adaptador=new MascotaAdaptador(Mascotas,getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotas.setAdapter(adaptador);
    }


    public void enviarToken(View v){
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarLike(token);
        //toque(v);
    }

    //like
    private void enviarLike(String token){
        Log.d("TOKEN", token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endponits endponits = restApiAdapter.establecerConexionRestAPI();
        if (UserGral.usuario_id.compareToIgnoreCase("")==0){
            UserGral.usuario_id="3885420775";
        }
        Call<UsuarioResponseLike> usuarioResponseCallLike = endponits.registrarTokenLike(token, UserGral.usuario_id,"125");

        usuarioResponseCallLike.enqueue(new Callback<UsuarioResponseLike>() {
            @Override
            public void onResponse(Call<UsuarioResponseLike> call, Response<UsuarioResponseLike> response) {
                UsuarioResponseLike usuarioResponseLike = response.body();
                // Log.d("ID_FIREBASE", usuarioResponse.toString());
                Log.d("ID_FIREBASE", usuarioResponseLike.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponseLike.getId_dispositivo());
                Log.d("TOKEN_FIREBASE", usuarioResponseLike.getId_usuario());
                Log.d("TOKEN_FIREBASE", usuarioResponseLike.getId_foto());
            }

            @Override
            public void onFailure(Call<UsuarioResponseLike> call, Throwable t) {

            }
        });
    }

    public void toque(View v){
        Log.d("TOQUE_ANIMAL", "true");
        final UsuarioResponseLike usuarioResponse = new UsuarioResponseLike("-KUG0R5zKgNbdeDN39DX", "123", "assa","hh");
        RestApiAdapter restApiAdapter =  new RestApiAdapter();
        Endponits endponits = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponseLike> usuarioResponseCall = endponits.toque(usuarioResponse.getId(), "ANIMAL_EMISOR");
        usuarioResponseCall.enqueue(new Callback<UsuarioResponseLike>() {
            @Override
            public void onResponse(Call<UsuarioResponseLike> call, Response<UsuarioResponseLike> response) {
                UsuarioResponseLike usuarioResponse1 =response.body();
//                Log.d("ID_FIREBASE", usuarioResponse1.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse1.getId_dispositivo());
               // Log.d("ANIMAL_FIREBASE", usuarioResponse1.getId_usuario());
               // Log.d("ANIMAL_FIREBASE", usuarioResponse1.getId_foto());
            }

            @Override
            public void onFailure(Call<UsuarioResponseLike> call, Throwable t) {

            }
        });
    }
/*
    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            RecyclerViewFragment.this.refresh();
        }
    }

    public void refresh() {
        //yout code in refresh.
        Log.i("Refresh", "YES");
    }*/
}

