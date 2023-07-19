package com.example.exfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class BoletaListAdapter extends BaseAdapter {

    private Context context;
    private List<Plato> platosList;
    private OnPlatoDeleteListener listener;

    public BoletaListAdapter(Context context, List<Plato> platosList, OnPlatoDeleteListener listener) {
        this.context = context;
        this.platosList = platosList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return platosList.size();
    }

    @Override
    public Object getItem(int position) {
        return platosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.platos_boleta, parent, false);
        }

        Plato plato = platosList.get(position);

        TextView nombreTextView = convertView.findViewById(R.id.txvNombre);
        TextView cantidadTextView = convertView.findViewById(R.id.txvCantidad);
        TextView precioTextView = convertView.findViewById(R.id.txvPrecio);
        Button deleteButton = convertView.findViewById(R.id.btnDelete);

        nombreTextView.setText(plato.getNombre());
        cantidadTextView.setText("Cantidad: " + plato.getCantidad());
        precioTextView.setText("Precio: S/ " + plato.getPrecioTotal());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPlatoDeleted(plato);
            }
        });

        return convertView;
    }

    public interface OnPlatoDeleteListener {
        void onPlatoDeleted(Plato plato);
    }

    public void clear() {
        platosList.clear();
        notifyDataSetChanged();
    }
}
