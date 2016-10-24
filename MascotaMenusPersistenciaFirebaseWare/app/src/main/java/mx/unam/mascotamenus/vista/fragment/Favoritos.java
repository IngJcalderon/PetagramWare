package mx.unam.mascotamenus.vista.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

import mx.unam.mascotamenus.R;
import mx.unam.mascotamenus.adapter.MascotaAdaptador;
import mx.unam.mascotamenus.pojo.Mascota;
import mx.unam.mascotamenus.presentador.IRecyclerViewFragmentPresenter;
import mx.unam.mascotamenus.presentador.RecyclerViewFragmentFavoritosPresenter;

/**
 * Created by john on 21/08/2016.
 */
public class Favoritos extends AppCompatActivity/* extends Fragment*/ implements IRecyclerViewFragmentView {
   // Mascota mascota;
    private ImageView ivRegresar;
    //ArrayList<Mascota> listMacotasFavoritas;
    private RecyclerView rvMascotasFavoritas;
    private IRecyclerViewFragmentPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritos);
  /*  @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.favoritos,container,false);
*/
        rvMascotasFavoritas=(RecyclerView) findViewById(R.id.rvMascotasFavoritas);


        //listMacotasFavoritas=mascota.ListaFavoritos();

       // ivRegresar = (ImageView) findViewById(R.id.ivRegresar);
/*
        ivRegresar.setOnClickListener(new AdapterView.OnClickListener() {
                                          @Override
                                          public void onClick(View view) {
                                              Intent intent=new Intent(Favoritos.this,MainActivity.class);
                                              startActivity(intent);
                                          }
                                      }
        );*/

        presenter=new RecyclerViewFragmentFavoritosPresenter(this,getBaseContext());
     //   return v;

    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotasFavoritas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> Mascotas) {
        MascotaAdaptador adaptador=new MascotaAdaptador(Mascotas,this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotasFavoritas.setAdapter(adaptador);
    }

}
