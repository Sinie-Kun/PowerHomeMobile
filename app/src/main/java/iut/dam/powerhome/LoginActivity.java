package iut.dam.powerhome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView createAccountTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.ConnexionMail);
        passwordEditText = findViewById(R.id.ConnexionMDP);
        loginButton = findViewById(R.id.loginButton);
        createAccountTextView = findViewById(R.id.Register);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }
            loginUser(email, password);
        });

        createAccountTextView.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser(String email, String password) {
        String url = "http://10.0.2.2/PowerHome/login.php?email=" + email + "&password=" + password;

        Ion.with(this)
                .load(url)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {
                            Toast.makeText(getApplicationContext(), "Erreur réseau", Toast.LENGTH_SHORT).show();
                            Log.e("LOGIN_ERROR", e.getMessage());
                            return;
                        }

                        Log.d("LOGIN_RESPONSE", "Réponse brute : " + result);

                        try {
                            JSONObject jsonResponse = new JSONObject(result);
                            if (jsonResponse.has("token")) {
                                String token = jsonResponse.getString("token");
                                String expiredAt = jsonResponse.getString("expired_at");

                                SharedPreferences prefs = getSharedPreferences("PowerHome", MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefs.edit();
                                editor.putString("token", token);
                                editor.putString("expired_at", expiredAt);
                                editor.apply();

                                Toast.makeText(getApplicationContext(), "Connexion réussie !", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("token", token);
                                intent.putExtra("expired_at", expiredAt);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException jsonException) {
                            Toast.makeText(getApplicationContext(), "Erreur serveur", Toast.LENGTH_SHORT).show();
                            Log.e("LOGIN_JSON_ERROR", jsonException.getMessage());
                        }
                    }
                });
    }
}