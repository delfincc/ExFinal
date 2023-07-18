package com.example.exfinal;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Plato> {

    private Context context;
    private List<Plato> platosList;

    public CustomListAdapter(Context context, List<Plato> platosList) {
        super(context, R.layout.platos, platosList);
        this.context = context;
        this.platosList = platosList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.platos, parent, false);
        }

        Plato currentPlato = platosList.get(position);

        ImageView imageView = listItemView.findViewById(R.id.imgPlato);
        TextView textViewNombre = listItemView.findViewById(R.id.txvNombre);
        TextView textViewPrecio = listItemView.findViewById(R.id.txvPrecio);
        TextView textViewDatos = listItemView.findViewById(R.id.txvDatos);


        // Cargar la imagen con Picasso
        Picasso.get()
                .load(currentPlato.getImageURL())
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        // La imagen se cargó correctamente, puedes hacer algo aquí si lo deseas
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                    }
                });

        textViewNombre.setText(currentPlato.getNombre());
        textViewPrecio.setText(currentPlato.getPrecio());
        textViewDatos.setText(currentPlato.getDatos());

        return listItemView;
    }
}

