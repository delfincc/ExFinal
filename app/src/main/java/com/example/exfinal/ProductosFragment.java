package com.example.exfinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ProductosFragment extends Fragment implements CustomListAdapter.OnPlatoClickListener {

    private ListView listView;
    private CustomListAdapter adapter;
    private List<Plato> platosList;
    private OnPlatoSelectedListener onPlatoSelectedListener;

    public interface OnPlatoSelectedListener {
        void onPlatoSelected(Plato plato);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_productos, container, false);

        listView = rootView.findViewById(R.id.listview);
        platosList = new ArrayList<>();


        Plato plato1 = new Plato("Aji de Gallina", "20",
                "Categoría: Plato Principal\nInfo: Aji de gallina con un huevo cocido y una porción de arroz",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Faji.png?alt=media&token=0734fedf-12fc-4bd0-9660-e1236abea4c3");
        Plato plato2 = new Plato("Suspiro a la Limeña", "8",
                "Categoría: Postre\nInfo: Delicioso suspiro a la limeña en una copa de vidrio",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fsuspiro.jpg?alt=media&token=e6e6d1d7-695c-403c-84e1-af66ab3787ba");
        Plato plato3 = new Plato("Anticucho", "18",
                "Categoría: Entrada\nInfo: 3 palos de anticucho de corazón de res con papas de acompañamiento",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fanticucho.jpg?alt=media&token=9bc13d57-f049-4d95-9fd4-3e425f7944ab");
        Plato plato4 = new Plato("Seco de cabrito", "22",
                "Categoría: Plato Principal\nInfo: Seco de cabrito con arroz y verduras, acompañados de cebolla con ají",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fseco.jpg?alt=media&token=72bc5e86-fc57-44f3-87a0-53f4fda781bb");
        Plato plato5 = new Plato("Arroz con leche", "10",
                "Categoría: Postre\nInfo: Arroz con leche con canela",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Farrozcon.jpg?alt=media&token=a7fc04a1-39e2-4e38-9bd0-7f28a90bafb4");
        Plato plato6 = new Plato("Pollo a la Brasa", "24",
                "Categoría: Plato Principal\nInfo: 1/4 de pollo a la brasa con ricas papas fritas",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fpollito.jpg?alt=media&token=4ff27195-f2a5-4652-ac1d-60cf503db16d");
        Plato plato7 = new Plato("Arroz con Pollo", "21",
                "Categoría: Plato Principal\nInfo: Arroz con pollo con papa a la huancaina de regalo",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Farrozconpollo.jpg?alt=media&token=8aa6a9d2-b5d7-4d33-a129-eb22206a2384");
        Plato plato8 = new Plato("Pisco Sour", "14",
                "Categoría: Bebida\nInfo: Delicioso pisco sour preparado por nuestro mejor barista",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fpiscosour.jpg?alt=media&token=681e518a-8eb8-4472-95a2-ce08f6a5cf9e");
        Plato plato9 = new Plato("Cau Cau de pollo", "17",
                "Categoría: Plato Principal\nInfo: Cau cua de pollo acompañado de arroz",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fcaucau.png?alt=media&token=e08f2fef-be9c-4d7a-aeb1-bbd61a17a654");
        Plato plato10 = new Plato("Papa a la Huancaina", "14",
                "Categoría: Entrada\nInfo: Rica papa ala huancaina con huevo y aceitunas",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fpapa.jpg?alt=media&token=ef5a1bdd-5d99-4163-9576-92f2324be846");
        Plato plato11 = new Plato("Causa de Pollo", "13",
                "Categoría: Entrada\nInfo: Causa rellena de pollo con mallonesa y verduras",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fcausa.jpg?alt=media&token=1e965e8e-8f3e-42f5-af5b-fd5bf6e9ebc1");
        Plato plato12 = new Plato("Mazamorra morada", "15",
                "Categoría: Postre\nInfo: Mazamorra morada con canela y frutas",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fmazamorra.png?alt=media&token=033a1d00-a916-4f10-bc94-2394baa337eb");
        Plato plato13 = new Plato("Ceviche", "15",
                "Categoría: Entrada\nInfo: Ceviche de tilapia con choclo, camote y maiz cerrano",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fceviche.jpg?alt=media&token=93882082-3838-4c02-bbc0-b1eb0e68f712");
        Plato plato14 = new Plato("Bebida Maracuyá", "9",
                "Categoría: Bebida\nInfo: Bebida de maracuyá recien hecha",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fmaracuya.jpg?alt=media&token=25a2f8ff-76a2-48e5-bdf8-7d690e833887");
        Plato plato15 = new Plato("Chicha Morada", "14",
                "Categoría: Bebida\nInfo: Jarra de chicha morada para 4 personas",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Fchicha.jpg?alt=media&token=1ce027dd-f30a-4df1-afdf-fbb5d76141b2");
        Plato plato16 = new Plato("Lomo Saltado", "23",
                "Categoría: Plato Principal\nInfo: Lomo saltado con arroz y papas fritas",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Flomo.jpg?alt=media&token=7cdc75fc-1dfa-454b-9535-549f22422b4d");
        Plato plato17 = new Plato("Limonada", "8",
                "Categoría: Bebida\nInfo: Limonada frozen",
                "https://firebasestorage.googleapis.com/v0/b/examenfinal-7ffb7.appspot.com/o/platos%2Flimonada.jpg?alt=media&token=7e40fcf3-a85c-486b-8986-4278bc67fbeb");



        platosList.add(plato1);
        platosList.add(plato2);
        platosList.add(plato3);
        platosList.add(plato4);
        platosList.add(plato5);
        platosList.add(plato6);
        platosList.add(plato7);
        platosList.add(plato8);
        platosList.add(plato9);
        platosList.add(plato10);
        platosList.add(plato11);
        platosList.add(plato12);
        platosList.add(plato13);
        platosList.add(plato14);
        platosList.add(plato15);
        platosList.add(plato16);
        platosList.add(plato17);

        adapter = new CustomListAdapter(getContext(), platosList, this);
        listView.setAdapter(adapter);

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onPlatoSelectedListener = (OnPlatoSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnPlatoSelectedListener");
        }
    }

    @Override
    public void onPlatoClicked(Plato plato) {
        if (onPlatoSelectedListener != null) {
            onPlatoSelectedListener.onPlatoSelected(plato);
        }
    }
}