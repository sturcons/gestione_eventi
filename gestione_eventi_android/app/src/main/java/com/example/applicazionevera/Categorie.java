package com.example.applicazionevera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Categorie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);
        Button bottone;
        ImageButton button;

        bottone=(Button)findViewById(R.id.toSport);
        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEvento();
            }

        });
        bottone=(Button)findViewById(R.id.Cultura);
        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEvento();
            }

        });
        bottone=(Button)findViewById(R.id.Servizi);
        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEvento();
            }

        });
        bottone=(Button)findViewById(R.id.Intrattenimento);
        bottone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEvento();
            }

        });

    }

    public void openHome() {
        Intent HomeIntent = new Intent(this, Home.class);
        startActivity(HomeIntent);
    }
    public void openSettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }
    public void openNotifications() {
        Intent intent = new Intent(this, Notifiche.class);
        startActivity(intent);
    }

    public void openSearch() {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
    public void openEvento() {
        Intent intent = new Intent(this, Evento.class);
        startActivity(intent);
    }
}
