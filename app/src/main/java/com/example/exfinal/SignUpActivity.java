package com.example.exfinal;

import androidx.annotation.NonNull;
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

public class SignUpActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText emailEditText;
    private EditText ageEditText;
    private Button signUpButton;
    private Button signInButton;
    private RequestQueue requestQueue;

    private static final String API_URL = "https://examenfinal-7ffb7-default-rtdb.firebaseio.com/usuarios.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        requestQueue = Volley.newRequestQueue(this);

        usernameEditText = findViewById(R.id.edtInUsuario);
        passwordEditText = findViewById(R.id.edtInContraseña);
        confirmPasswordEditText = findViewById(R.id.edtIncnContraseña);
        emailEditText = findViewById(R.id.edtInCorreo);
        ageEditText = findViewById(R.id.edtInEdad);
        signUpButton = findViewById(R.id.btnInSingUp);
        signInButton = findViewById(R.id.btnInSingIn);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String age = ageEditText.getText().toString();

                if (validateInputs(username, password, confirmPassword, email, age)) {
                    createUser(username, password, email, age);
                }
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean validateInputs(String username, String password, String confirmPassword, String email, String age) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void createUser(String username, String password, String email, String age) {
        JSONObject userObject = new JSONObject();
        try {
            userObject.put("username", username);
            userObject.put("password", password);
            userObject.put("email", email);
            userObject.put("age", age);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, API_URL, userObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(SignUpActivity.this, "Usuario creado exitosamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this, "Error al crear el usuario", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }
}


