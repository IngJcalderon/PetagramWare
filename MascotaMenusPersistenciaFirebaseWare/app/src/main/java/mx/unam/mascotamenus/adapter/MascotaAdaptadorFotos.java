package mx.unam.mascotamenus.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.unam.mascotamenus.DetalleMascota;
import mx.unam.mascotamenus.R;
import mx.unam.mascotamenus.pojo.Mascota;

/**
 * Created by john on 20/08/2016.
 */
public class MascotaAdaptadorFotos extends RecyclerView.Adapter<MascotaAdaptadorFotos.MascotaViewHolder> {
    private TextView tvfavorito;
   // ListMascotasFavoritas listMascotasFavoritas= new ListMascotasFavoritas();
    ArrayList<Mascota> listMacotas;
    Activity activity;

   // ArrayList<Mascota> listMacotasFavoritas;

   public MascotaAdaptadorFotos(ArrayList<Mascota> listMacotas, Activity activity){
        this.listMacotas=listMacotas;
        this.activity = activity;
   }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_fotos,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota=listMacotas.get(position);
        Picasso.with(activity)
                .load(mascota.getFoto())
                .placeholder(R.drawable.dog_482)
                .into(mascotaViewHolder.ivMascota);
        //contactoViewHolder.tvNombre.setText(contacto.getNombre());
        //contactoViewHolder.tvTelefono.setText(contacto.getTelefono());
        mascotaViewHolder.tvRate.setText(String.valueOf(mascota.getRaiting()));

        mascotaViewHolder.ivMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity, contacto.getNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, DetalleMascota.class);
                intent.putExtra("url", mascota.getFoto());
                intent.putExtra("like", mascota.getRaiting());
                //intent.putExtra("email", contacto.getEmail());
                activity.startActivity(intent);
            }
        });
        //mascotaViewHolder.ivMascota.setImageResource(mascota.getFoto());
       // mascotaViewHolder.tvRate.setText(String.valueOf(mascota.getRaiting())+"");
    }

    @Override
    public int getItemCount() {
        return listMacotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{


        private ImageView ivMascota;
        private TextView tvRate;

        public MascotaViewHolder(final View itemView) {
            super(itemView);
            ivMascota=(ImageView) itemView.findViewById(R.id.ivMascota);
            tvRate=(TextView)  itemView.findViewById(R.id.tvRate);
        }
    }
}
