package com.example.tryoutpas_29_19;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuUtama extends AppCompatActivity {

    private Button btnpremi;
    private Button btnspan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_utama);

        btnpremi = findViewById(R.id.premiliga);
        btnspan = findViewById(R.id.espaliga);

        btnpremi.setOnClickListener(v ->{
            Intent intent = new Intent(MenuUtama.this, MainActivity.class);
            intent.putExtra("LEAGUE_NAME", "English Premier League");
            startActivity(intent);
        });

        btnspan.setOnClickListener(v ->{
            Intent intent = new Intent(MenuUtama.this, MainActivity.class);
            intent.putExtra("LEAGUE_NAME", "Spanish La Liga");
            startActivity(intent);
        });
    }
}