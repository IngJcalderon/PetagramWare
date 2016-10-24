package mx.unam.mascotamenus;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by john on 09/10/2016.
 */
public class NotificationIDTokenService  extends FirebaseInstanceIdService {

    private static final String TAG = "FIREBASE_TOKEN";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);

        enviarToekenRegistro(token);
    }

    private void enviarToekenRegistro(String token) {
        // TODO: Implement this method to send token to your app server.
        Log.d(TAG, "Mi token: " + token);
    }
}
