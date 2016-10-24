package mx.unam.mascotamenus.RestApiFireBase.model;

/**
 * Created by john on 16/10/2016.
 */
public class UsuarioResponseLike {

    private String id;
    private String id_dispositivo;
    private String id_usuario;
    private String id_foto;

    public UsuarioResponseLike(String id, String id_dispositivo, String id_usuario,String id_foto) {
        this.id = id;
        this.id_dispositivo = id_dispositivo;
        this.id_usuario = id_usuario;
        id_foto=id_foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getId_foto() {
        return id_foto;
    }

    public void setId_foto(String id_foto) {
        this.id_foto = id_foto;
    }
}
