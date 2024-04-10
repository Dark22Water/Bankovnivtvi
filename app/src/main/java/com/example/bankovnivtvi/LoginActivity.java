package com.example.bankovnivtvi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    private DatabaseManager dbManager;

    private Button createAccountButton; // Deklarace členské proměnné pro tlačítko

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Zde prověřte přihlašovací údaje
                // Předpokládá se, že validace probíhá ve vaší aplikaci

                // Pokud jsou přihlašovací údaje platné, přesměrujte na hlavní obrazovku
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Uzavře aktuální aktivitu
            }

        });

        createAccountButton = findViewById(R.id.button_create_account); // Inicializace tlačítka

        // Přidání OnClickListeneru k tlačítku pro vytvoření účtu
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tělo metody onClick - zde se provede akce po kliknutí na tlačítko
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
