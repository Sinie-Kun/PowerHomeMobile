package iut.dam.powerhome;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText phoneNumberEditText;
    private EditText floorEditText;
    private EditText areaEditText;
    private Spinner countryCodeSpinner;
    private Button registerButton;
    private ImageButton backButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstNameEditText = findViewById(R.id.editTextRegisterFirstName);
        lastNameEditText = findViewById(R.id.editTextRegisterLastName);
        emailEditText = findViewById(R.id.editTextRegisterEmail);
        passwordEditText = findViewById(R.id.editTextRegisterPassword);
        phoneNumberEditText = findViewById(R.id.editTextRegisterPhoneNumber);
        registerButton = findViewById(R.id.registerButton);
        backButton = findViewById(R.id.backButtonRegister);

        registerButton.setOnClickListener(v -> {
            String firstName = firstNameEditText != null && firstNameEditText.getText() != null ? firstNameEditText.getText().toString() : "";
            String lastName = lastNameEditText != null && lastNameEditText.getText() != null ? lastNameEditText.getText().toString() : "";
            String email = emailEditText != null && emailEditText.getText() != null ? emailEditText.getText().toString() : "";
            String password = passwordEditText != null && passwordEditText.getText() != null ? passwordEditText.getText().toString() : "";
            String phoneNumber = phoneNumberEditText != null && phoneNumberEditText.getText() != null ? phoneNumberEditText.getText().toString() : "";

            String countryCode = "";
            if (countryCodeSpinner != null && countryCodeSpinner.getSelectedItem() != null) {
                countryCode = countryCodeSpinner.getSelectedItem().toString();
            }

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Il faut remplir tout les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 5) {
                Toast.makeText(getApplicationContext(), "Le mdp doit contenir au moins 5 caractères", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!isValidEmail(email)) {
                Toast.makeText(getApplicationContext(), "Email invalide", Toast.LENGTH_SHORT).show();
                return;
            }


            if (!phoneNumber.isEmpty() && phoneNumber.length() < 8) {
                Toast.makeText(getApplicationContext(), "Num de tel trop court", Toast.LENGTH_SHORT).show();
                return;
            }

            registerUser(firstName, lastName, email, password, countryCode, phoneNumber);
        });

        if (backButton != null) {
            backButton.setOnClickListener(v -> {
                finish();
            });
        }
    }
    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void registerUser(String firstName, String lastName, String email, String password,
                              String countryCode, String phoneNumber) {
        StringBuilder urlBuilder = new StringBuilder("http://10.0.2.2/PowerHome/register.php?");

        if (!firstName.isEmpty()) {
            urlBuilder.append("firstName=").append(Uri.encode(firstName)).append("&");
        }

        if (!lastName.isEmpty()) {
            urlBuilder.append("lastName=").append(Uri.encode(lastName)).append("&");
        }

        if (!email.isEmpty()) {
            urlBuilder.append("mail=").append(Uri.encode(email)).append("&");
        }

        if (!password.isEmpty()) {
            urlBuilder.append("password=").append(Uri.encode(password)).append("&");
        }

        if (!countryCode.isEmpty()) {
            urlBuilder.append("countryCode=").append(Uri.encode(countryCode)).append("&");
        }

        if (!phoneNumber.isEmpty()) {
            urlBuilder.append("phoneNumber=").append(Uri.encode(phoneNumber)).append("&");
        }

        String url = urlBuilder.toString();
        if (url.endsWith("&")) {
            url = url.substring(0, url.length() - 1);
        }

        Ion.with(this)
                .load(url)
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        if (e != null) {
                            if (getApplicationContext() != null) {
                                Toast.makeText(getApplicationContext(), "Erreur réseau", Toast.LENGTH_SHORT).show();
                            }
                            e.printStackTrace();
                            return;
                        }

                        if (result != null && getApplicationContext() != null) {
                            try {
                                JSONObject jsonResponse = new JSONObject(result);

                                if (jsonResponse.has("success") && jsonResponse.getBoolean("success")) {
                                    Toast.makeText(getApplicationContext(), "Inscription réussie!", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    String message = jsonResponse.has("message") ? jsonResponse.getString("message") : "L'email est déjà pris ou une erreur est survenue!";
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException jsonException) {
                                jsonException.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Erreur serveur", Toast.LENGTH_SHORT).show();
                            }
                        } else if (getApplicationContext() != null) {
                            Toast.makeText(getApplicationContext(), "Aucune réponse du serveur", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}