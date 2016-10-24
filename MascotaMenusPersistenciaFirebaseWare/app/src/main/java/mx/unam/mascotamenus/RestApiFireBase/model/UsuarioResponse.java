package mx.unam.mascotamenus.RestApiFireBase.model;

/**
 * Created by john on 15/10/2016.
 */
public class UsuarioResponse {

    private String id;
    private String id_dispositivo;
    private String id_usuario;


    public UsuarioResponse(String id, String id_dispositivo, String id_usuario) {
        this.id = id;
        this.id_dispositivo = id_dispositivo;
        this.id_usuario = id_usuario;
    }

    public UsuarioResponse() {
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
}
