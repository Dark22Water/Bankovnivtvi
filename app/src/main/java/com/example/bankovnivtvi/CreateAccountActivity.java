package com.example.bankovnivtvi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    private DatabaseManager dbManager;
    private EditText newUsernameEditText;
    private EditText newPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        dbManager = new DatabaseManager(this);
        dbManager.open();

        newUsernameEditText = findViewById(R.id.editText_new_username);
        newPasswordEditText = findViewById(R.id.editText_new_password);
        Button createAccountButton = findViewById(R.id.button_create_account);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = newUsernameEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();

                long newUserId = dbManager.addUser(newUsername, newPassword);
                if (newUserId != -1) {
                    Toast.makeText(CreateAccountActivity.this, "Nový účet byl úspěšně vytvořen.", Toast.LENGTH_SHORT).show();
                    finish(); // Ukončíme aktivitu pro vytvoření účtu
                } else {
                    Toast.makeText(CreateAccountActivity.this, "Chyba při vytváření nového účtu.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
