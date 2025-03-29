package iut.dam.powerhome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import iut.dam.powerhome.MainActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView forgotPasswordTextView;
    private TextView createAccountTextView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.ConnexionMail);
        passwordEditText = findViewById(R.id.ConnexionMDP);
        loginButton = findViewById(R.id.loginButton);
        createAccountTextView = findViewById(R.id.Register);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

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
        String url = "http://10.0.2.2/login.php?email=" + email + "&password=" + password;

        Ion.with(this)
                .load(url)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {
                            Toast.makeText(getApplicationContext(), "Erreur réseau: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                            return;
                        }

                        if (result != null) {
                            try {
                                JSONObject jsonResponse = new JSONObject(result);

                                if (jsonResponse.has("token")) {
                                    String token = jsonResponse.getString("token");
                                    String expiredAt = jsonResponse.getString("expired_at");

                                    Toast.makeText(getApplicationContext(), "Connexion réussie !", Toast.LENGTH_SHORT).show();
                                    if (jsonResponse.has("token")) {
                                        String tokenn = jsonResponse.getString("token");
                                        String expiredAtt = jsonResponse.getString("expired_at");

                                        android.content.SharedPreferences prefs = getSharedPreferences("PowerHome", MODE_PRIVATE);
                                        android.content.SharedPreferences.Editor editor = prefs.edit();
                                        editor.putString("email", email);
                                        editor.putString("password", password);
                                        editor.apply();

                                        Toast.makeText(getApplicationContext(), "Connexion réussie !", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("token", tokenn);
                                        intent.putExtra("expired_at", expiredAtt);
                                        startActivity(intent);
                                        finish();
                                    }
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("token", token);
                                    intent.putExtra("expired_at", expiredAt);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Le mail ou le mot de passe est incorrect.", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Erreur serveur: " + result, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Aucune réponse du serveur.", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }
}