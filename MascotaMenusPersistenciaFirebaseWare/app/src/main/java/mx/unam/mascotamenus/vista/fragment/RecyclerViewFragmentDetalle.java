package mx.unam.mascotamenus.vista.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import mx.unam.mascotamenus.UserGral;
import mx.unam.mascotamenus.adapter.MascotaAdaptadorFotos;
import mx.unam.mascotamenus.R;
import mx.unam.mascotamenus.pojo.Mascota;
import mx.unam.mascotamenus.presentador.IRecyclerViewFragmentPresenter;
import mx.unam.mascotamenus.presentador.RecyclerViewFragmentPresenterDetalle;


public class RecyclerViewFragmentDetalle extends Fragment implements  IRecyclerViewFragmentViewFotos{

    ArrayList<Mascota> listMacotasFavoritas;
    ArrayList<Mascota> listMacotasFotosFavorita;
    private RecyclerView rvMascotaFavorita;
    private RecyclerView rvFotosMascotaFavorita;
    private TextView tvNombreMascota;
    private IRecyclerViewFragmentPresenter presenter;
    View v;
    public RecyclerViewFragmentDetalle() {
        // Required empty public constructor
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       //super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_recyclervire_detalle, container, false);

        // rvMascotaFavorita=(RecyclerView) v.findViewById(R.id.rvMascotaFavorita);

        rvFotosMascotaFavorita=(RecyclerView) v.findViewById(R.id.rvFotosMascotaFavorita);

        tvNombreMascota= (TextView) v.findViewById(R.id.tvNombreMascota);
        //Imagen redondeada
        /*
        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.perro22);//UserGral.url_foto

        CircularImageView circularImageView = (CircularImageView) v.findViewById(R.id.civMascotaFavorita);
        // Set Border
        //circularImageView.setBorderColor(getResources().getColor(R.color.colorAccent));
        //circularImageView.setBorderWidth(10);
        circularImageView.setImageBitmap(icon);

        // Add Shadow with default param
        circularImageView.addShadow();
        // or with custom param
        circularImageView.setShadowRadius(15);
        circularImageView.setShadowColor(Color.GREEN);
*/

        //Fin de imagen redondeada
        //Mascota principal
        //Fotos de Mascota principal


        presenter=new RecyclerViewFragmentPresenterDetalle(this,getContext());
//        */
        return v;
    }
/*
    @Override
    public void onResume() {
        super.onResume();

//to refresh your view
     //   refresh();

    }
*/

    @Override
    public void generarGridLayout() {
        GridLayoutManager glm=new GridLayoutManager(getActivity(),3);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        rvFotosMascotaFavorita.setLayoutManager(glm);

        //tvNombreMascota.setText(UserGral.nombre);
    }

    @Override
    public MascotaAdaptadorFotos crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptadorFotos adaptador=new MascotaAdaptadorFotos(mascotas,getActivity());
        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.perro22);//UserGral.url_foto

        CircularImageView circularImageView = (CircularImageView) v.findViewById(R.id.civMascotaFavorita);
        // Set Border
        //circularImageView.setBorderColor(getResources().getColor(R.color.colorAccent));
        //circularImageView.setBorderWidth(10);
        circularImageView.setImageBitmap(icon);

        // Add Shadow with default param
        circularImageView.addShadow();
        // or with custom param
        circularImageView.setShadowRadius(15);
        circularImageView.setShadowColor(Color.GREEN);
        tvNombreMascota.setText(UserGral.nombre);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptadorFotos adaptador) {
        rvFotosMascotaFavorita.setAdapter(adaptador);
    }

    public void perfil(){

    }
}
