package com.example.exfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordActivity extends AppCompatActivity {

    private static final String API_URL = "https://examenfinal-7ffb7-default-rtdb.firebaseio.com/usuarios.json";

    private Button guardar, registrarse;
    private EditText usu, correo, ncontra, confcontra;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        guardar= findViewById(R.id.btnGuardarContra);
        registrarse= findViewById(R.id.btnFpSignUp);
        usu= findViewById(R.id.edtFpUsuario);
        correo= findViewById(R.id.edtFpCorreo);
        ncontra= findViewById(R.id.edtFpNuevaContra);
        confcontra= findViewById(R.id.edtFpConfirmarNContra);

        confcontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}