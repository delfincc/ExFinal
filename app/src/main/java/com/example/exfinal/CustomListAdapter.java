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
    private OnPlatoClickListener onPlatoClickListener;

    // Definir la interfaz OnPlatoClickListener
    public interface OnPlatoClickListener {
        void onPlatoClicked(Plato plato);
    }

    public CustomListAdapter(Context context, List<Plato> platosList, OnPlatoClickListener listener) {
        super(context, R.layout.platos, platosList);
        this.context = context;
        this.platosList = platosList;
        this.onPlatoClickListener = listener;
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

        textViewNombre.setText(currentPlato.getNombre());
        textViewPrecio.setText(currentPlato.getPrecio());
        textViewDatos.setText(currentPlato.getDatos());

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

        // Configurar el clic en el elemento de la lista
        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPlatoClickListener != null) {
                    onPlatoClickListener.onPlatoClicked(currentPlato);
                }
            }
        });

        return listItemView;
    }
}
