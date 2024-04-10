package com.example.bankovnivtvi;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button logoutButton = findViewById(R.id.button_logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Zde implementujte akci pro odhlášení uživatele
                // Například zde můžete spustit LoginActivity nebo provést další úkony
                Toast.makeText(HomeActivity.this, "Byl jste úspěšně odhlášen.", Toast.LENGTH_SHORT).show();
                finish(); // Ukončíme HomeActivity po odhlášení
            }
        });
    }
}
