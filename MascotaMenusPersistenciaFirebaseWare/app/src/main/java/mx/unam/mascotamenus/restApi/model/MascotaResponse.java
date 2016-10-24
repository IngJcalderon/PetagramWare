package mx.unam.mascotamenus.restApi.model;

import java.util.ArrayList;

import mx.unam.mascotamenus.pojo.Mascota;

public class MascotaResponse {
    ArrayList<Mascota> mascotas;
    //String user_id;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

}

