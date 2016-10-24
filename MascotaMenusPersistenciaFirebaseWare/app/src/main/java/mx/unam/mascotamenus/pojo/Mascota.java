package mx.unam.mascotamenus.pojo;

import java.util.ArrayList;

/**
 * Created by john on 20/08/2016.
 */
public class Mascota {

    private  String id;
    private String nombre;
    private String foto;
    private int raiting;
    private int favorito;


    ArrayList<Mascota> listMacotas;

    public Mascota() {
    }

    public Mascota(String id) {
        this.id = id;
    }

    public Mascota(String nombre, String foto, int raiting) {
        this.nombre = nombre;
        this.foto = foto;
        this.raiting = raiting;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public int getFavorito() {
        return favorito;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }
/*
    public ArrayList<Mascota> InicializarLista(){
        listMacotas=new ArrayList<Mascota>();
        listMacotas.add(new Mascota("Boby",R.drawable.perro1,2));
        listMacotas.add(new Mascota("Fido",R.drawable.perro2,1));
        listMacotas.add(new Mascota("Spy",R.drawable.perro3,2));
        listMacotas.add(new Mascota("Chester",R.drawable.perro4,1));
        listMacotas.add(new Mascota("Yago",R.drawable.perro5,2));
        return listMacotas;
    }

    public ArrayList<Mascota> ListaFavoritos(){
        ArrayList<Mascota> listMacotasFavotitas=new ArrayList<Mascota>();
        for (Mascota m:listMacotas ) {
            if (m.getFavorito()==1){
                listMacotasFavotitas.add(m);
            }
        }
        return listMacotasFavotitas;
    }*/
}
