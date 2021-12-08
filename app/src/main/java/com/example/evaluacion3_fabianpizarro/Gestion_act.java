package com.example.evaluacion3_fabianpizarro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import Objetos.Pizzas;

public class Gestion_act extends AppCompatActivity {

    private EditText name, precio, localizacion;
    private Button anadir;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);

        name = findViewById(R.id.et_nombre);
        precio = findViewById(R.id.et_precio);
        localizacion = findViewById(R.id.et_localizacion);

        anadir = findViewById(R.id.btn_addPizza);

        inicializarBase();

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pizzas p = new Pizzas();

                p.setId(UUID.randomUUID().toString());
                p.setNombre(name.getText().toString());
                p.setPrecio(precio.getText().toString());
                p.setLocalizacion(localizacion.getText().toString());

                databaseReference.child("Pizzas").child(p.getId()).setValue(p);

                Toast.makeText(getBaseContext(), "Has guardado una pizza", Toast.LENGTH_SHORT).show();

                limpiarCampos();
            }
        });
    }

    public void inicializarBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
    }

    public void limpiarCampos(){
        name.setText("");
        precio.setText("");
        localizacion.setText("");
    }
}