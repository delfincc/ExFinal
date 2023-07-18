package com.example.exfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    FragmentTransaction transaction;
    BoletaFragment boleta;
    ProductosFragment productos;
    ElijaFragment elija;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        elija = new ElijaFragment();
        productos = new ProductosFragment();
        boleta = new BoletaFragment();
        back= findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(HomeActivity.this, "Sesión cerrada exitosamente", Toast.LENGTH_SHORT).show();
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.contenedorFragments, elija).commit();

    }
    public void onClick(View view){
        transaction=getSupportFragmentManager().beginTransaction();
        if(view.getId() == (R.id.btnProduct))
        {
            transaction.replace(R.id.contenedorFragments, productos).commit();
        }
        else{
            transaction.replace(R.id.contenedorFragments, boleta).commit();

        }
    }
}