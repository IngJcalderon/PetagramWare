package mx.unam.mascotamenus.presentador;

/**
 * Created by john on 04/09/2016.
 */
public interface IRecyclerViewFragmentPresenter {

    public void obtenerMascotas();

    public void obtenerMediosRecientes();

    public void obtenerMediosRecientesUsuario(String user);

    public void obtenerMediosPerfil(String nomUser);

    public void obtenerFotos();

    public void mostrarMascotasRV();
}
