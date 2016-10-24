package mx.unam.mascotamenus.restApi.deserializador;

import mx.unam.mascotamenus.pojo.Mascota;
import mx.unam.mascotamenus.restApi.JsonKeys;
import mx.unam.mascotamenus.restApi.model.MascotaResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by john on 18/09/2016.
 */
public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson=new Gson();
        MascotaResponse contactoResponse=gson.fromJson(json,MascotaResponse.class);
        JsonArray contactoResponseData=json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        contactoResponse.setMascotas(deserializarContactoDeJson(contactoResponseData));
        return contactoResponse;
    }

    private ArrayList<Mascota> deserializarContactoDeJson(JsonArray contactoResponseData){

        ArrayList<Mascota> contactos=new ArrayList<Mascota>();
        for (int i = 0; i < contactoResponseData.size(); i++) {
            JsonObject contactoResponseDataObject=contactoResponseData.get(i).getAsJsonObject();
            JsonObject userJson=contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id               = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto   = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson=contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson=imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto= stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson=contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes= likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Mascota contactoActual =new Mascota();
            contactoActual.setId(id);
            contactoActual.setNombre(nombreCompleto);
            contactoActual.setFoto(urlFoto);
            contactoActual.setRaiting(likes);

            contactos.add(contactoActual);
        }
        return  contactos;
    }
}
