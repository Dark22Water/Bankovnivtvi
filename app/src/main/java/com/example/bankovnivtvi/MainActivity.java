package com.example.bankovnivtvi;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity{

    private TextView balanceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        balanceTextView = findViewById(R.id.textViewBalance);

        // Zde zobrazte aktuální zůstatek účtů uživatele
        // Předpokládá se, že data načtete z databáze a nastavíte do TextView
        // balanceTextView.setText("Zůstatek: " + currentBalance);
    }
}
