package com.example.exfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class BoletaFragment extends Fragment {

    private ListView listView;
    private CustomListAdapter adapter;
    private List<Plato> platosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_boleta, container, false);

        listView = rootView.findViewById(R.id.listview);
        platosList = new ArrayList<>();


        Plato plato1 = new Plato("Aji de gallina", "20",
                "El Aji de gallina viene con un huevo y arroz",
                "gs://examenfinal-7ffb7.appspot.com/platos/aji.png");



        platosList.add(plato1);


        adapter = new CustomListAdapter(getContext(), platosList);
        listView.setAdapter(adapter);

        return rootView;
    }
}

