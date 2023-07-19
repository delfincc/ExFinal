package com.example.exfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signUpButton;
    private Button forgotPasswordButton;
    private RequestQueue requestQueue;

    private static final String API_URL = "https://examenfinal-7ffb7-default-rtdb.firebaseio.com/usuarios.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        usernameEditText = findViewById(R.id.edtUsuario);
        passwordEditText = findViewById(R.id.edtContraseña);
        loginButton = findViewById(R.id.btnsignin);
        signUpButton = findViewById(R.id.btnsignup);
        forgotPasswordButton = findViewById(R.id.btnfgpassword);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ingresa el usuario y la contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    verifyCredentials(username, password);
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void verifyCredentials(final String username, final String password) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean userExists = false;

                            Iterator<String> keys = response.keys();
                            while (keys.hasNext()) {
                                String key = keys.next();
                                JSONObject userObject = response.getJSONObject(key);
                                String storedUsername = userObject.getString("username");
                                String storedPassword = userObject.getString("password");

                                if (username.equals(storedUsername)) {
                                    userExists = true;
                                    if (password.equals(storedPassword)) {
                                        // Las credenciales son correctas
                                        Toast.makeText(MainActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                        return;
                                    } else {
                                        // La contraseña es incorrecta
                                        Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }
                            }

                            if (!userExists) {
                                // El usuario no existe
                                Toast.makeText(MainActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                            } else {
                                // Las credenciales son incorrectas
                                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error en la solicitud
                        Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }


}

