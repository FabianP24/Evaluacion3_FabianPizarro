package com.example.evaluacion3_fabianpizarro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Objetos.Pizzas;

public class Listado_act extends AppCompatActivity {

    private ListView lista;
    private ArrayList<Pizzas> listaPizzas = new ArrayList();
    private Button eliminar;

    Pizzas pizzaSeleccionada;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lista = findViewById(R.id.lv);
        eliminar = findViewById(R.id.btn_eliPizza);

        inicializarBase();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pizzaSeleccionada = (Pizzas) parent.getItemAtPosition(position);
            }
        });

        databaseReference.child("Pizzas").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot op: snapshot.getChildren()){
                    Pizzas p = op.getValue(Pizzas.class);
                    listaPizzas.add(p);
                    ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, listaPizzas);
                    lista.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void inicializarBase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getInstance().getReference();
    }

    public void eliminarPizza(View view){
        Pizzas p = new Pizzas();
        p.setId(pizzaSeleccionada.getId());
        databaseReference.child("Pizzas").child(p.getId()).removeValue();
        Toast.makeText(getBaseContext(), "Has eliminado una pizza", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getBaseContext(), Listado_act.class);
        startActivity(i);
        finish();
    }
}