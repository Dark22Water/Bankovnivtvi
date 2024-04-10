package com.example.bankovnivtvi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    private TextView balanceTextView;
    private Button transferButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        balanceTextView = findViewById(R.id.textView_balance);
        transferButton = findViewById(R.id.button_transfer);

        double currentBalance = loadBalanceFromDatabase();
        displayBalance(currentBalance);

        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Zde můžete implementovat funkci pro převod peněz
                Toast.makeText(HomeActivity.this, "Funkce převodu peněz není implementována", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private double loadBalanceFromDatabase() {
        // Simulace načtení zůstatku z databáze
        return 15000.0; // Například 15000 Kč
    }

    private void displayBalance(double balance) {
        balanceTextView.setText("Aktuální zůstatek: " + balance + " Kč");
    }
}
