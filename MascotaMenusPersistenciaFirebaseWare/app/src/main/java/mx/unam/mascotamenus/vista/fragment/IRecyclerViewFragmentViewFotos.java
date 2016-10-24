package mx.unam.mascotamenus.vista.fragment;

import java.util.ArrayList;

import mx.unam.mascotamenus.adapter.MascotaAdaptadorFotos;
import mx.unam.mascotamenus.pojo.Mascota;

/**
 * Created by john on 04/09/2016.
 */
public interface IRecyclerViewFragmentViewFotos {

    public void generarGridLayout();

    public MascotaAdaptadorFotos crearAdaptador(ArrayList<Mascota> Mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptadorFotos adaptador);
}
