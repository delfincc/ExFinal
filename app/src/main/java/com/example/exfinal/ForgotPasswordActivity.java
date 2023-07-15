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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ForgotPasswordActivity extends AppCompatActivity {

    private static final String API_URL = "https://examenfinal-7ffb7-default-rtdb.firebaseio.com/usuarios.json";

    private Button guardarButton;
    private Button registrarseButton;
    private EditText usuarioEditText;
    private EditText correoEditText;
    private EditText nuevaContraEditText;
    private EditText confirmarContraEditText;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        requestQueue = Volley.newRequestQueue(this);

        guardarButton = findViewById(R.id.btnGuardarContra);
        registrarseButton = findViewById(R.id.btnFpSignUp);
        usuarioEditText = findViewById(R.id.edtFpUsuario);
        correoEditText = findViewById(R.id.edtFpCorreo);
        nuevaContraEditText = findViewById(R.id.edtFpNuevaContra);
        confirmarContraEditText = findViewById(R.id.edtFpConfirmarNContra);

        registrarseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        guardarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = usuarioEditText.getText().toString().trim();
                String correo = correoEditText.getText().toString().trim();
                String nuevaContra = nuevaContraEditText.getText().toString().trim();
                String confirmarContra = confirmarContraEditText.getText().toString().trim();

                if (validateInputs(usuario, correo, nuevaContra, confirmarContra)) {
                    verifyUserAndUpdatePassword(usuario, correo, nuevaContra);
                }
            }
        });
    }

    private boolean validateInputs(String usuario, String correo, String nuevaContra, String confirmarContra) {
        if (usuario.isEmpty() || correo.isEmpty() || nuevaContra.isEmpty() || confirmarContra.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!nuevaContra.equals(confirmarContra)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void verifyUserAndUpdatePassword(final String usuario, final String correo, final String nuevaContra) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            boolean userExists = false;
                            String userId = "";

                            Iterator<String> keys = response.keys();
                            while (keys.hasNext()) {
                                String key = keys.next();
                                JSONObject userObject = response.getJSONObject(key);
                                String storedUsername = userObject.getString("username");
                                String storedEmail = userObject.getString("email");

                                if (usuario.equals(storedUsername) && correo.equals(storedEmail)) {
                                    userExists = true;
                                    userId = key;
                                    break;
                                }
                            }

                            if (userExists) {
                                updatePassword(userId, nuevaContra);
                            } else {
                                Toast.makeText(ForgotPasswordActivity.this, "Usuario no encontrado o correo incorrecto", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForgotPasswordActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }

    private void updatePassword(String userId, String nuevaContra) {
        String url = API_URL.replace(".json", "/" + userId + ".json");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PATCH, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            response.put("password", nuevaContra);
                            Toast.makeText(ForgotPasswordActivity.this, "Contraseña actualizada con éxito", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPasswordActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForgotPasswordActivity.this, "Error al actualizar la contraseña", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public byte[] getBody() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("password", nuevaContra);
                    return jsonObject.toString().getBytes();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };

        requestQueue.add(request);
    }

}




