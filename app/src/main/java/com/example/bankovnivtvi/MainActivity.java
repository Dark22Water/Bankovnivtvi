package com.example.bankovnivtvi;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private EditText usernameEditText;
    private EditText passwordEditText;

    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        usernameEditText = findViewById(R.id.editText_username);
        passwordEditText = findViewById(R.id.editText_password);
        Button loginButton = findViewById(R.id.button_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                long userId = dbManager.getUserIdByUsernameAndPassword(username, password);
                if (userId != -1) {
                    // Přihlášení úspěšné, přesuneme se na HomeActivity
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Ukončíme MainActivity
                } else {
                    // Přihlášení neúspěšné, zobrazíme zprávu o chybě
                    Toast.makeText(MainActivity.this, "Neplatné přihlašovací údaje.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Inicializace tlačítka pro vytvoření nového účtu
        createAccountButton = findViewById(R.id.button_create_account);

        // Nastavení OnClickListeneru pro tlačítko "Vytvořit nový účet"
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Spuštění CreateAccountActivity po kliknutí na tlačítko
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
