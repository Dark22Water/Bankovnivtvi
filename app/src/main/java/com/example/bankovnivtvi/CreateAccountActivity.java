package com.example.bankovnivtvi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CreateAccountActivity extends AppCompatActivity {

    private EditText newUsernameEditText;
    private EditText newPasswordEditText;
    private Button createAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        newUsernameEditText = findViewById(R.id.editText_new_username);
        newPasswordEditText = findViewById(R.id.editText_new_password);
        createAccountButton = findViewById(R.id.button_create_account);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUsername = newUsernameEditText.getText().toString();
                String newPassword = newPasswordEditText.getText().toString();

                // Zde byste prováděli logiku pro vytvoření nového účtu, například v databázi
                Toast.makeText(CreateAccountActivity.this, "Nový účet byl vytvořen", Toast.LENGTH_SHORT).show();
                finish(); // Ukončí aktivitu po vytvoření účtu
            }
        });
    }
}
