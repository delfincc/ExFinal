package com.example.exfinal;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class BoletaFragment extends Fragment implements BoletaListAdapter.OnPlatoDeleteListener {

    private ListView listView;
    private BoletaListAdapter adapter;
    private List<Plato> boletaList;
    private TextView totalTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_boleta, container, false);

        listView = rootView.findViewById(R.id.listview_boleta);
        totalTextView = rootView.findViewById(R.id.txvTotal);

        // Inicializar la lista de platos de la boleta
        boletaList = new ArrayList<>();

        adapter = new BoletaListAdapter(getContext(), boletaList, this);
        listView.setAdapter(adapter);

        return rootView;
    }

    private void updateTotal() {
        double total = 0;
        for (Plato p : boletaList) {
            total += p.getPrecioTotal();
        }
        totalTextView.setText("Total a pagar: S/ " + total);
    }

    public void updateBoleta(Plato plato, int quantityChange) {
        List<Plato> updatedBoletaList = new ArrayList<>();

        for (Plato p : boletaList) {
            if (p.getNombre().equals(plato.getNombre())) {
                int newQuantity = p.getCantidad() + quantityChange;
                if (newQuantity > 0) {
                    // Si la nueva cantidad es mayor a 0, actualizamos el plato en la boleta
                    p.setCantidad(newQuantity);
                    p.setPrecioTotal(newQuantity * Double.parseDouble(p.getPrecio()));
                } else {
                    // Si la nueva cantidad es 0 o menor, no lo agregamos a la lista actualizada (lo eliminamos)
                    continue;
                }
            }
            updatedBoletaList.add(p);
        }

        boletaList = updatedBoletaList;

        adapter.notifyDataSetChanged();
        updateTotal();
    }




    @Override
    public void onPlatoDeleted(Plato plato) {
        updateBoleta(plato, -1);
        Toast.makeText(getContext(), "Plato eliminado de la boleta", Toast.LENGTH_SHORT).show();
    }

    public void addPlatoToBoleta(Plato plato) {
        boolean platoExists = false;

        for (Plato p : boletaList) {
            if (p.getNombre().equals(plato.getNombre())) {
                // Si el plato ya existe en la boleta, actualizamos la cantidad y el precio total
                p.setCantidad(p.getCantidad() + 1);
                p.setPrecioTotal(p.getCantidad() * Double.parseDouble(p.getPrecio()));
                platoExists = true;
                break;
            }
        }

        if (!platoExists) {
            // Si el plato no existe en la boleta, lo añadimos a la lista
            plato.setCantidad(1);
            plato.setPrecioTotal(Double.parseDouble(plato.getPrecio()));
            boletaList.add(plato);
        }

        adapter.notifyDataSetChanged();
        updateTotal();
    }

}