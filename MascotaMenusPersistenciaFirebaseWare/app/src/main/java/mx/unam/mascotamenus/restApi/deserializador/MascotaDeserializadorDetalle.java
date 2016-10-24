package mx.unam.mascotamenus.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import mx.unam.mascotamenus.pojo.Mascota;
import mx.unam.mascotamenus.restApi.JsonKeys;
import mx.unam.mascotamenus.restApi.model.MascotaResponse;

/**
 * Created by john on 18/09/2016.
 */
public class MascotaDeserializadorDetalle implements JsonDeserializer<MascotaResponse> {

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

            String id               = contactoResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto   = contactoResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();
            String urlFoto          = contactoResponseDataObject.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();
           // int likes= contactoResponseDataObject.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Mascota contactoActual =new Mascota();
            contactoActual.setId(id);
            contactoActual.setNombre(nombreCompleto);
            contactoActual.setFoto(urlFoto);
            //contactoActual.setRaiting(likes);

            contactos.add(contactoActual);
        }
        return  contactos;
    }
}
