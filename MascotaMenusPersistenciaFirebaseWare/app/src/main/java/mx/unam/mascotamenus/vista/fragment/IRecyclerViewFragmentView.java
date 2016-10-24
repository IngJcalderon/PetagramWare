package mx.unam.mascotamenus.vista.fragment;

import java.util.ArrayList;

import mx.unam.mascotamenus.adapter.MascotaAdaptador;
import mx.unam.mascotamenus.pojo.Mascota;

/**
 * Created by john on 04/09/2016.
 */
public interface IRecyclerViewFragmentView {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> Mascotas);

    public void inicializarAdaptadorRV( MascotaAdaptador adaptador);
}
