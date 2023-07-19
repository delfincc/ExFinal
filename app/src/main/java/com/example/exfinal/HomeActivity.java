    package com.example.exfinal;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;
    import androidx.fragment.app.FragmentTransaction;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.Toast;

    public class HomeActivity extends AppCompatActivity implements ProductosFragment.OnPlatoSelectedListener {

        FragmentTransaction transaction;
        ProductosFragment productos;
        BoletaFragment boletaFragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            productos = new ProductosFragment();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contenedorFragments, productos, "ProductosFragment").commit();

            boletaFragment = new BoletaFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedorFragments, boletaFragment, "BoletaFragment")
                    .hide(boletaFragment) // Ocultar el fragmento de boleta inicialmente
                    .commit();

            Button back = findViewById(R.id.btnBack);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    Toast.makeText(HomeActivity.this, "Sesión cerrada exitosamente", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onPlatoSelected(Plato plato) {
            // Mostrar el fragmento de boleta cuando se selecciona un plato
            getSupportFragmentManager().beginTransaction()
                    .show(boletaFragment)
                    .commit();

            // Pasar el plato seleccionado al fragmento de boleta
            boletaFragment.addPlatoToBoleta(plato);

            Toast.makeText(this, "Plato añadido a la boleta", Toast.LENGTH_SHORT).show();
        }

        public void onClick(View view) {
            transaction = getSupportFragmentManager().beginTransaction();
            if (view.getId() == R.id.btnProduct) {
                // Esconder el fragmento de la boleta y mostrar el fragmento de productos
                transaction.hide(boletaFragment)
                        .show(productos)
                        .commit();
            } else {
                // Esconder el fragmento de productos y mostrar el fragmento de la boleta
                transaction.hide(productos)
                        .show(boletaFragment)
                        .commit();
            }
        }
    }
