package com.example.evaluacion3_fabianpizarro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton facebook, twitter, instagram, youtube;
    private Button gestion, listado, armado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        facebook = findViewById(R.id.ib_facebook);
        twitter = findViewById(R.id.ib_twitter);
        instagram = findViewById(R.id.ib_instagram);
        youtube = findViewById(R.id.ib_youtube);

        gestion = findViewById(R.id.btn_gestPizza);
        listado = findViewById(R.id.btn_listPizza);
        armado = findViewById(R.id.btn_armPizza);

        gestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Gestion_act.class);
                startActivity(i);
            }
        });

        listado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Listado_act.class);
                startActivity(i);
            }
        });

        armado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Arma_pizza_act.class);
                startActivity(i);
            }
        });
    }

    public void Facebook(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.facebook.com/"));
        startActivity(i);
    }

    public void Instagram(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.instagram.com/"));
        startActivity(i);
    }

    public void Twitter(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.twitter.com/"));
        startActivity(i);
    }

    public void Youtube(View view){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("https://www.youtube.com/"));
        startActivity(i);
    }
}