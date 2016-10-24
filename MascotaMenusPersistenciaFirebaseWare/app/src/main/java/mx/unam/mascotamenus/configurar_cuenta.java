package mx.unam.mascotamenus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import mx.unam.mascotamenus.RestApiFireBase.Endponits;
import mx.unam.mascotamenus.RestApiFireBase.adapter.RestApiAdapter;
import mx.unam.mascotamenus.RestApiFireBase.model.UsuarioResponse;
import mx.unam.mascotamenus.RestApiFireBase.model.UsuarioResponseLike;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class configurar_cuenta extends AppCompatActivity implements View.OnClickListener{
    Button btnGuardarCuenta;
    EditText inputNombreUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cuenta);
        inputNombreUsuario = (EditText) findViewById(R.id.inputNombreUsuario);
        btnGuardarCuenta = (Button) findViewById(R.id.btnGuardarCuenta);

        btnGuardarCuenta.setOnClickListener(this);
    }

    public void asignarUsuario(View v){

    }

    public void enviarToken(View v){
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
        //enviarLike(token);
    }

    private void enviarTokenRegistro(String token){
        Log.d("TOKEN", token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endponits endponits = restApiAdapter.establecerConexionRestAPI();
        Call<UsuarioResponse> usuarioResponseCall = endponits.registrarTokenID(token, UserGral.usuario_id);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
               // Log.d("ID_FIREBASE", usuarioResponse.toString());
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse.getId_dispositivo());
                Log.d("TOKEN_FIREBASE", usuarioResponse.getId_usuario());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }
    //like
    /*
    private void enviarLike(String token){
        Log.d("TOKEN", token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endponits endponits = restApiAdapter.establecerConexionRestAPI();
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
*/
    @Override
    public void onClick(View view) {
        String user = inputNombreUsuario.getText().toString();

        String [] opciones = {
                getResources().getString(R.string.user1),
                getResources().getString(R.string.user2)

        };

        String [] opciones_id = {
                getResources().getString(R.string.user1_id),
                getResources().getString(R.string.user2_id)

        };

        boolean esValido = false;

        for (int i = 0; i < opciones.length; i++) {
            if (user.equals(opciones[i])){
                esValido=true;
                //obtenenos su id _usuario
                UserGral.usuario_id=opciones_id[i];
                //enviarToken(view);
            }
        }

        //UserGral.usuario="3964909392";
        if (esValido){

            UserGral.usuario=user;
            enviarToken(view);
            Toast.makeText(configurar_cuenta.this, user +" Guardado :)", Toast.LENGTH_LONG).show();

        }else {

              Toast.makeText(configurar_cuenta.this, "Incorrecto", Toast.LENGTH_LONG).show();
        }
    }
}
