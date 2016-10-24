package mx.unam.mascotamenus.db;

/**
 * Created by john on 04/09/2016.
 */
public class ConstantesBaseDatos {

    public static final String DATABASE_NAME="mascota";

    public static final int DATABASE_VERSION=1;

    public static final String TABLE_MASCOTA           = "mascota";
    public static final String TABLE_MASCOTA_ID        = "id";
    public static final String TABLE_MASCOTA_NOMBRE    = "nombre";
    public static final String TABLE_MASCOTA_FOTO      = "foto";

    public static final String TABLE_LIKES_MASCOTA = "mascota_likes";
    public static final String TABLE_LIKES_MASCOTA_ID = "id";
    public static final String TABLE_LIKES_MASCOTA_ID_MASCOTA= "id_mascota";
    public static final String TABLE_LIKES_MASCOTA_NUMERO_LIKES = "numero_likes";

    public static final String TABLE_MASCOTA_FAVORITA = "mascota_favorita";
    public static final String TABLE_MASCOTA_FAVORITA_ID = "id";
    public static final String TABLE_MASCOTA_FAVORITA_ID_MASCOTA= "id_mascota";

}
